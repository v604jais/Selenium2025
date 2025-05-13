package hpe.inta.fas.gui.utilities;

import hpe.inta.fas.gui.base.BaseClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {



    public static List<Map<String, String>> readExcel(String filePath){
        //dataList to get final excel objects
        List<Map<String, String>> dataList = new ArrayList<>();
        //String filePath= System.getProperty("user.dir") +"/src/test/resources/testdata/Data.xlsx";
        try(FileInputStream  fis = new FileInputStream(new File(filePath))){

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("userCred");

            Row headerRow = sheet.getRow(0); //get column header
            for(int i=1; i <=sheet.getLastRowNum();i++) {
                Row row = sheet.getRow(i);
                Map<String, String> dataMap = new HashMap<>();
                for(int j=0; j<row.getLastCellNum();j++) {
                    String key = headerRow.getCell(j).getStringCellValue().trim();
                    String value = row.getCell(j).toString().trim();
                    dataMap.put(key,value);
                }
                dataList.add(dataMap);
            }

        }
        catch(Exception e){
            BaseClass.logger.error(" unable to read excel ->> "+e.getMessage());

        }
        BaseClass.logger.info("reading excel");
        return dataList;

    }


}