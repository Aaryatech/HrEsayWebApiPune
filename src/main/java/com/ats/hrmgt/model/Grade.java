package com.ats.hrmgt.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_grade")
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grade_id")
	private int gradeId;

	@Column(name = "grade_name")
	private String gradeName;
 
	@Column(name = "del_status")
	private int delStatus;

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", gradeName=" + gradeName + ", delStatus=" + delStatus + "]";
	}
	
	

}
