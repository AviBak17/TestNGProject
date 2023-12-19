package utility;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlClass;

import BasePackage.BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class ReadExcelData {
    private String filePath;
    private String sheetName;
    private HSSFWorkbook workbook;
    ITestContext context;
    String strTestName;
    
    Map<String, String> singleRowData = new HashMap<String, String>();
    public ReadExcelData(String filePath, String sheetName, ITestContext context) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.context=context;
    }
    private HSSFSheet getSheet() throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheet(sheetName);
        return sheet;
    }
    public Map<String, String> getExcelAsMap() throws IOException {
    	HSSFSheet sheet = getSheet();
        List<String> columnHeader = new ArrayList<String>();
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            columnHeader.add(cellIterator.next().getStringCellValue());
        }
        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = row.getLastCellNum();
        for (int i = 1; i <= rowCount-1; i++) {
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row1.getCell(j);
                singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
            }
                             
            if(singleRowData.get("KeyWord").equals(BaseClass.strTestName)) {
            	
            	break;
            }
        }
        return singleRowData;
    }
    public String getCellValueAsString(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue= cell.getCellFormula();
            case Cell.CELL_TYPE_BLANK:
                cellValue="BLANK";
            default:
                cellValue ="DEFAULT";
        }
        return cellValue;
    }

    public String getSheetName(int index) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        String sheet = workbook.getSheetName(index);
        return sheet;
    }
    public int getSheetCount() throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        int sheetCount = workbook.getNumberOfSheets();
        return sheetCount;
    }
    public int totolColumnCount() throws IOException {
        HSSFSheet sheet = getSheet();
        Row row = sheet.getRow(0);
        int lastColumnNum = row.getLastCellNum();
        return lastColumnNum;

    }
}