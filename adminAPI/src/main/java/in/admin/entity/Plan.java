package in.admin.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String planName;
	private String category;
	@Column(insertable = true,updatable = true)
	private LocalDate startDate;
	@Column(insertable = true,updatable = true)
	private LocalDate endDate;
	private String action;
}
