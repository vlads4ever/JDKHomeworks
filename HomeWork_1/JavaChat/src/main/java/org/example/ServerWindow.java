package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JButton btnStart, btnStop;
    private JTextArea log;
    private JPanel panBottom;
    private boolean isServerWorking;

    ServerWindow() {
        btnStart = new JButton("Start server");
        btnStop = new JButton("Stop server");
        log = new JTextArea();
        panBottom = new JPanel(new GridLayout(1, 2));
        isServerWorking = false;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);

        panBottom.add(btnStart);
        panBottom.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                System.out.println("Server stopped");
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                System.out.println("Server started");
            }
        });

        add(panBottom, BorderLayout.SOUTH);
        add(log);

        setVisible(true);
    }

    public void postMessage(String message) {
        if (isServerWorking) {
            log.append(message);
            System.out.println("Message on server: " + message);
        } else System.out.println("Server is not started");
    }
}
