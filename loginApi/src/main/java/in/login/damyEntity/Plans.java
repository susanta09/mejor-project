package in.login.damyEntity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class Plans{
	private Integer numberOfPlan=23;
	private LocalDate startDate=LocalDate.of(2022,05, 01);
	private LocalDate endtDate=LocalDate.of(2023,05, 01);

}
