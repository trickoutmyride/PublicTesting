package cs340.client.services;

import cs340.shared.model.ClientModel;

public class ErrorService {
    public static void onError(String message) {
        ClientModel.getInstance().sendError(message);
    }
}
