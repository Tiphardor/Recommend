package zju.edu.cn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import zju.edu.cn.po.Student;

public class ExcelUtil {
	
	private static List<Course> courseList = new ArrayList<>();
	private static List<Student> studentList = new ArrayList<>();
	
	
	public static List<Student> readExcel(File file) throws EncryptedDocumentException, InvalidFormatException, IOException{
		courseList.clear();
		studentList.clear();
		FileInputStream is = new FileInputStream(file); //文件流
		Workbook workbook = WorkbookFactory.create(is); 
		int sheetCount = workbook.getNumberOfSheets();
		for (int s = 0; s < sheetCount; s++) {
			Sheet aSheet = workbook.getSheetAt(s);
            int rowCount = aSheet.getPhysicalNumberOfRows(); //获取总行数
            if(rowCount < 1)
            	continue;
            //遍历第一行
            Row rowOne = aSheet.getRow(0);
            int cellCountOne = rowOne.getPhysicalNumberOfCells();
            ConstantUtil.COURSE_NUMBER = cellCountOne - 3;
            System.out.println("列数"+cellCountOne);
            for(int cc = 3; cc < cellCountOne;cc++){
            	Cell cellOne = rowOne.getCell(cc);
            	String cellValueString = cellOne.getStringCellValue();
            	Course course = transCellValue(cellValueString);
            	courseList.add(course);
            }
            //遍历每一行，从第二行开始
            for (int r = 1; r < rowCount; r++) {
            	 ConStudent conStudent = new ConStudent();
            	 Row row = aSheet.getRow(r);
                 int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
                 
                 for (int c = 0; c < cellCount; c++) {
                	 Cell cell = row.getCell(c);
                	 int cellType = cell.getCellType();
                	 String cellValue = null;
                	 switch(cellType) {
                	 	case Cell.CELL_TYPE_STRING: //文本
                	 		cellValue = cell.getStringCellValue();
                	 		if(c == 0)
                	 			conStudent.setStudentCode(cellValue);
                	 		if(c == 1)
                	 			conStudent.setStudentName(cellValue);
                	 		if(c == 2)
                	 			conStudent.setMajor(cellValue);
                	 		break;
                	 	case Cell.CELL_TYPE_NUMERIC: //数字
                	 		if(c == 0){
                	 			DecimalFormat df = new DecimalFormat("0");  
                	 		    String strCell = df.format(cell.getNumericCellValue()); 
                	 			conStudent.setStudentCode(strCell);
                	 		}else{
                	 			Float grade = Float.valueOf(String.valueOf(cell.getNumericCellValue()));
                	 			//System.out.println("grade------>"+grade);
                    	 		Student student = new Student();
                    	 		student.setStudentId(conStudent.getStudentCode());
                    	 		student.setStudentName(conStudent.getStudentName());
                    	 		student.setMajor(conStudent.getMajor());
                    	 		student.setCourseCode(courseList.get(c-3).getCourseCode());
                    	 		student.setCourseName(courseList.get(c-3).getCourseName());
                    	 		student.setCourseCredit(courseList.get(c-3).getCourseCredit());
                    	 		student.setCourseType(courseList.get(c-3).getCourseType());
                    	 		student.setCourseScale(courseList.get(c-3).getCourseScale());
                    	 		student.setCourseGrade(grade);
                    	 		studentList.add(student);
                	 		}
                	 		break;
                	 	case Cell.CELL_TYPE_BLANK: //空白
                	 		Student student = new Student();
                	 		student.setStudentId(conStudent.getStudentCode());
                	 		student.setStudentName(conStudent.getStudentName());
                	 		student.setMajor(conStudent.getMajor());
                	 		student.setCourseCode(courseList.get(c-3).getCourseCode());
                	 		student.setCourseName(courseList.get(c-3).getCourseName());
                	 		student.setCourseCredit(courseList.get(c-3).getCourseCredit());
                	 		student.setCourseType(courseList.get(c-3).getCourseType());
                	 		student.setCourseScale(courseList.get(c-3).getCourseScale());
                	 		student.setCourseGrade(0F);
                	 		studentList.add(student);
                            break;
                	 }
                 }
            }
		}
		for(int i=0;i<studentList.size();i++)
		{
			System.out.println(studentList.get(i).toString());
		}
		return studentList;
	}
	
	private static Course transCellValue(String cellValue){
		int k=0,j=0;
		Course course = new Course();
		System.out.println(cellValue);
		char value[] = cellValue.toCharArray();
		for(int i=0;i<value.length;i++){
			if(!(value[i] >= '0' && value[i] <= '9')){
				k = i;
				break;
			}
		}
		for(int i=k;i<value.length;i++){
			if(value[i] >= '0' && value[i] <= '9'){
				j = i;
				break;
			}
		}
		course.setCourseCode(cellValue.substring(0, k));
		course.setCourseName(cellValue.substring(k, j));
		course.setCourseCredit(Float.valueOf(cellValue.substring(j, cellValue.length())));
		String courseName = course.getCourseName();
		if(courseName.equals("自然辩证法") || courseName.equals("硕士英语") || courseName.equals("科学社会主义理论与实践")){
			course.setCourseType("公共学位课");
			course.setCourseScale(0.6F);
		}else if(courseName.equals("软件项目管理") || courseName.equals("系统分析与设计")){
			course.setCourseType("专业学位课");
			course.setCourseScale(0.6F);
		}else{
			course.setCourseType("专业方向必修课");
			course.setCourseScale(0.4F);
		}
		return course;
	}
	
}
          
                           
