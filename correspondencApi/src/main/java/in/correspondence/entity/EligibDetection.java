package in.correspondence.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity
public class EligibDetection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eid;
	private String planName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer benefitAmount;
	private String denialReson;
	private String planStatus;
	//mapping...
	@OneToOne
	@JoinColumn(name = "case_num")
	private Citizen citizen;
	

}
