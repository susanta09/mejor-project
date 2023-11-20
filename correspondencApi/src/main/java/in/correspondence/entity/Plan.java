package in.correspondence.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer pId;
	private String planName;
	private String category;
	@Column(insertable = true,updatable = true)
	private LocalDate startDate;
	@Column(insertable = true,updatable = true)
	private LocalDate endDate;
	private String action;
	//mapping......
//	@OneToOne
//	@JoinColumn(name = "case_num",nullable = false)
//	private Citizen citizen;
}
