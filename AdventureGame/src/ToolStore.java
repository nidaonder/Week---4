public class ToolStore extends NormalLoc{

    public ToolStore(Player player){
        super(player, "Mağaza");
    }
    @Override
    public boolean onLocation(){
        System.out.println("------- Mağazaya hoşgeldiniz! -------");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1-Silahlar \n2-Zırhlar \n3-Çıkış yap");
            System.out.print("Seçiminiz : ");
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3){
                System.out.print("Hatalı giriş. Yeniden seçim yapınız: ");
                selectCase = input.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz!");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }
    public void printWeapon(){
        System.out.println("------- Silahlar -------");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId()+ " - " + w.getName() +
                    " < Para : " + w.getPrice() + ", Hasar : " + w.getDamage() + " > ");
        }
        System.out.println("0 - Çıkış yap");
    }
    public void buyWeapon(){
        System.out.print("Bir silah seçiniz: ");
        int selectWeaponId = input.nextInt();
        while (selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length){
            System.out.println("Geçersiz değer! Tekrar giriniz: ");
            selectWeaponId = input.nextInt();
        }
        if (selectWeaponId != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponId);
            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                } else {
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız!");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan bakiye: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }

    }
    public void printArmor(){
        System.out.println("------- Zırhlar -------");
        for (Armor a : Armor.armors()){
            System.out.println(a.getId() + " - " + a.getName() +
                    "  < Para : " + a.getPrice() +
                    ", Zırh : " + a.getBlock() + " >");
        }
        System.out.println("0 - Çıkış yap");
    }
    public void buyArmor(){
        System.out.print("Bir zırh seçiniz: ");
        int selectArmorId = input.nextInt();
        while (selectArmorId < 0 || selectArmorId > Armor.armors().length){
            System.out.println("Geçersiz değer! Tekrar giriniz: ");
            selectArmorId = input.nextInt();
        }
        if (selectArmorId != 0){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorId);
            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                } else {
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kalan bakiye: " + this.getPlayer().getMoney());
                }
            }
        }
    }
}
