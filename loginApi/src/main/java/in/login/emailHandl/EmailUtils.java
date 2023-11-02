package in.login.emailHandl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;
	boolean status=false;
		public boolean sendMail(Map<String,String> info)
		{
			try {
				
				MimeMessage message=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(message, true);
				
				helper.setTo(info.get("email"));
				helper.setSubject(info.get("sub"));
				helper.setText("<h2>Your password is <mark>"+info.get("password")+"</mark></h2>", true);
				//helper.addAttachment(file.getName(), file);
				
				mailSender.send(message);
				
				status=true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return status;
		}
	
}

