package zju.edu.cn.util;

import java.util.List;

public class StudentDetailView {
	private String studentId;
	private String studentName;
	private String major;
	private List<CourseType> compulsory;
	private List<CourseType> degree;

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public List<CourseType> getCompulsory() {
		return compulsory;
	}
	public void setCompulsory(List<CourseType> compulsory) {
		this.compulsory = compulsory;
	}
	public List<CourseType> getDegree() {
		return degree;
	}
	public void setDegree(List<CourseType> degree) {
		this.degree = degree;
	}
}
