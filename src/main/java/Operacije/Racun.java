package Operacije;

public class Racun {
    private String brojRacuna;
    private String kategorija;
    private double cena;
    private String valuta;

    public Racun() {
    }

    public Racun(String brojRacuna, String kategorija, double cena, String valuta) {
        this.brojRacuna = brojRacuna;
        this.kategorija = kategorija;
        this.cena = cena;
        this.valuta = valuta;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }
}
