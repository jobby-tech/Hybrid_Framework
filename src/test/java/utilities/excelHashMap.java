package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class excelHashMap {


    // https://www.youtube.com/watch?v=lfA5M4mCGqg

    public static Map<String,String> getMapData()  {

        Map <String,String> testData = new HashMap<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("./testData/LoginTestData.xlsx");
            Workbook workbook= new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRow = sheet.getLastRowNum();




            for(int i=0;i<lastRow;i++){
                Row row = sheet.getRow(i);
                Cell keyCell = row.getCell(0);
                String key = keyCell.getStringCellValue().trim();

                Cell valueCell = row.getCell(1);
                String value = valueCell.getStringCellValue().trim();

                testData.put(key,value);
            }

        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }

        return testData;

    }

}
