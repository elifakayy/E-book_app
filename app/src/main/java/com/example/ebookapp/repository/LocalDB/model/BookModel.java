package com.example.ebookapp.repository.LocalDB.model;

public class BookModel {

    private int id;
    private String Kitap;
    private String Resim;
    private String Abonelik;
    private int SayfaSayisi;
    private Long ISBN;
    private String Yazar;
    private int OkunmaSayisi;
    private String Ozet;

    public BookModel() {
    }

    public BookModel(int id, String kitap, String resim, String abonelik, int sayfaSayisi, Long ISBN, String yazar, int okunmaSayisi, String ozet) {
        this.id = id;
        this.Kitap = kitap;
        this.Resim = resim;
        this.Abonelik = abonelik;
        this.SayfaSayisi = sayfaSayisi;
        this.ISBN = ISBN;
        this.Yazar = yazar;
        this.OkunmaSayisi = okunmaSayisi;
        this.Ozet = ozet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKitap() {
        return Kitap;
    }

    public void setKitap(String kitap) {
        Kitap = kitap;
    }

    public String getResim() {
        return Resim;
    }


    public String getAbonelik() {
        return Abonelik;
    }

    public int getSayfaSayisi() {
        return SayfaSayisi;
    }

    public Long getISBN() {
        return ISBN;
    }

    public String getYazar() {
        return Yazar;
    }

    public int getOkunmaSayisi() {
        return OkunmaSayisi;
    }

    public String getOzet() {
        return Ozet;
    }

}
