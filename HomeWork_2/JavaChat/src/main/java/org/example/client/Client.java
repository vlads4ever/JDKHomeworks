package org.example.client;

import org.example.server.Server;

public class Client {
    private Server server;
    private ClientView clientView;
    private boolean isConnectedToServer;

    public Client(Server server, int x, int y) {
        this.server = server;
        clientView = new ChatWindow(this, x, y);
        isConnectedToServer = false;
    }

    public void connectUser(ClientView clientView) {
        if (!isConnectedToServer) {
            if (server.connectUser(this)) {
                isConnectedToServer = true;
                System.out.println("You have connect to server.");
            } else {
                clientView.writeLog("Server does not answer.\n");
                System.out.println("Server does not answer.");
            }
        } else {
            clientView.writeLog("You already have connect to server.\n");
            System.out.println("You already have connect to server.");
        }
    }

    public boolean postMessage(String message) {
        if (isConnectedToServer) {
            return server.postMessage(message);
        } else {
            clientView.writeLog("No connect to server.\n");
            System.out.println("No connect to server.");
            return false;
        }
    }

    public void disconnectFromServer() {
        if (isConnectedToServer) {
            isConnectedToServer = false;
            server.disconnectUser(this);
        }
    }

    public void printInLog(String text) {
        clientView.writeLog(text);
    }

    public String getLogin() {
        return clientView.getLogin();
    }
}
