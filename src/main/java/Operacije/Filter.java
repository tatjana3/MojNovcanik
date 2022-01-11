package Operacije;

import Greske.NepravilanFilterException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Filter {
    private Nalog nalog;

    public Filter(Nalog nalog) {
        this.nalog = nalog;
    }

    public void filtriraj(ArrayList<Racun> racuni) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Izaberite filter: \n");
        sb.append("1. Svi\n");
        sb.append("2. Po broju racuna\n");
        sb.append("3. Po kategoriji\n");

        System.out.println(sb.toString());

        Scanner sc = new Scanner(System.in);

        System.out.print("Izbor: ");
        int izabranFilter = sc.nextInt();

        System.out.println();

        if (izabranFilter > 3 || izabranFilter < 1) {
            throw new NepravilanFilterException("Izabrali ste nepostojeci filter.");
        }

        ArrayList<Racun> racuniLista = new ArrayList<Racun>();

        switch (izabranFilter) {
            case 1:
                racuniLista = racuni;
                break;
            case 2:
                System.out.print("Unesite broj racuna: ");
                String brojRacuna = sc.next();
                for(Racun racun : racuni) {
                    if (racun.getBrojRacuna().toLowerCase(Locale.ROOT).equals(brojRacuna.toLowerCase(Locale.ROOT)))
                    {
                        racuniLista.add(racun);
                    }
                }
                break;
            case 3:
                System.out.print("Unesite kategoriju: ");
                String kategorija = sc.next();
                for(Racun racun : racuni) {
                    if (racun.getKategorija().toLowerCase(Locale.ROOT).equals(kategorija.toLowerCase(Locale.ROOT)))
                    {
                        racuniLista.add(racun);
                    }
                }
                break;
        }

        StringBuilder sb2 = new StringBuilder("Vasi racuni: \n");
        sb2.append("------------------------------------\n");
        for (Racun racun : racuniLista) {
            sb2.append("------------------------------------\n");
            sb2.append("Broj racuna: ");
            sb2.append(racun.getBrojRacuna());
            sb2.append('\n');
            sb2.append("Kategorija: ");
            sb2.append(racun.getKategorija());
            sb2.append('\n');
            sb2.append("Cena: ");
            sb2.append(racun.getCena());
            sb2.append(racun.getValuta().toUpperCase(Locale.ROOT));
            sb2.append('\n');
            sb2.append("------------------------------------\n");
        }

        System.out.println(sb2.toString());
    }

    public boolean ponovi() {
        StringBuilder sb = new StringBuilder("\nDa li zelite da ponovite unos?\n");
        sb.append("1. Da\n");
        sb.append("2. Ne (Nazad na glavni meni)\n");
        sb.append("\n");

        System.out.println(sb.toString());
        Scanner sc = new Scanner(System.in);

        System.out.print("Vas izbor: ");
        int izbor = sc.nextInt();

        return izbor == 1;
    }
}
