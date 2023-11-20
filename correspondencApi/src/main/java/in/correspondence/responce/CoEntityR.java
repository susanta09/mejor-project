package in.correspondence.responce;

import java.time.LocalDate;

import lombok.Data;
@Data
public class CoEntityR {
	private Integer traceId;
	private Integer caseNum;
	private String planName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer benefitAmount;
	private String planStatus;
	private String noticeStatus;
	private LocalDate noticePrintDate;
	private String print;
	private String noticeS3Url;

}
