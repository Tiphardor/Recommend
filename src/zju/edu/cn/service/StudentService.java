package zju.edu.cn.service;

import java.util.List;

import zju.edu.cn.po.AveGrade;
import zju.edu.cn.po.Student;
import zju.edu.cn.util.StudentDetailView;

public interface StudentService {
	public List<Student> calAverageGrade(List<Student> studentList);
	public void insertStudent(List<Student> studentList);
	public AveGrade findStudentByIdAndPass(String studentId,String password);
	public StudentDetailView findStudentDetailByStudentId(String studentId);
	public List<AveGrade> findMajorStudent(String major,String type);
	public AveGrade findStudentById(String studentId);
	public Integer updatePassword(String studentId,String oldPassword,String newPassword);
}
