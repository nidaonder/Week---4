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
            if (getMonster().getName().equals("Yılan")) {
                droppingItem();
            } else {
                gainBooty();
                return true;
            }

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
    // İlk atağı kim yapacak?
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
    // Kullanıcı Maden bölgesinde savaşı kazanırsa buradan ödül alabilecek.
    public void droppingItem(){
        Random random = new Random();
        int randomItem = random.nextInt(1,100);
        if (randomItem <= 15) {
            Random randomFirst = new Random();
            int randomItemFirst = random.nextInt(1,100);
            if (randomItemFirst <= 20){
                System.out.println("Tebrikler TÜFEK kazandınız");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
            } else if (randomItemFirst > 20 && randomItemFirst <= 50) {
                System.out.println("Tebrikler KILIÇ kazandınız");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
            } else {
                System.out.println("Tebrikler TABANCA kazandınız");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
            }
        } else if (randomItem > 15 && randomItem <= 30) {
            int randomItemSecond = random.nextInt(1,100);
            if (randomItemSecond <= 20){
                System.out.println("AĞIR zırh kazansınız!");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
            } else if (randomItemSecond > 20 && randomItemSecond < 50) {
                System.out.println("ORTA zırh kazandınız!");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
            } else {
                System.out.println("HAFİF zırh kazandınız");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
            }
        } else if (randomItem > 30 && randomItem <= 55) {
            int randomItemThird = random.nextInt(1,100);
            if (randomItemThird <= 20){
                System.out.println("10 Para Kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            } else if (randomItemThird > 20 && randomItemThird <= 50) {
                System.out.println("5 Para Kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            } else {
                System.out.println("1 Para Kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
            System.out.println("Güncel paranız = " + this.getPlayer().getMoney());
        } else {
            System.out.println("Maalesef, hiçbir şey kazanamadınız!");
        }
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
