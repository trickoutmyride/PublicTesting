package client.services;

import client.commands.ICommand;

public interface IService {
    void setProxy(Proxy proxy);

    interface Proxy {
        void sendCommand(ICommand command);
    }
}
