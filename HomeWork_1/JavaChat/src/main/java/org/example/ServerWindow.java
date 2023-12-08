package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JButton btnStart, btnStop;
    private JTextArea log;
    private JPanel panBottom;
    private JScrollPane scrollLog;
    private boolean isServerWorking;

    private List<ChatWindow> connectedUsers;

    ServerWindow(int x, int y) {
        connectedUsers = new ArrayList<>();

        btnStart = new JButton("Start server");
        btnStop = new JButton("Stop server");
        log = new JTextArea();
        scrollLog = new JScrollPane(log);
        panBottom = new JPanel(new GridLayout(1, 2));
        isServerWorking = false;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(x, y);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        panBottom.add(btnStart);
        panBottom.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                log.append("Server is stopped.\n");
                System.out.println("Server stopped");
                if (saveLog()) {
                    log.append("Log was saved.\n");
                    System.out.println("Log was saved.");
                } else {
                    log.append("Log was not saved.\n");
                    System.out.println("Log was not saved.");
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                loadLog();
                log.append("Server is started.\n");
                System.out.println("Server started");
            }
        });

        add(panBottom, BorderLayout.SOUTH);
        add(scrollLog);

        setVisible(true);
    }

    public boolean postMessage(String message) {
        if (isServerWorking) {
            log.append(message + "\n");
            for (ChatWindow chatWindow: connectedUsers) {
                chatWindow.writeLog(message);
            }
            System.out.println("Message on server: " + message);
            return true;
        } else {
            log.append("Accept message but server is not started.\n");
            System.out.println("Accept message but server is not started.");
            return false;
        }
    }

    public boolean connectUser(ChatWindow chatWindow) {
        if (isServerWorking) {
            connectedUsers.add(chatWindow);
            log.append("User " + chatWindow.getLogin() + " is connected.\n");
            chatWindow.writeLog(log.getText());
            chatWindow.writeLog("Connected to server");
            System.out.println("User " + chatWindow.getLogin() + " is connected.\n");
            return true;
        } else {
            log.append("Try to log in but server is not started.\n");
            System.out.println("Try to log in but server is not started.");
            return false;
        }
    }

    private boolean saveLog() {
        try (FileWriter writer = new FileWriter("log.txt", false))
        {
            for (String line: log.getText().split("\\n")) {
                writer.write(line + "\n");
            }

            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void loadLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader("log.txt")) {
            int c;
            while((c = reader.read())!=-1) {
                stringBuilder.append((char)c);
            }

            log.append(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
