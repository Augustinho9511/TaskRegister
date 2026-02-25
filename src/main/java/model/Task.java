package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "TaskDAO")
public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq", sequenceName = "minha_sequence_no_banco", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne
    @JoinColumn(name = "profile_id")
	private Profile responsible;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "priority")
	private String priority;
	
	@Column(name = "deadline")
	private String deadline;
	
	public Task () {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Profile  getResponsible() {
		return responsible;
	}
	
	public void setResponsible(Profile  responsiblel) {
		this.responsible = responsiblel;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
}
