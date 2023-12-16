package org.example.server;

import org.example.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerView serverView;
    private Saveable repository;
    private List<Client> connectedUsers;
    private StringBuilder chat;
    private boolean isServerWorking;

    public Server(Saveable repository, int x, int y) {
        this.repository = repository;
        serverView = new ServerWindow(this, x, y);
        connectedUsers = new ArrayList<>();
        chat = new StringBuilder();
        isServerWorking = false;
    }

    public void startServer() {
        isServerWorking = true;
        loadLog();
        serverView.printInLog("Server is started.\n");
        System.out.println("Server started");
    }

    public void stopServer() {
        isServerWorking = false;
        serverView.printInLog("Server is stopped.\n");
        System.out.println("Server stopped");
        if (saveLog()) {
            serverView.printInLog("Log was saved.\n");
            System.out.println("Log was saved.");
        } else {
            serverView.printInLog("Log was not saved.\n");
            System.out.println("Log was not saved.");
        }
    }

    public boolean postMessage(String message) {
        if (isServerWorking) {
            serverView.printInLog(message + "\n");
            chat.append(message + "\n");
            for (Client client: connectedUsers) {
                client.printInLog(message);
            }
            System.out.println("Message on server: " + message);
            return true;
        } else {
            serverView.printInLog("Accept message but server is not started.\n");
            System.out.println("Accept message but server is not started.");
            return false;
        }
    }

    public boolean connectUser(Client client) {
        if (isServerWorking) {
            connectedUsers.add(client);
            serverView.printInLog("User " + client.getLogin() + " is connected.\n");
            client.printInLog(chat.toString());
            client.printInLog("Connected to server");
            System.out.println("User " + client.getLogin() + " is connected.\n");
            return true;
        } else {
            serverView.printInLog("Try to log in but server is not started.\n");
            System.out.println("Try to log in but server is not started.");
            return false;
        }
    }

    public void disconnectUser(Client user) {
        serverView.printInLog("User " + user.getLogin() + " has left chat.\n");
        for (Client client: connectedUsers) {
            client.printInLog("User " + user.getLogin() + " has left chat.\n");
        }
        connectedUsers.remove(user);
    }

    private boolean saveLog() {
        return repository.saveLog(chat);
    }

    private void loadLog() {
        chat.append(repository.loadLog());
        serverView.printInLog(chat.toString());
    }
}
