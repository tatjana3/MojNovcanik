package Operacije;

import Greske.NeuspesnoPlacanjeException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Placanje {
    private Nalog nalog;

    public Placanje(Nalog nalog){
        this.nalog = nalog;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public void platiRacun(String username, String valuta) throws Exception {
        ArrayList<Racun> racuni = this.nalog.getRacuni(username);
        if (racuni == null) {
            racuni = new ArrayList<Racun>();
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Molimo vas da popunite neophodne podatke:");

        System.out.print("Broj racuna: ");
        String brojRacuna = sc.next();
        System.out.println();

        System.out.print("Kategorija (odeca, hrana, zabava...): ");
        String kategorija = sc.next();
        System.out.println();

        System.out.print("Cena u vasoj izabranoj valuti (" + valuta + "): ");
        double cena = sc.nextInt();
        System.out.println();

        Racun racun = new Racun(brojRacuna, kategorija, cena, valuta);

        StringBuilder sb = new StringBuilder("Vas racun izgleda ovako: \n");
        sb.append("Broj racuna: ");
        sb.append(racun.getBrojRacuna());
        sb.append('\n');
        sb.append("Kategorija: ");
        sb.append(racun.getKategorija());
        sb.append('\n');
        sb.append("Cena:");
        sb.append(racun.getCena());
        sb.append(racun.getValuta().toUpperCase(Locale.ROOT));
        sb.append('\n');
        sb.append("-----------------------------------------\n");
        sb.append("Molimo vas da potvrdite transakciju: \n");
        sb.append("1. Slazem se\n");
        sb.append("2. Odustani\n");

        System.out.println(sb.toString());
        System.out.print("Vas izbor: ");

        int izbor = sc.nextInt();

        if (izbor != 1) {
            throw new NeuspesnoPlacanjeException("Placanje je prekinuto.");
        }

        racuni.add(racun);

        this.nalog.setRacuni(username, racuni);

        System.out.println("---------------------------");
        System.out.println("Uspesno ste platili racun!");
        System.out.println("---------------------------");
    }
}
