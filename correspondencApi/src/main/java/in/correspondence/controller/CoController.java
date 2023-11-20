 package in.correspondence.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import in.correspondence.request.ReAction;
import in.correspondence.responce.CoEntityR;
import in.correspondence.servoce.CorrespondenceService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CoController {
	@Autowired
	private CorrespondenceService correspondenceService;
	@GetMapping("/correspondences")
	public ResponseEntity<?> getCorrespondenceData()
	{
		List<CoEntityR> corl=correspondenceService.getAllCorresposneData();
		if(!corl.isEmpty())
		{
			return new ResponseEntity<>(corl,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("no record is found..",HttpStatus.OK);
		}
	}
	@GetMapping("/correspondence/{caseNum}")
	public ResponseEntity<?> getCorrespondenceDataByCaseNum(@PathVariable Integer caseNum)
	{
		List<CoEntityR> corl=correspondenceService.getCorresposneData(caseNum);
		if(!corl.isEmpty())
		{
			String status=corl.get(0).getNoticeStatus();
			if (status.equals("progress")) {
//				return new ResponseEntity<>("You are in progress..",HttpStatus.OK);
				return new ResponseEntity<>(corl,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(corl,HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<>("no record is found..",HttpStatus.OK);
		}
	}
	@PostMapping("/print")
	public ResponseEntity<?> generateNotic(@RequestBody ReAction action,HttpServletResponse response)
	{
		response.setContentType("application/pdf");
		Integer r=correspondenceService.performAction(action,response);
		if(r!=null)
		{
			return new ResponseEntity<>("Action is perform successfully..",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Please check your caseNum..",HttpStatus.OK);
		}
		
	}

}
