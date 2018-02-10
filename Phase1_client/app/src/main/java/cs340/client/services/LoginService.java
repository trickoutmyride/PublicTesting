package cs340.client.services;

import cs340.shared.requests.SignInRequest;
import cs340.client.communication.ServerProxy;

public class LoginService {
	private static final LoginService singleton = new LoginService();

	public void login(SignInRequest request) {
		singleton.loginInner(request);
	}

	private void loginInner(SignInRequest request) {
		ServerProxy.singleton.login(request);
	}
}
