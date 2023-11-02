package in.login.service;

import in.login.entity.LogAccount;
import in.login.request.UpdatePassword;

public interface LogAccountService {
	public LogAccount createAccount(LogAccount account);
	public boolean passwordUpdate(UpdatePassword password);
	public LogAccount login(UpdatePassword updatePassword);
	public boolean getPassword(String mail);

}
