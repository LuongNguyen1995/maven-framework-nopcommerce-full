package ultilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;

    String path;
    String outPutPath;
    String fileName;
    String sheetName;

    public ConfigReader configReader = new ConfigReader();
    Properties prop = configReader.init_prop();
    public XLUtility(String fileName, String sheetName){
        this.fileName = fileName;
        this.sheetName = sheetName;
        path = prop.getProperty("testDatafolder") +fileName;
        outPutPath = prop.getProperty("outputTestDatafolder") + fileName;
    }

    public ArrayList<List<String>> getData() throws IOException{
        int totalRows = getRowCount();
        int totalCols = getCellCount(1);

        ArrayList<List<String>> data = new ArrayList<>();

        for (int i = 0; i < totalRows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < totalCols; j++) {
                row.add(getCellData(i,j));
            }
            data.add(i , row);
        }
        return data;
    }

    public void setCellData(int rowNum, int colNum, String data)throws IOException{
        File xlfile = new File(path);
        // If file not exists then create new file
        if(!xlfile.exists()){
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        // If sheet not exists then create new Sheet
        if (workbook.getSheetIndex(sheetName) == -1){
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        // If row not exists then create new Row
        if(sheet.getRow(rowNum)==null){
            sheet.createRow(rowNum);
        }
        row = sheet.getRow(rowNum);

        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public void writeDataToExcel(ArrayList<List<String>> datas) throws IOException{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Result");
        int rowNum = 0;
        for (List<String> rowData : datas){
            XSSFRow row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.size(); i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(rowData.get(i));
            }
        }

        String filePath = outPutPath.replace(".xlsx","") + "_result.xlsx";
        FileOutputStream outputStream = new FileOutputStream(filePath, false);
        workbook.write(outputStream);
        outputStream.close();
    }
    private String getCellData(int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(fileName);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter dataFormatter = new DataFormatter();
        String data;
        try{
            //Returns the formatted value of a cell as a String regardless of the cell type.
            data = dataFormatter.formatCellValue(cell);
        }catch(Exception e){
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }


    private int getCellCount(int rowNum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    private int getRowCount() {
        int rowCount = 0;
        try{
            fi = new FileInputStream(path);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            rowCount = sheet.getLastRowNum();
            workbook.close();
            fi.close();
        } catch (Exception e) {
            System.out.println("==Exception:== " + e);
        }
        return rowCount;
    }



}
