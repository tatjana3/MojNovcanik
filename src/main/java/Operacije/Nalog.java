package Operacije;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Nalog {
    private ArrayList<Racun> racuni;

    public Nalog(){
    }

    public Nalog(ArrayList<Racun> racuni) {
        this.racuni = racuni;
    }

    public ArrayList<Racun> getRacuni(String korisnik) throws Exception {
        FileInputStream file = new FileInputStream(new File("mojeksel.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet(korisnik);

        if (racuni == null) {
            racuni = new ArrayList<Racun>();
        }

        if (sheet == null)
            return null;

        Iterator<Row> rowIterator = sheet.iterator();

        int prolaz = 0;
        while(rowIterator.hasNext()) {
            if (prolaz == 0) {
                rowIterator.next();
                prolaz++;
                continue;
            }
            Row row = rowIterator.next();

            String brojRacuna = row.getCell(0).getStringCellValue();
            String kategorija = row.getCell(1).getStringCellValue();
            double cena = row.getCell(2).getNumericCellValue();
            String valuta = row.getCell(3).getStringCellValue();

            racuni.add(new Racun(brojRacuna, kategorija, cena, valuta));
        }

        workbook.close();
        file.close();

        return racuni;
    }

    public void setRacuni(String username, ArrayList<Racun> racuni) throws Exception {
        FileInputStream in = new FileInputStream(new File("mojeksel.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        boolean trebaNov = false;

        if (workbook == null) {
            workbook = new XSSFWorkbook();
            trebaNov = true;
        }

        XSSFSheet sheet = workbook.getSheet(username);

        if (sheet == null) {
            sheet = workbook.createSheet(username);
            trebaNov = true;
        }

        int redniBroj = 0;
        Row naslov = sheet.createRow(redniBroj++);
        naslov.createCell(0).setCellValue("Broj Racuna");
        naslov.createCell(1).setCellValue("Kategorija");
        naslov.createCell(2).setCellValue("Cena");
        naslov.createCell(3).setCellValue("Valuta");

        for(Racun racun : racuni) {
            Row row = sheet.createRow(redniBroj++);

            row.createCell(0).setCellValue(racun.getBrojRacuna());
            row.createCell(1).setCellValue(racun.getKategorija());
            row.createCell(2).setCellValue((double) racun.getCena());
            row.createCell(3).setCellValue(racun.getValuta());
        }

        in.close();

        FileOutputStream out = new FileOutputStream(new File("mojeksel.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Racuni uspesno upisani!");

        this.racuni = racuni;
    }
}
