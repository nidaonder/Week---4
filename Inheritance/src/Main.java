public class Main {
    public static void main(String[] args) {
        Calisan c1 = new Calisan("Mustafa Çetindağ", "1111", "a@patika.dev");
        Akademisyen a1 = new Akademisyen("Nida Onder", "2222", "b@patika.dev","CENG", "Docent");
        Memur m1 = new Memur("Ali Veli", "3333", "c@patika.dev", "Bilgi İşlem", "09:00-18:00");
        m1.yemekhane();
        m1.calis();
        OgretimGorevlisi o1 = new OgretimGorevlisi("Mahmut Çetindağ", "5555", "d@patika.dev", "CENG", "DOCENT", "110");
        o1.sinavYap();
        o1.derseGir();
        Asistan as1 = new Asistan("Nida Onder", "7878", "as@patika.dev", "CENG", " Arş.Gorevlisi", "09:00-17:00");
        as1.quizYap();
        BilgiIslem pers1 = new BilgiIslem("Batu", "5656", "ba@patika.dev", "Bilgi İşlem", "09:00-18:00", "Memur");
        pers1.networkKurulum();
        GuvenlikGorevlisi gg1 = new GuvenlikGorevlisi("Ali Veli", "3434", "ab@patika.dev", "Guvenlik", "18:00-02:00", "Guvenlik");
        gg1.nobet();
    }
}