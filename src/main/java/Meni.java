import java.util.Scanner;

public class Meni {
    private Scanner sc;

    public Meni() {
        sc = new Scanner(System.in);
    }

    public int printujGlavniMeni() {
        StringBuilder sb = new StringBuilder();
        sb.append("Izaberite opciju:\n");
        sb.append("1. Placanje\n");
        sb.append("2. Racuni\n");
        sb.append("3. Valuta\n");
        sb.append("4. Izadji\n");
        System.out.println(sb.toString());

        try {
            return opcijaIliIzadji(4, 4);
        }catch(Exception greska) {
            System.out.println("Greska je nastala" + greska.getMessage());
            return printujGlavniMeni();
        }
    }

    public int printujLogInMeni() {
        StringBuilder sb = new StringBuilder();
        sb.append("Izaberite opciju:\n");
        sb.append("1. Log in\n");
        sb.append("2. Registracija\n");
        sb.append("3. Izadji\n");
        System.out.println(sb.toString());

        try {
            return opcijaIliIzadji(3, 3);
        }catch(Exception greska) {
            System.out.println("Greska je nastala: " + greska.getMessage());
            return printujLogInMeni();
        }
    }

    private int opcijaIliIzadji(int maksimalan, int opcijaZaIzlazak) throws Exception{
        System.out.print("Opcija: ");
        int opcija = sc.nextInt();

        if (opcija > maksimalan || opcija < 1) {
            throw new Exception("Opcija broj " + opcija + "ne postoji.");
        }

        if (opcija == opcijaZaIzlazak) {
            System.exit(0);
        }

        return opcija;
    }
}
