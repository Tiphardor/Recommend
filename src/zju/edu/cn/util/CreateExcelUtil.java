package zju.edu.cn.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import zju.edu.cn.po.AveGrade;

public class CreateExcelUtil {
	public static String createExcelFile(List<AveGrade>  aveGradeList){
		String fileName = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("学生成绩");
		HSSFRow row = sheet.createRow((int)0);
		HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		HSSFCell cell = row.createCell(0);  
        cell.setCellValue("学号");  
        cell.setCellStyle(style);  
        cell = row.createCell(1);  
        cell.setCellValue("姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("方向");  
        cell.setCellStyle(style);  
        cell = row.createCell(3);  
        cell.setCellValue("加权学分");  
        cell.setCellStyle(style);  
        cell = row.createCell(4);  
        cell.setCellValue("排名");  
        cell.setCellStyle(style); 
        for (int i = 0; i < aveGradeList.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            AveGrade aveGrade = aveGradeList.get(i);
            fileName = aveGrade.getMajor();
            // 第四步，创建单元格，并设置值  
            row.createCell(0).setCellValue(aveGrade.getStudentId());  
            row.createCell(1).setCellValue(aveGrade.getStudentName());  
            row.createCell(2).setCellValue(aveGrade.getMajor());  
            row.createCell(3).setCellValue(aveGrade.getAverageGrade());
            row.createCell(4).setCellValue(aveGrade.getRate());  
        } 
        FileOutputStream fout;
		try {
			fout = new FileOutputStream(ConstantUtil.PIC_PATH+fileName+".xls");
			wb.write(fout);  
		    fout.close();
		    wb.close();
		    //return "/mnt/tomcat/sur/"+fileName+".xls";
		    return ConstantUtil.PIC_PATH+fileName+".xls";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
		
	}
}
