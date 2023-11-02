package in.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.login.emailHandl.EmailUtils;
import in.login.entity.LogAccount;
import in.login.repository.LogAccountRepository;
import in.login.request.UpdatePassword;
@Service
public class LogAccountServiceImpl implements LogAccountService{
	@Autowired
	private LogAccountRepository accountRepository;
	@Autowired
	private EmailUtils emailUtils;
	@Override
	public LogAccount createAccount(LogAccount account)
	{
		Integer min=100000,max=999999;
		Integer pass=min + (int)(Math.random() * ((max - min) + 1));
		System.out.println(pass);
		account.setPassword(String.valueOf(pass));
        LogAccount logac=accountRepository.save(account);
        Map<String,String> hm=new HashMap<>();
        if(logac!=null)
        {
        	hm.put("sub","Your Login Account is created,Please update your password");
        	hm.put("email", logac.getEmail());
        	hm.put("password", logac.getPassword());
        	boolean b=emailUtils.sendMail(hm);
        	return logac;
        }else
        {
        	return logac;
        }
	}
	@Override
	public boolean passwordUpdate(UpdatePassword password)
	{
		System.out.println(password.getConfirmPassword()+" "+ password.getMail()+" "+ password.getNewPassword());

		LogAccount lac=accountRepository.checkByEmailAndPassword(password.getNewPassword(),password.getMail());
		if(lac!=null)
		{
			accountRepository.updateCpasswordByEmailAndNpassword(
					password.getConfirmPassword(), password.getMail(), password.getNewPassword());
			System.out.println("rama");
			return true;
		}else {
			return false;
		}
		
	}
	@Override
	public LogAccount login(UpdatePassword updatePassword)
	{
		LogAccount lac=accountRepository.checkByEmailAndPassword(updatePassword.getNewPassword(),updatePassword.getMail());
		return lac;
	}
	
	public boolean getPassword(String mail)
	{
		String pass=accountRepository.findPasswordByEmail(mail);
		 Map<String,String> hm=new HashMap<>();
		if(pass!=null)
		{
			hm.put("sub","Please Check Your Mail");
        	hm.put("email", mail);
        	hm.put("password", pass);
        	emailUtils.sendMail(hm);
        	return true;
		}else
		{
			return false;
		}
		
	}

}
