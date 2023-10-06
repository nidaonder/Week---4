import java.util.Scanner;

public class Player {
    private int damage;
    private  int health;
    private int orjinalHealth;
    private int money;
    private String charName;
    private String name;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory ;

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }
    public void selectChar(){
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Karakterler;");
        System.out.println("---------------------------------------------------------------------");
        for (GameChar gameChar : charList) {
            System.out.println("ID: " + gameChar.getId() +
                    "\t Karakter: " + gameChar.getName() +
                    "\t Hasar: " + gameChar.getDamage() +
                    "\t Sağlık: " + gameChar.getHealth() +
                    "\t Para: " + gameChar.getMoney());
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.print("Lütfen bir karakter giriniz: ");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
    }
    public void selectLocation(){
        Location location = null;
        System.out.println("Bölgeler; ");
        System.out.println("1-Güvenli Ev " +
                "\n2-Mağaza");
        System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
        int selectLocation = input.nextInt();
        switch (selectLocation){
            case 1:
                location = new SafeHouse(this);
                break;
            case 2:
                location = new ToolStore(this);
                break;
            default:
                location = new SafeHouse(this);
        }
        location.onLocation();
    }
    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOrjinalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }
    public void printInfo(){
        System.out.println("Silahınız: " + this.getInventory().getWeapon().getName() +
                ", Zırhınız: " + this.getInventory().getArmor().getName() +
                ", Bloklama: " + this.getInventory().getArmor().getBlock() +
                ", Hasarınız: " + this.getTotalDamage() +
                ", Sağlık: " + this.getHealth()  +
                ", Paranız: " + this.getMoney());
    }
    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage(){
        return damage;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }
    public int getOrjinalHealth(){
        return orjinalHealth;
    }
    public void setOrjinalHealth(int orjinalHealth){
        this.orjinalHealth = orjinalHealth;
    }
    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }
    public String getCharName(){
        return charName;
    }
    public void setCharName(String charName){
        this.charName = charName;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Inventory getInventory(){
        return inventory;
    }
    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }
}
