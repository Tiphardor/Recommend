package zju.edu.cn.mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zju.edu.cn.po.AveGrade;
import zju.edu.cn.service.StudentService;
import zju.edu.cn.util.StudentDetailView;

@Controller
@RequestMapping("/studentMobile")
public class StudentMobileController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public void login(String studentId,String password,HttpServletResponse response){
		System.out.println("进来了");
		AveGrade aveGrade = studentService.findStudentByIdAndPass(studentId, password);
		if(aveGrade != null){
			StudentDetailView sdv = studentService.findStudentDetailByStudentId(studentId);
			if(sdv != null){
				sdv.setAverageGrade(aveGrade.getAverageGrade());
				sdv.setRate(aveGrade.getRate());
				sdv.setResultCode("0");
				ObjectMapper mapper = new ObjectMapper();
				String resultJson = null;
				try {
					resultJson = mapper.writeValueAsString(sdv);
					System.out.println(resultJson);
					response.setHeader("Content-type", "text/html;charset=UTF-8"); 
					response.setCharacterEncoding("UTF-8");  
					PrintWriter pw = response.getWriter();  
					pw.write(resultJson); 
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
			}else{
				//暂无此学生成绩
				
				sdv = new StudentDetailView();
				sdv.setResultCode("9999");
				ObjectMapper mapper = new ObjectMapper();
				String resultJson = null;
				response.setHeader("Content-type", "text/html;charset=UTF-8"); 
				response.setCharacterEncoding("UTF-8");  
				PrintWriter pw;
				try {
					resultJson = mapper.writeValueAsString(sdv);
					pw = response.getWriter();
					pw.write(resultJson); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			}
		}else{
			//用户名或密码错误
			StudentDetailView sdv = new StudentDetailView();
			sdv.setResultCode("9998");
			ObjectMapper mapper = new ObjectMapper();
			String resultJson = null;
			response.setHeader("Content-type", "text/html;charset=UTF-8"); 
			response.setCharacterEncoding("UTF-8");  
			PrintWriter pw;
			try {
				resultJson = mapper.writeValueAsString(sdv);
				pw = response.getWriter();
				pw.write(resultJson); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
		}
	}
	
	@RequestMapping(value="/updateStudentPassword.action",method=RequestMethod.POST)
	public void updateStudentPassword(String studentId,String oldPassword,String newPassword,HttpServletResponse response){
		Integer result = studentService.updatePassword(studentId,oldPassword,newPassword);
		String resultCode = null;
		if(result == 0){
			//原密码不正确
			resultCode = "9999";
		}else{
			resultCode = "0";
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");  
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(resultCode); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
}

