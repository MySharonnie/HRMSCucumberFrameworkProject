package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public static List<Map<String, String>> read(String path, String sheetName) throws IOException {
        List<Map<String, String>> excelData = new ArrayList<>();

        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook xssf=new XSSFWorkbook(fis);
        Sheet sheet= xssf.getSheet(sheetName);
        int noOfRows=sheet.getPhysicalNumberOfRows();
        Row rowHeader=sheet.getRow(0);
        for (int i = 1 ; i < noOfRows; i++) {

            Row currentRow=sheet.getRow(i);
            Map <String, String> rows=new LinkedHashMap<>();
            int cellsWithData=currentRow.getPhysicalNumberOfCells();
            for (int j = 0; j < cellsWithData; j++) {
                String key = rowHeader.getCell(j).toString();
                String value = currentRow.getCell(j).toString();
                rows.put(key, value);
            }
            excelData.add(rows);


        }


        return excelData;
    }

    public static List<Map<String, String>> read (String sheetName){
        return read(Constants.EXCEL_FILE_PATH);
    }

    public static List<Map<String, String>> read() throws IOException {
        return read(Constants.EXCEL_FILE_PATH, "sheet1");
    }
}




