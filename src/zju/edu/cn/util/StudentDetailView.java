package zju.edu.cn.util;

import java.io.Serializable;
import java.util.List;

public class StudentDetailView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String studentId;
	private String studentName;
	private String major;
	private List<CourseType> compulsory;
	private List<CourseType> degree;
	private Float averageGrade;
	private Integer rate;
	private String resultCode;
	
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
	public Float getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(Float averageGrade) {
		this.averageGrade = averageGrade;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
}
