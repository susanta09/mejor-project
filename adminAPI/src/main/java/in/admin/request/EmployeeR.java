package in.admin.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class EmployeeR {
	private String fullName;
	private String email;
	private String phNo;
	private String gender;
	private LocalDate dob;
	private Integer ssn;
	private String action;

}
