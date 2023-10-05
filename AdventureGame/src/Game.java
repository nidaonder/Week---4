import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("MACERA OYUNUNA HOŞGELDİNİZ!");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + ", bu karanlık ve sisli adaya hoşgeldin!" +
                "\nBurada yaşananların hepsi gerçek!");
        System.out.println("Lütfen bir karakter seçiniz!");
        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("======= Bölgeler ======= ");
            System.out.println();
            System.out.println("1-Güvenli Ev --> Burası sizin için güvenli, düşman yok!" +
                    "\n2-Mağaza --> Silah veya zırh satın alabilirsiniz." +
                    "\n0-Çıkış yap --> Oyunu sonlandır.");

            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            int selectLocation = input.nextInt();
            switch (selectLocation){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                default:
                    location = new SafeHouse(player);
            }
            if (location == null){
                System.out.println("Oyun bitti yine bekleriz :)");
                break;
            }
            if (!location.onLocation()){
                System.out.println("GAME OVER!");
                break;
            }
        }
    }
}
