package com.nieyue.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nieyue.util.SnowflakeIdWorker;

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
     * @return 表、行、列
     */
    public static List<List<List<Object>>> importData(File file)
    {
        Workbook wb = null;
        List<List<List<Object>>> list = new ArrayList<>();
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
        for (int i = 0; i <wb.getNumberOfSheets(); i++)//getLastRowNum()并非获取实际行数
        {
	        Sheet sheet = wb.getSheetAt(i);//获取第i张表
	        List<List<Object>> list2=new ArrayList<>();
	        for (int j = 0; j <sheet.getPhysicalNumberOfRows(); j++)//getLastRowNum()并非获取实际行数
	        {
	        	List<Object> list3=new ArrayList<>();
	            Row row = sheet.getRow(j);//获取索引为j的行，以0开始
	            for (int z = 0; z < row.getPhysicalNumberOfCells(); z++) {
	            	Cell cell = row.getCell(z);//getLastCellNum获取索引为z的行，以0开始
	            	Object value = null;
	            	if(cell==null){
	            		 value="";
	            	}else if(CellType._NONE.equals(cell.getCellTypeEnum())){
	            		cell.setCellType(CellType._NONE);
	            		 value=cell.getStringCellValue();//获取第i行的索引为j的单元格数据
	            	}else if(cell.getCellTypeEnum().equals(CellType.BOOLEAN)){
	            		cell.setCellType(CellType.BOOLEAN);
	            		value=cell.getBooleanCellValue();
	            	}else if(cell.getCellTypeEnum().equals(CellType.NUMERIC)){
	            		cell.setCellType(CellType.NUMERIC);
	            		value=cell.getNumericCellValue();
	            	}else if(cell.getCellTypeEnum().equals(CellType.STRING)){
	            		cell.setCellType(CellType.STRING);
	            		value=cell.getStringCellValue();
	            	}else if(cell.getCellTypeEnum().equals(CellType.ERROR)){
	            		cell.setCellType(CellType.ERROR);
	            		value=cell.getErrorCellValue();
	            	}else if(cell.getCellTypeEnum().equals(CellType.BLANK)){
	            		cell.setCellType(CellType.BLANK);
	            		value=cell.getStringCellValue();
	            	}else if(cell.getCellTypeEnum().equals(CellType.FORMULA)){
	            		cell.setCellType(CellType.FORMULA);
	            		value=cell.getStringCellValue();
	            	}
	            	
	            	list3.add(value);
				}
	            list2.add(list3);
	        }
	        list.add(list2);
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
     * @param sheetNameList  工作薄名称列表;
     * @param list 表 、行 、列
     * @param rootPath 根目录 如：D:/nieyue
     * @param filePath 文件目录 如：/uploaderPath/img 
     * @param fileName 文件名 如： d.xls
     */
    @SuppressWarnings("resource")
	public static void exportData(
			List<String> sheetNameList,
			List<List<List<String>>> list,
			String rootPath,
			String filePath,
			String fileName){
        try {
            if(fileName==null){
            	fileName=SnowflakeIdWorker.getId().toString()+".xls";
            }
        	File exportFile=new File(rootPath+filePath+fileName);
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
   xunhuan: for (int i = 0; i < list.size(); i++) {
            	String sheetName=sheetNameList.get(i);
            	if(sheetName==null){
            		 continue xunhuan;	
            	}
            	Sheet sheet=workBook.createSheet();
	            workBook.setSheetName(i, sheetName);//设置名字（以及编码）
	        	List<List<String>> ll=list.get(i);
	            for (int j = 0; j < ll.size(); j++) {
	           	 Row row =sheet.createRow(j);
	           	 List<String> l=ll.get(j);
	           	 	for (int z = 0; z < l.size(); z++) {
	           	 		Cell cell = row.createCell(z);
	           	 		if(l.get(z).getClass().equals(String.class)){
	           	 			cell.setCellValue((String)l.get(z));
	           	 			//System.out.println("String");
	           	 		}
	           	 		/*else if(l.get(z).getClass().equals(Double.class)){
	           	 			cell.setCellValue((Double)l.get(z));
	           	 			System.out.println("Double");
	           	 		}else if(l.get(z).getClass().equals(Date.class)){
	           	 			cell.setCellValue((Date)l.get(z));
	           	 			System.out.println("Date");
	           	 		}else if(l.get(z).getClass().equals(Calendar.class)){
	           	 			cell.setCellValue((Calendar)l.get(z));
	           	 			System.out.println("Calendar");
	           	 		}else if(l.get(z).getClass().equals(Boolean.class)){
	           	 			cell.setCellValue((Boolean)l.get(z));
	           	 			System.out.println("Boolean");
	           	 		}*/
           	 		
	           	 	}
	            }
            }
            workBook.write(out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    	ArrayList<String> listname = new ArrayList<>();
    	listname.add("士大夫似的1");
    	listname.add("dsf法规的2");
    	
    	List<List<List<String>>> list = new ArrayList<>();
    	
    	List<List<String>> list0 = new ArrayList<>();
    	ArrayList<String> list00 = new ArrayList<>();
    	list00.add("sdfdsf000");
    	list00.add("sdfdsf001");
    	list0.add(list00);
    	
    	ArrayList<String> list01 = new ArrayList<>();
    	list01.add("sdfdsf010");
    	list01.add("sdfdsf011");
    	list0.add(list01);
    	
    	ArrayList<String> list02 = new ArrayList<>();
    	list01.add("sdfdsf020");
    	list01.add("sdfdsf021");
    	list01.add("sdfdsf022");
    	list0.add(list02);
    	
    	List<List<String>> list1 = new ArrayList<>();
    	ArrayList<String> list10 = new ArrayList<>();
    	list10.add("sdfdsf100");
    	list10.add("sdfdsf101");
    	list1.add(list10);
    	
    	ArrayList<String> list11 = new ArrayList<>();
    	list11.add("sdfdsf110");
    	list11.add("sdfdsf111");
    	list1.add(list11);
    	
    	ArrayList<String> list12 = new ArrayList<>();
    	list11.add("sdfdsf120");
    	list11.add("sdfdsf121");
    	list11.add("sdfdsf122");
    	list1.add(list12);
    	
    	list.add(list0);
    	list.add(list1);
    	
    	exportData(listname,list,"D://home","/",null);
    	
    	List<List<List<Object>>> sss = importData(new File("D://home/d.xls"));
    	for (int i = 0; i < sss.size(); i++) {
    		List<List<Object>> ss = sss.get(i);
			System.out.println("------------");
			for (int j = 0; j < ss.size(); j++) {
				List<Object> s = ss.get(j);
				System.out.println(" ");
				for (int z = 0; z < s.size(); z++) {
				Object e = s.get(z);
				System.out.print(e); 
				}
			}
		}
	}
    
}
