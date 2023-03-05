package domain;

public class LoginResult {
	private boolean valid;
	private boolean admin;
	private String errmsg;
	private User foundUser;
	
	public LoginResult(boolean valid) {
		this.valid = valid;
	}
	
	public void setIsAdmin(boolean v) {
		this.admin = v;
	}
	public void setErr(String err) {
		this.errmsg = err;
	}

	public boolean isValid() {
		return valid;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public void setFoundUser(User u) {
		this.foundUser = u;
	}
	public User getFoundUser() {
		return this.foundUser;
	}
	public void setValid(boolean v) {
		this.valid = v;
	}
}
