package cs340.client.services;

import cs340.shared.requests.RegisterRequest;
import cs340.client.communication.ServerProxy;

public class RegisterService {
	private static final RegisterService singleton = new RegisterService();

	public void register(RegisterRequest request) {
		singleton.registerInner(request);
	}

	private void registerInner(RegisterRequest request) {
		ServerProxy.singleton.register(request);
	}
}
