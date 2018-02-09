package cs340.client.services;

import cs340.shared.requests.LoginRequest;
import cs340.client.communication.ServerProxy;

public class LoginService {
	private static final LoginService singleton = new LoginService();

	public void login(LoginRequest request) {
		singleton.loginInner(request);
	}

	private void loginInner(LoginRequest request) {
		ServerProxy.singleton.login(request);
	}
}
