package in.correspondence.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Citizen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer caseNum;
	private String name;
	private String gender;
	private LocalDate dob;
	@Column(unique = true)
	private String email;
	private String phNo;
	private Integer ssn;
	//private Integer planId;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createDate;
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDate updateDate;
	private String createdBy;
	//mapping..
	@ManyToOne
	@JoinColumn(name = "p_id")//if you put nullable=false the we can not put citizendata without p_id value so we accept null value at the citizen data creation
	private Plan plan;
	

}
