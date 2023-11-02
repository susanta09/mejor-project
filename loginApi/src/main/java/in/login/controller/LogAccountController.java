package in.login.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.login.damyEntity.Benefit;
import in.login.damyEntity.CitizenInfo;
import in.login.damyEntity.Plans;
import in.login.entity.LogAccount;
import in.login.request.UpdatePassword;
import in.login.service.LogAccountService;
@RestController
public class LogAccountController {
	@Autowired
	private LogAccountService accountService;
	@PostMapping("/accountCreate")
	public ResponseEntity<?> accountCreater(@RequestBody LogAccount account)
	{
		LogAccount ac=accountService.createAccount(account);
		if(ac!=null)
		{
			return new ResponseEntity<>(ac,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Please chack your input",HttpStatus.OK);
		}
	}
	@PatchMapping("/updatePassword/{email}")
	public ResponseEntity<?> udatePassword(@PathVariable String email,@RequestBody UpdatePassword password)
	{
		password.setMail(email);
		boolean b=accountService.passwordUpdate(password);
		List<Object> objectList=new ArrayList<Object>();
		if(b){
			objectList.add(new Plans());
			objectList.add(new CitizenInfo());
			objectList.add(new Benefit());
			return new ResponseEntity<>(objectList,HttpStatus.OK);
		}else {
			String msg="Please check your password or mail";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UpdatePassword updatePassword)
	{
		LogAccount lac=accountService.login(updatePassword);
		if(lac!=null)
		{
			return new ResponseEntity<>(lac,HttpStatus.OK);	
		}else {
			String msg="Login account is not created";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}	
	}
	@PostMapping("/getPassword")
	public ResponseEntity<?> getPasswordByMail(@RequestBody String mail)
	{
		boolean b=accountService.getPassword(mail);
		if(b)
		{
			String msg="Please Check Your Email";
			return new ResponseEntity<>(msg,HttpStatus.OK);	
		}else {
			String msg="Please put the currect mail or sing up";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}

}
