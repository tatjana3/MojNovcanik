import Greske.KorisnikNePostojiException;
import Greske.PrazniPodaciException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class LogIn {
    private String username;
    private String password;

    public LogIn() {
    }

    public LogIn(String username, String password) throws Exception {
        if (password.isEmpty() && password.length() < 4) {
            throw new PrazniPodaciException("Lozinka ne sme da bude prazna.");
        }

        if (username.isEmpty() && password.length() < 4){
            throw new PrazniPodaciException("Username ne sme da bude prazan.");
        }

        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password.isEmpty() && password.length() < 4) {
            throw new PrazniPodaciException("Lozinka ne sme da bude prazna ili manja od 4 karaktera.");
        }
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        if (username.isEmpty() && username.length() < 4){
            throw new PrazniPodaciException("Username ne sme da bude prazan ili manji od 4 karaktera.");
        }
        this.username = username;
    }

    public String dohvatiPodatkeIzEksela() throws Exception {
        FileInputStream file = new FileInputStream(new File("mojeksel.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet("Korisnici");

        Row row = sheet.getRow(1);

        String username = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();

        if (!username.equals(this.username) || !password.equals(this.password)) {
            throw new KorisnikNePostojiException(
                    "Korisnik sa podacima za username: " + this.username + " ili lozinkom: " + this.password +
                            " ne postoji, proverite vase podatke ili se registrujte");
        }

        Korisnik.username = this.username;
        Korisnik.ulogovan = true;

        workbook.close();
        file.close();

        return username;
    }
}
