package org.example;

import org.example.client.Client;
import org.example.server.TXTRepository;
import org.example.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new TXTRepository(),0,0);
        new Client(server, 500, 0);
        new Client(server, 1000, 0);
    }
}