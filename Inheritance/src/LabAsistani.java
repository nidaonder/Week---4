public class LabAsistani extends Asistan{

    public LabAsistani(String adSoyad, String telefon, String ePosta, String bolum, String unvan, String ofisSaati){
        super(adSoyad, telefon, ePosta, bolum, unvan, ofisSaati);
    }
    public void LablaraGir(){
        System.out.println(this.getAdSoyad() + " lablara giriyor!");
    }
}
