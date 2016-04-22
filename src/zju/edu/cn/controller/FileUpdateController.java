package zju.edu.cn.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import zju.edu.cn.exception.CustomException;
import zju.edu.cn.po.AveGrade;
import zju.edu.cn.po.Student;
import zju.edu.cn.service.StudentService;
import zju.edu.cn.util.ConstantUtil;
import zju.edu.cn.util.ExcelUtil;

@Controller
@RequestMapping("/file")
public class FileUpdateController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/upload.action",method=RequestMethod.POST)
	public String upload(MultipartFile uploadFile,Model model,HttpServletResponse response) throws CustomException {	
		//String pic_path="/mnt/tomcat/sur/";
		//String pic_path = "/Users/hardor/Desktop/picture/";
		if(uploadFile != null){
			String uploadFileName = uploadFile.getOriginalFilename();
			System.out.println(uploadFileName);
			String newUploadFileName = UUID.randomUUID() + uploadFileName.substring(uploadFileName.lastIndexOf('.'));
//			新图片
			File newFile = new File(ConstantUtil.PIC_PATH + newUploadFileName);
//			将内存中的数据写入磁盘
			System.out.println(uploadFileName);
			try {
				uploadFile.transferTo(newFile);
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<Student> studentList = new ArrayList<>();
			try {
				try {
					studentList = ExcelUtil.readExcel(newFile);
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
							
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				throw new CustomException("请上传不带有txt属性的xls或者xlsx文件");
			}
			System.out.println(studentList.size());
			if(studentList.size() != 0){
				studentService.insertStudent(studentList);
				studentService.calAverageGrade(studentList);
			}
		}
		model.addAttribute("resultCode", "文件上传成功");
		if(ConstantUtil.MAJOR_NAME != null){
			List<AveGrade> AveGradeList = studentService.findMajorStudent(ConstantUtil.MAJOR_NAME, "学号");
			model.addAttribute("majorName",ConstantUtil.MAJOR_NAME);
			model.addAttribute("aveGradeList", AveGradeList);
		}
		return "teacherPage";
	}
}
