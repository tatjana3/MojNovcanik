import Greske.KorisnikNePostojiException;
import Greske.KorisnikNemaRacuneException;
import Greske.NepravilanFilterException;
import Greske.PrazniPodaciException;
import Operacije.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StartKlasa {
    // Start aplikacije
    public static void start(String meniKod, boolean prviUlaz) {
        Meni meni = new Meni();

        try {
            if (!prviUlaz) {
                switch(meniKod) {
                    case "glavni":
                        int opcijaGlavniMeni = meni.printujGlavniMeni();

                        switch(opcijaGlavniMeni) {
                            case 1:
                                StartKlasa.start("placanje", false);
                                break;
                            case 2:
                                StartKlasa.start("racuni", false);
                                break;
                            case 3:
                                StartKlasa.start("valuta", false);
                                break;
                        }
                        break;
                    case "placanje":
                        Placanje placanje = new Placanje(new Nalog());
                        placanje.platiRacun(Korisnik.username, Korisnik.valuta);
                        StartKlasa.start("glavni", false);
                        break;
                    case "racuni":
                        Nalog nalog = new Nalog();
                        Filter filter = new Filter(nalog);
                        ArrayList<Racun> racuni = nalog.getRacuni(Korisnik.username);
                        filter.filtriraj(racuni);
                        if (filter.ponovi()) {
                            StartKlasa.start("racuni", false);
                        }
                        StartKlasa.start("glavni", false);
                        break;
                    case "valuta":
                        Valuta valuta = new Valuta();
                        int valutaVrednost = valuta.promeniValutu(PlacanjeProgram.dozvoljeneValute) - 1;
                        String valutaPromenjena = PlacanjeProgram.dozvoljeneValute[valutaVrednost];

                        if (valutaPromenjena.equals(Korisnik.valuta)) {
                            System.out.println("Niste nista promenili.");
                        } else {
                            System.out.println("Promenili ste valutu na: " + valutaPromenjena);
                        }

                        Korisnik.valuta = valutaPromenjena;
                        StartKlasa.start("glavni", false);
                        break;

                    default:
                        int opcijaDefault = meni.printujGlavniMeni();
                        break;
                }
            }
            else {
                Scanner sc = new Scanner(System.in);
                int opcija = meni.printujLogInMeni();

                if (opcija == 1) {
                    LogIn login = new LogIn();
                    System.out.print("Username: ");
                    String username = sc.next();
                    System.out.println();
                    System.out.print("Password: ");
                    String password = sc.next();

                    login.setUsername(username);
                    login.setPassword(password);

                    login.dohvatiPodatkeIzEksela();
                    StartKlasa.start("glavni", false);
                } else if(opcija == 2) {
                    System.out.print("Username: ");
                    String username = sc.next();

                    System.out.println();

                    System.out.print("Password: ");
                    String password = sc.next();

                    System.out.println();

                    System.out.print("Ponovite password: ");
                    String rePassword = sc.next();

                    SignIn signIn = new SignIn(username, password, rePassword);
                    signIn.ubaciUEksel();
                    StartKlasa.start("", true);
                }
            }
        }
        catch(NepravilanFilterException nepravilanFilterException) {
            System.out.println(nepravilanFilterException.getMessage());
            StartKlasa.start("racuni", false);
        }
        catch(KorisnikNemaRacuneException korisnikNemaRacuneException) {
            System.out.println(korisnikNemaRacuneException.getMessage());
            StartKlasa.start("glavni", true);
        }
        catch(KorisnikNePostojiException korisnikNePostojiException) {
            System.out.println(korisnikNePostojiException.getMessage());
            StartKlasa.start("", true);
        }
        catch (PrazniPodaciException prazniPodaciGreska) {
            System.out.println("Doslo je do greske: " + prazniPodaciGreska.getMessage());
            StartKlasa.start("", true);
        }
        catch(Exception ex) {
            System.out.println("Doslo je do greske.");
            if (Korisnik.ulogovan)  {
                StartKlasa.start("glavni", false);
            }

            StartKlasa.start("", true);
        }
    }
}
