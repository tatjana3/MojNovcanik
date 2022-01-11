import Greske.PrazniPodaciException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SignIn {
    private String username;
    private String password;
    private String rePassword;

    public SignIn() {
    }

    public SignIn(String username, String password, String rePassword) throws Exception {
        if (username.isEmpty() || username.length() < 4) {
            throw new PrazniPodaciException("Username ne sme da bude prazan ili manji od 4 karaktera");
        }

        if (password.isEmpty() || password.length() < 4) {
            throw new PrazniPodaciException("Password ne sme da bude prazan ili manji od 4 karaktera");
        }

        if (!rePassword.equals(password)) {
            throw new PrazniPodaciException("Ponovljen password mora da bude isti kao i password.");
        }

        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public void ubaciUEksel() throws Exception {
        FileInputStream in = new FileInputStream(new File("mojeksel.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        boolean trebaNov = false;

        if (workbook == null) {
            workbook = new XSSFWorkbook();
            trebaNov = true;
        }

        XSSFSheet sheet = workbook.getSheet("Korisnici");

        if (sheet == null) {
            sheet = workbook.createSheet("Korisnici");
            trebaNov = true;
        }

        int brojKoline = 0;

        Row row = sheet.createRow(brojKoline++);

        int celija = 0;

        Cell cell1 = row.createCell(celija++);
        cell1.setCellValue("Username");

        Cell cell2 = row.createCell(celija++);
        cell2.setCellValue("Password");

        Row row2 = sheet.createRow(brojKoline++);

        int celija2 = 0;
        Cell cell3 = row2.createCell(celija2++);
        cell3.setCellValue(this.username);

        Cell cell4 = row2.createCell(celija2++);
        cell4.setCellValue(this.password);

        if (trebaNov) {
            FileOutputStream out = new FileOutputStream(new File("mojeksel.xlsx"));
            workbook.write(out);
            out.close();
        }
        workbook.close();
        System.out.println("Podaci su uspeno upisani!");
    }
}
