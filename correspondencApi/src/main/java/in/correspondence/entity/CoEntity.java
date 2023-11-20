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
public class CoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coId;
	private String noticeStatus;
	@Column(nullable = true)
	private LocalDate noticePrintDate;
	private String noticeS3Url;
	private String print;

	@OneToOne
	@JoinColumn(name = "case_num")
	private Citizen citizen;
}
