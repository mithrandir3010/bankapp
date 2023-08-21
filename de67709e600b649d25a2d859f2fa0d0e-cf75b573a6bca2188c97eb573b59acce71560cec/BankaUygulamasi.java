import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//create DATABASE bank_app;
class BankaHesabi{
    int hesapNumarasi;
    String tamAd;
    double bakiye;

    BankaHesabi(int hesapNumarasi, String tamAd,double bakiye){
        this.hesapNumarasi=hesapNumarasi;
        this.tamAd= tamAd;
        this.bakiye=bakiye;

    }
}

public class BankaUygulamasi{
    private static Map<Integer,BankaHesabi>hesaplar=new HashMap<>();
    private static int sonrakiHesapNumarasi=10000;

    public static void main(String[] args){
        Scanner scanner= new Scanner(System.in);
        while(true){
            System.out.println("1. Kullanici girisi");
            System.out.println("2. Hesap olusturma");
            System.out.println("3.para yatirma");
            System.out.println("4. para cekme");
            System.out.println("5.hesaplar arasi para aktar");
            System.out.println("6.cikis");
            System.out.println("Seciminizi yapiniz:  ");
            int secim= scanner.nextInt();

            switch (secim ){
                case 1:
                    //kullanici giris
                    break;
                case 2:
                    hesapOlustur();
                    break;
                case 3:
                    paraYatir();
                    break;
                case 4:
                    paraCek();
                    break;
                case 5:
                    paraTransferi();
                    break;
                case 6:
                    System.out.println("cikiliyor");
                    System.exit(0);
                default:
                    System.out.println("gecersiz secim.Tekrar deneyiniz");

            }
        }
    }
    private static void hesapOlustur(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("adiniz: ");
        String isim = scanner.nextLine();

        System.out.print("soyadiniz: ");
        String soyisim = scanner.nextLine();

        System.out.print("TC kimlik numaraniz: ");
        String tcKimlik = scanner.nextLine();

        BankaHesabi yeniHesap = new BankaHesabi(sonrakiHesapNumarasi, isim + " " + soyisim, 0.0);
        hesaplar.put(sonrakiHesapNumarasi, yeniHesap);
        sonrakiHesapNumarasi++;

        System.out.println("yeni hesabiniz olusturuldu. Hesap Numarasi: " + yeniHesap.hesapNumarasi);
    }

    private static void paraYatir(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("hesap numaraniz: ");
        int hesapNumarasi=scanner.nextInt();
        System.out.println("yatirmak istediginiz miktari girin: ");

        if(hesaplar.containsKey(hesapNumarasi)){
            BankaHesabi hesap =hesaplar.get(hesapNumarasi);
            double yatirilanMiktar = 0;
            hesap.bakiye +=yatirilanMiktar;
            System.out.println("yatirim tamamlandi. guncel bakiye:"+hesap.bakiye);

        }else {
            System.out.println("hesap bulunamadi");

        }

    }

    private static void paraCek(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("hesap numaraniz: ");
        int hesapNumarasi=scanner.nextInt();

        System.out.println("cekmek istediginiz tutarı giriniz");
        double yatirilanMiktar =scanner.nextDouble();

        if(hesaplar.containsKey(hesapNumarasi)){
            BankaHesabi hesap= hesaplar.get(hesapNumarasi);
            hesap.bakiye +=yatirilanMiktar;
            System.out.println("yatirim tamamlandi. guncel bakiye:" +hesap.bakiye);

        }else {
            System.out.println("hesap bulunamadi");
        }

    }
    private static void paraTransferi() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("gonderen hesap numarasi: ");
        int gonderenHesapNumarasi = scanner.nextInt();

        System.out.print("hedef hesap numarasi: ");
        int hedefHesapNumarasi = scanner.nextInt();

        System.out.print("gonderilecek miktarı girin: ");
        double gonderilecekMiktar = scanner.nextDouble();

        if (hesaplar.containsKey(gonderenHesapNumarasi) && hesaplar.containsKey(hedefHesapNumarasi)) {
            BankaHesabi gonderenHesap = hesaplar.get(gonderenHesapNumarasi);
            BankaHesabi hedefHesap = hesaplar.get(hedefHesapNumarasi);

            if (gonderenHesap.bakiye >= gonderilecekMiktar) {
                gonderenHesap.bakiye -= gonderilecekMiktar;
                hedefHesap.bakiye += gonderilecekMiktar;
                System.out.println("Islem tamamlandi. Gonderen Bakiye: " + gonderenHesap.bakiye);
            } else {
                System.out.println("Yetersiz bakiye.");
            }
        } else {
            System.out.println("Hesap bulunamadi.");
        }
    }
}