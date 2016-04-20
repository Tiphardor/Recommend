package zju.edu.cn.po;

public class Student {
	private Integer tableId;
	private String studentId;
	private String studentName;
	private String major;
	private String courseName;
	private String courseCode;
	private Float courseCredit;
	private String courseType;
	private Float courseScale;
	private Float courseGrade;
	private Float averageGrade;
	private Integer rate;
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public Float getCourseCredit() {
		return courseCredit;
	}
	public void setCourseCredit(Float courseCredit) {
		this.courseCredit = courseCredit;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public Float getCourseGrade() {
		return courseGrade;
	}
	public void setCourseGrade(Float courseGrade) {
		this.courseGrade = courseGrade;
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
	public Float getCourseScale() {
		return courseScale;
	}
	public void setCourseScale(Float courseScale) {
		this.courseScale = courseScale;
	}
	@Override
	public String toString() {
		return "Student [tableId=" + tableId + ", studentId=" + studentId + ", studentName=" + studentName + ", major="
				+ major + ", courseName=" + courseName + ", courseCode=" + courseCode + ", courseCredit=" + courseCredit
				+ ", courseType=" + courseType + ", courseScale=" + courseScale + ", courseGrade=" + courseGrade
				+ ", averageGrade=" + averageGrade + ", rate=" + rate + "]";
	}
	
}
