public class BilgiIslem extends Memur{
    private String gorev;

    public BilgiIslem(String adSoyad, String telefon, String ePosta, String departman, String mesai, String gorev){
        super(adSoyad, telefon, ePosta, departman, mesai);
        this.gorev = gorev;
    }
    public String getGorev(){
        return gorev;
    }
    public void setGorev(String gorev){
        this.gorev = gorev;
    }
    public void networkKurulum(){
        System.out.println(this.getMesai() + " saatlerinde Network Kurulum yapılıyor!");
    }
}
