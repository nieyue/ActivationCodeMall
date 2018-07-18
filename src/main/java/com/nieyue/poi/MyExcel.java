package com.nieyue.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel
 * @author 聂跃
 * @date 2018年7月5日
 */
public class MyExcel {
	/**
	 * 是否excel2003
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2003(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
	/**
	 * 是否excel2007
	 * @param filePath
	 * @return
	 */
    public static boolean isExcel2007(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    /**
     * 单元格为Numeric格式，带有指数E,获取其String类型
     * @param cell
     * @return
     */
    public static String getStringFromNumericCell(Cell cell)
    {
        return new DecimalFormat("#").format(cell.getNumericCellValue());
    }
    /**
     * 导入excel
     * @param file
     * @return
     */
    public static List<String> importData(File file)
    {
        Workbook wb = null;
        List<String> list = new ArrayList<>();
        try
        {
            if (isExcel2007(file.getPath())) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            } else {
                wb = new HSSFWorkbook(new FileInputStream(file));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return null;
        }

        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        for (int i = 0; i < sheet.getLastRowNum(); i++)//getLastRowNum()并非获取实际行数
        {
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            String value= row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
            list.add(value);
        }
        try
        {
            wb.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 导出excel
     * @param list
     * @param templetFilePath
     * @param exportFilePath
     */
    @SuppressWarnings("resource")
	public static void exportData(List<String> list, String exportFilePath){
        try {
            File exportFile=new File(exportFilePath);
            Workbook workBook;

            if(!exportFile.exists()){
                exportFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(exportFile);
            if(isExcel2007(exportFile.getPath())){
                workBook=new XSSFWorkbook();
            }else {
                workBook=new HSSFWorkbook();
            }
             CellStyle style = workBook.createCellStyle();  
            style.setAlignment(HorizontalAlignment.CENTER); 
            //Sheet sheet=workBook.getSheetAt(0);
            Sheet sheet=workBook.createSheet("测试");
            workBook.setSheetName(0, "我的工作簿1");//设置名字（以及编码）
            Row headrow =sheet.createRow(0);
            Cell headrowcell0 = headrow.createCell(0);
            headrowcell0.setCellValue("序号");
            int rowIndex = 1 ;
            for (String item :list) {
                Row row=sheet.createRow(rowIndex);
                row.createCell(0).setCellValue(item);
                rowIndex++;
            }
            workBook.write(out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("sdfdsf");
    	exportData(list,"D://home/d.xls");
	}
    
}
