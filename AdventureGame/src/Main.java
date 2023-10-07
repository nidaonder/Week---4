public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }


    /* public boolean war(int mnsNumber){
        for (int i = 1; i <= mnsNumber; i++){
            resetMonsterHealth();
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0){
                System.out.print("<V>ur veya <K>aç !: ");
                String selectWar = input.nextLine().toUpperCase();
                if (selectWar.equals("V")){
                    System.out.println("Siz vurdunuz!!");
                    this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getDamage());
                    afterHit();
                    if (this.getMonster().getHealth() > 0){
                        System.out.println();
                        System.out.println("Canavar size vurdu !!");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage < 0){
                            monsterDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz!!");
                System.out.println(this.getMonster().getAward() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }

        return true;
    } */

}