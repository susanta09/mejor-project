package in.login.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePassword {
	private String newPassword;
	private String confirmPassword;
	private String mail;

}
