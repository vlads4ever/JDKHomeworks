package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    ServerWindow serverWindow;

    private final JTextArea log;
    private JScrollPane scrollLog;
    private JPanel panelTop, panelBottom;
    private JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    private JPasswordField tfPassword;
    private JButton btnLogin, btnSend;



    ChatWindow(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;

        panelTop = new JPanel(new GridLayout(2,3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan_Fedorovich");
        tfPassword = new JPasswordField("12345678");
        btnLogin = new JButton("Login");

        panelBottom = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        btnSend = new JButton("Send");

        log = new JTextArea();
        scrollLog = new JScrollPane(log);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Log in to server");
            }
        });
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        tfMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Message on client: " + tfMessage.getText());
                serverWindow.postMessage(tfMessage.getText());
                tfMessage.setText("");
            }
        });
        panelBottom.add(btnSend, BorderLayout.EAST);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Message on client: " + tfMessage.getText());
                serverWindow.postMessage(tfMessage.getText());
                tfMessage.setText("");
            }
        });

        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        add(scrollLog);

        setVisible(true);

    }

}
