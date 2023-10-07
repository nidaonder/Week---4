import java.awt.font.TextHitInfo;
import java.util.Random;

public abstract class BattleLoc extends Location{
    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster){
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int mnsNumber = this.randomMonsterNumber();
        System.out.println("Şu an buradasınız: " + this.getName());
        System.out.println("Dikkatli ol! Burada " + mnsNumber + " tane " + this.getMonster().getName() + " yaşıyor!");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S") && war(mnsNumber)){
            System.out.println(this.getName() + " tüm düşmanları yendiniz!");
            gainBooty();
            return true;
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz!");
            return false;
        }
        return true;
    }
    public boolean war(int mnsNumber){
        if (firstHit()){
            // player
            return playerAttack(mnsNumber);
        } else {
            // monster
            return monsterAttack(mnsNumber);
        }
    }
    // who will strike first?
    public boolean firstHit(){
        if (Math.random() < 0.5){
            return true;
        } else {
            return false;
        }
    }
    public boolean playerAttack(int mnsNumber){
        System.out.println("İlk kullanıcı vuruyor");
        for (int i = 1; i <= mnsNumber; i++){
            resetMonsterHealth();
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0){
                System.out.print("<V>ur veya <K>aç !: ");
                String selectWar = input.nextLine().toUpperCase();
                if (selectWar.equals("V")){
                    System.out.println("Siz vurdunuz !!");
                    this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getDamage());
                    afterHit();
                    if (this.getMonster().getHealth() > 0){
                        System.out.println();
                        System.out.println("Canavar size vurdu !!");
                        int receivedDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (receivedDamage < 0){
                            receivedDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - receivedDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz!!");
                int earnMoney = this.getMonster().getAward(); // Kazanılan para!!
                System.out.println(earnMoney + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + earnMoney);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }
    public boolean monsterAttack(int mnsNumber){
        System.out.println("İlk monster vuruyor.");
        for (int i = 1; i <= mnsNumber; i++){
            resetMonsterHealth();
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0){
                System.out.println("Canavar size vurdu !!");
                int receivedDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                if (receivedDamage < 0){
                    receivedDamage = 0;
                }
                this.getPlayer().setHealth(this.getPlayer().getHealth() - receivedDamage);
                afterHit();
                if (this.getPlayer().getHealth() > 0){
                    System.out.print("<V>ur veya <K>aç !: ");
                    String selectWar = input.nextLine().toUpperCase();
                    if (selectWar.equals("V")){
                        System.out.println("Siz vurdunuz !!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getDamage());
                        afterHit();
                    } else {
                        return false;
                    }
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz !!");
                int earnMoney = this.getMonster().getAward();
                System.out.println(earnMoney + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + earnMoney);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }
    public void gainBooty(){
        System.out.println("TEBRİKLER! " + this.award + " KAZANDINIZ!!");
        this.getPlayer().setBooty(this.getPlayer().getBooty() + this.getAward());
    }
    public void resetMonsterHealth(){
        this.getMonster().setHealth(this.getMonster().getOrjinalHealth());
    }
    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " canı: " + this.getMonster().getHealth());
        System.out.println("----------");
    }
    public void playerStats(){
        System.out.println("Oyuncu Değerleri : ");
        System.out.println("--------------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());
        System.out.println("Ganimetler: " + this.getPlayer().getBooty());
        System.out.println();
    }
    public void monsterStats(int i){
        System.out.println(i + "." + this.getMonster().getName() + " Değerleri: ");
        System.out.println("--------------------------");
        System.out.println("Sağlık: " + this.getMonster().getHealth());
        System.out.println("Hasar: " + this.getMonster().getDamage());
        System.out.println("Ödül: " + this.getMonster().getAward());
        System.out.println();
    }
    public int randomMonsterNumber(){
        Random r = new Random();
        return (r.nextInt(this.getMaxMonster()) + 1);
    }

    public Monster getMonster(){
        return monster;
    }
    public void setMonster(Monster monster){
        this.monster = monster;
    }
    public String getAward(){
        return award;
    }
    public void setAward(String award){
        this.award = award;
    }
    public int getMaxMonster(){
        return maxMonster;
    }
    public void setMaxMonster(int maxMonster){
        this.maxMonster = maxMonster;
    }
}
