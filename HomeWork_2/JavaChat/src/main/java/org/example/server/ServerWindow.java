package org.example.server;

import org.example.client.ChatWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame implements ServerView {
    private Server server;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JButton btnStart, btnStop;
    private JTextArea log;
    private JPanel panBottom;
    private JScrollPane scrollLog;

    public ServerWindow(Server server, int x, int y) {
        this.server = server;

        log = new JTextArea();
        scrollLog = new JScrollPane(log);

        setting(x, y);
        add(createBottomPanel(), BorderLayout.SOUTH);
        add(scrollLog);

        setVisible(true);
    }

    private Component createBottomPanel() {
        panBottom = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start server");
        btnStop = new JButton("Stop server");
        panBottom.add(btnStart);
        panBottom.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServer();
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });

        return panBottom;
    }

    private void setting(int x, int y) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(x, y);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
    }

    @Override
    public void stopServer() {
        server.stopServer();
    }

    @Override
    public void startServer() {
        server.startServer();
    }

    @Override
    public void printInLog(String text) {
        log.append(text);
    }
}
