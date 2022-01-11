package Operacije;

import Greske.PogresanUnosZaValutuException;

import java.util.Locale;
import java.util.Scanner;

public class Valuta {
    public int promeniValutu(String[] valute) throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println("Izaberite jednu od valuta: ");

        StringBuilder sb = new StringBuilder();

        int duzina = valute.length;

        for(int i = 0; i < duzina; i++) {
            int redniBroj = i + 1;
            sb.append(redniBroj + ". " + valute[i].toUpperCase(Locale.ROOT) + "\n");
        }
        int odustaniOpcija = duzina + 1;
        sb.append(duzina + ". Odustani");

        System.out.println(sb.toString());
        System.out.print("Odabir: ");
        int izbor = sc.nextInt();

        if (izbor > odustaniOpcija || izbor < 1) {
            throw new PogresanUnosZaValutuException("Izabrali ste pogresan unos za valutu.");
        }

        return izbor;
    }
}
