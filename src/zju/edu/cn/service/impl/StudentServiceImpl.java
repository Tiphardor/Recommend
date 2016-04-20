package zju.edu.cn.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import zju.edu.cn.mapper.StudentMapper;
import zju.edu.cn.po.AveGrade;
import zju.edu.cn.po.MajorOrder;
import zju.edu.cn.po.Student;
import zju.edu.cn.po.StudentCustom;
import zju.edu.cn.service.StudentService;
import zju.edu.cn.util.ComparatorUtil;
import zju.edu.cn.util.ConstantUtil;
import zju.edu.cn.util.CourseType;
import zju.edu.cn.util.StudentDetailView;

public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentMapper studentMapper;
	private ComparatorUtil cmp = new ComparatorUtil();
	
	@Override
	public List<Student> calAverageGrade(List<Student> studentList) {
		// TODO Auto-generated method stub
		String major = null;
		float a1 = 0,b1 = 0;
		float a2 = 0,b2 = 0;
		int count = 0;
		for(int i=0;i<studentList.size();i++){
			count++;
			Student student = studentList.get(i);
			if(student.getCourseType().equals("专业方向必修课")){
				b2 = b2 + student.getCourseCredit();
				a2 = a2 + student.getCourseGrade() * student.getCourseCredit();
				//System.out.println("b2------a2="+b2 + "  "+ a2);
			}else{
				b1 = b1 + student.getCourseCredit();
				a1 = a1 + student.getCourseGrade() * student.getCourseCredit();
			}	
			if(count % ConstantUtil.COURSE_NUMBER == 0){
				float averageGrade = (float)((a2 / b2) * 0.4 + (a1 / b1) * 0.6);
				AveGrade ave_Grade = new AveGrade();
				ave_Grade.setAverageGrade(averageGrade);
				ave_Grade.setStudentId(student.getStudentId());
				ave_Grade.setStudentName(student.getStudentName());
				ave_Grade.setMajor(student.getMajor());
				ave_Grade.setPassword(student.getStudentId());
				major = student.getMajor();
				Integer stuId = 0;
				stuId = studentMapper.findAveGradeByStudentId(ave_Grade);
				if(stuId != null && stuId > 0){
					studentMapper.updateAveGrade(ave_Grade);
				}else{
					studentMapper.insertAveGrade(ave_Grade);
				}
				a1 = b1 = a2 = b2 = 0;
			}
		}
		List<AveGrade> aveGradeList = studentMapper.findAveGradeByMajor(major);
		aveGradeList = recordRate(aveGradeList);
		for(int i = 0;i<aveGradeList.size();i++){
			studentMapper.updateAveGrade(aveGradeList.get(i));
		}
		return null;
	}

	@Override
	public void insertStudent(List<Student> studentList) {
		// TODO Auto-generated method stub
		StudentCustom sc = new StudentCustom();
		for(int i=0;i<studentList.size();i++){
			Student student = studentList.get(i);
			sc.setStudentId(student.getStudentId());
			sc.setCourseName(student.getCourseName());
			Integer tableId = 0;
			tableId = studentMapper.findStudentByNameAndCourse(sc);
			if( tableId != null && tableId > 0){
				studentMapper.updateStudent(student);
				updateAverageGrade(student.getStudentId());
				List<AveGrade> aveGradeList = studentMapper.findAveGradeByMajor(student.getMajor());
				aveGradeList = recordRate(aveGradeList);
				for(int j = 0;j<aveGradeList.size();j++){
					studentMapper.updateAveGrade(aveGradeList.get(j));
				}
			}else{
				studentMapper.insertStudent(student);
			}
		}
	}
	
	private void updateAverageGrade(String studentId){
		List<Student> studentList = studentMapper.findStudentBystudentId(studentId);
		String studentId1 = null,studentName1 = null,major = null;
		float a1 = 0,b1 = 0;
		float a2 = 0,b2 = 0;
		for(int i=0;i<studentList.size();i++){
			Student student = studentList.get(i);
			studentId1 = student.getStudentId();
			studentName1 = student.getStudentName();
			major = student.getMajor();
			if(student.getCourseType().equals("专业方向必修课")){
				b2 = b2 + student.getCourseCredit();
				a2 = a2 + student.getCourseGrade() * student.getCourseCredit();
			}else{
				b1 = b1 + student.getCourseCredit();
				a1 = a1 + student.getCourseGrade() * student.getCourseCredit();
			}
		}
		float averageGrade = (float)((a2 / b2) * 0.4 + (a1 / b1) * 0.6);
		AveGrade ave_Grade = new AveGrade();
		ave_Grade.setAverageGrade(averageGrade);
		ave_Grade.setStudentId(studentId1);
		ave_Grade.setStudentName(studentName1);
		ave_Grade.setMajor(major);
		studentMapper.updateAveGrade(ave_Grade);
	}
	
	private List<AveGrade> recordRate(List<AveGrade> aveGradeList){
		Collections.sort(aveGradeList, cmp);
		for(int i = 0;i<aveGradeList.size();i++){
			aveGradeList.get(aveGradeList.size()  - i - 1).setRate(i + 1);
		}
		return aveGradeList;
	}

	@Override
	public AveGrade findStudentByIdAndPass(String studentId, String password) {
		// TODO Auto-generated method stub
		AveGrade aveGrade = new AveGrade();
		aveGrade.setStudentId(studentId);
		aveGrade.setPassword(password);
		AveGrade ave_grade = studentMapper.loginStudent(aveGrade);
		return ave_grade;
	}

	@Override
	public StudentDetailView findStudentDetailByStudentId(String studentId) {
		// TODO Auto-generated method stub
		List<Student> studentList = studentMapper.findStudentBystudentId(studentId);
		if(studentList != null){
			StudentDetailView sdv = transStudentToStudentDetail(studentList);
			return sdv;
		}
		return null;
	}
	
	private StudentDetailView transStudentToStudentDetail(List<Student> studentList){
		List<CourseType> compulsory = new ArrayList<>();
		List<CourseType> degree = new ArrayList<>();
		StudentDetailView sdv = new StudentDetailView();
		sdv.setStudentId(studentList.get(0).getStudentId());
		sdv.setStudentName(studentList.get(0).getStudentName());
		sdv.setMajor(studentList.get(0).getMajor());
		for(Student student : studentList){
			CourseType ct = new CourseType();
			ct.setCourseCode(student.getCourseCode());
			ct.setCourseName(student.getCourseName());
			ct.setCourseCredit(student.getCourseCredit());
			ct.setCourseScale(student.getCourseScale());
			ct.setCourseGrade(student.getCourseGrade());
			if(student.getCourseType().trim().equals("专业方向必修课")){
				compulsory.add(ct);
			}else{
				degree.add(ct);
			}
		}
		sdv.setCompulsory(compulsory);
		sdv.setDegree(degree);
		return sdv;
	}

	@Override
	public List<AveGrade> findMajorStudent(String major, String type) {
		// TODO Auto-generated method stub
		MajorOrder mo = new MajorOrder();
		mo.setMajor(major);
		mo.setType(type);
		List<AveGrade> aveGradeList = studentMapper.findAveGradeByMajorOrderBy(mo);
		return aveGradeList;
	}

	@Override
	public AveGrade findStudentById(String studentId) {
		// TODO Auto-generated method stub
		AveGrade aveGrade = studentMapper.findAveGradeById(studentId);
		return aveGrade;
	}
}
