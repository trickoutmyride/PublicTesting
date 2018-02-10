package cs340.client.services;

import cs340.shared.requests.SignInRequest;
import cs340.client.communication.ServerProxy;

public class RegisterService {
	private static final RegisterService singleton = new RegisterService();

	public void register(SignInRequest request) {
		singleton.registerInner(request);
	}

	private void registerInner(SignInRequest request) {
		ServerProxy.singleton.register(request);
	}
}
