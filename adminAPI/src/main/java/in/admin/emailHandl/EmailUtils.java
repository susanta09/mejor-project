package in.admin.emailHandl;

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
		public boolean sendMail(String email)
		{
			try {
				
				MimeMessage message=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(message, true);
				
				helper.setTo(email);
				helper.setSubject("Your Report");
				helper.setText("<h2>Employee is created</h2>", true);
				//helper.addAttachment(file.getName(), file);
				
				mailSender.send(message);
				
				status=true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return status;
		}
	
}

