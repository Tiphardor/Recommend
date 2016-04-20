package zju.edu.cn.mapper;

import java.util.List;

import zju.edu.cn.po.AveGrade;
import zju.edu.cn.po.MajorOrder;
import zju.edu.cn.po.Student;
import zju.edu.cn.po.StudentCustom;

public interface StudentMapper {
	public void insertStudent(Student student);
	public void insertAveGrade(AveGrade aveGrade);
	public Integer findStudentByNameAndCourse(StudentCustom sc);
	public void updateStudent(Student student);
	public List<Student> findStudentBystudentId(String studentId);
	public void updateAveGrade(AveGrade aveGrade);
	public Integer findAveGradeByStudentId(AveGrade aveGrade);
	public List<AveGrade> findAveGradeByMajor(String major);
	public AveGrade loginStudent(AveGrade aveGrade);
	public List<AveGrade> findAveGradeByMajorOrderBy(MajorOrder mo);
	public AveGrade findAveGradeById(String studentId);
}
