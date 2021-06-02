package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tests.MyStoreTests;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * GetExcelData class is implemented to get data for DataProvider class
 * return data
 */
public class GetExcelData {
    private static final Logger log = LogManager.getLogger(GetExcelData.class);

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        String[][] tabArray = null;

        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int startRow = 1;
            int startCol = 0;
            int ci, cj;
            int totalRows = ExcelWSheet.getLastRowNum();

            // Can we write a function as well to get Column count?
            int totalCols = 3;
            tabArray = new String[totalRows][totalCols];
            ci = 0;

            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j <totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                    System.out.println(tabArray[ci][cj]);
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Could not read the Excel sheet" + e);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Could not read the Excel sheet" + e);
            e.printStackTrace();
        }
        return (tabArray);
    }


    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            CellType dataType = Cell.getCellType();
            //three types of data in cells: String, Numeric, Formula
            switch (dataType) {
                case FORMULA:
                    return "";
                default:
                    String CellData = Cell.getStringCellValue();
                    return CellData;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw (e);
        }
    }
}
