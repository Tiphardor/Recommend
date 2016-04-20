package zju.edu.cn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zju.edu.cn.po.Admin;
import zju.edu.cn.po.AveGrade;
import zju.edu.cn.service.AdminService;
import zju.edu.cn.service.StudentService;
import zju.edu.cn.util.CreateExcelUtil;
import zju.edu.cn.util.StudentDetailView;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(String studentId,String password,Integer type,Model model,HttpSession session){
		if(type == 1){
			AveGrade aveGrade = studentService.findStudentByIdAndPass(studentId, password);
			if(aveGrade != null){
				StudentDetailView sdv = studentService.findStudentDetailByStudentId(studentId);
				if(sdv != null){
					model.addAttribute("aveGrade", aveGrade);
					model.addAttribute("sdv", sdv);
					session.setAttribute("studentId", sdv.getStudentId());
					return "studentPage";
				}
				model.addAttribute("resultCode","暂无此学生信息");
				return "studentPage";
			}else{
				model.addAttribute("resultCode","学号或密码错误");
				model.addAttribute("studentId",studentId);
				return "login";
			}
		}else{
			Admin admin = adminService.findAdminByIdAndPass(studentId, password);
			if(admin != null && admin.getId() > 0){
				return "teacherPage";
			}else{
				model.addAttribute("resultCode","学号或密码错误");
				model.addAttribute("studentId",studentId);
				return "login";
			}
		}
	}
	
	@RequestMapping(value="/logout.action",method=RequestMethod.GET)
	public String logout(HttpSession session,String studentId){
		if(session.getAttribute("studentId") != null){
			session.removeAttribute("studentId");
		}
		return "login";
	}
	
	@RequestMapping(value="/findMajorStudent.action",method=RequestMethod.POST)
	public @ResponseBody List<AveGrade> findMajorStudent(String major,String type){
		List<AveGrade> aveGradeList = new ArrayList<>();
		if(major != null && type != null){
			aveGradeList = studentService.findMajorStudent(major, type);
		}
		return aveGradeList;
	}
	
	@RequestMapping(value="/findDetailStudent.action",method=RequestMethod.GET)
	public String findDetailStudent(String studentId,Model model){
		StudentDetailView sdv = studentService.findStudentDetailByStudentId(studentId);
		AveGrade aveGrade = studentService.findStudentById(studentId);
		model.addAttribute("sdv", sdv);
		model.addAttribute("aveGrade", aveGrade);
		return "studentDetail";
	}
	
	@RequestMapping(value="/exportExcelFile.action",method=RequestMethod.POST)
	public void exportExcelFile(String major,HttpServletResponse response){
		List<AveGrade> aveGradeList = new ArrayList<>();
		aveGradeList = studentService.findMajorStudent(major, "学号");
		String path = CreateExcelUtil.createExcelFile(aveGradeList);
		// path是指欲下载的文件的路径。
		if(path != null){
			File file = new File(path);
			// 取得文件名。
			String fileName = file.getName();
			System.out.println(fileName);
			// 以流的形式下载文件。
			
			try {
				InputStream fis = new BufferedInputStream(new FileInputStream(path));
				byte[] buffer;
				buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				// 清空response
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(fileName.getBytes("utf8"),"iso-8859-1"));
				response.addHeader("Content-Length", "" + file.length());
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
