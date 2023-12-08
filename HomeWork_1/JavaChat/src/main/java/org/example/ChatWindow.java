package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private ServerWindow serverWindow;
    private ChatWindow thisChatWindow;
    private boolean isConnectedToServer;


    private final JTextArea log;
    private JScrollPane scrollLog;
    private JPanel panelTop, panelBottom;
    private JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    private JPasswordField tfPassword;
    private JButton btnLogin, btnSend;

    ChatWindow(ServerWindow serverWindow, int x, int y) {
        this.serverWindow = serverWindow;
        thisChatWindow = this;
        isConnectedToServer = false;

        panelTop = new JPanel(new GridLayout(2,3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan");
        tfPassword = new JPasswordField("12345678");
        btnLogin = new JButton("Login");

        panelBottom = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        btnSend = new JButton("Send");

        log = new JTextArea();
        scrollLog = new JScrollPane(log);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(x, y);
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
                if (!isConnectedToServer) {
                    if (serverWindow.connectUser(thisChatWindow)) {
                        isConnectedToServer = true;
                        System.out.println("You have connect to server.");
                    } else {
                        log.append("Server does not answer.\n");
                        System.out.println("Server does not answer.");
                    }
                } else {
                    log.append("You already have connect to server.\n");
                    System.out.println("You already have connect to server.");
                }

            }
        });
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        tfMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isConnectedToServer) {
                    if (serverWindow.postMessage(tfLogin.getText() + ": " + tfMessage.getText())) {
                        tfMessage.setText("");
                        System.out.println("Message on client: " + tfLogin.getText() + ": " + tfMessage.getText());
                    } else {
                        log.append("Server does not answer.\n");
                        System.out.println("Server does not answer.");
                    }

                } else {
                    log.append("No connect to server.\n");
                    System.out.println("No connect to server.");
                }
            }
        });
        panelBottom.add(btnSend, BorderLayout.EAST);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isConnectedToServer) {
                    serverWindow.postMessage(tfLogin.getText() + ": " + tfMessage.getText());
                    tfMessage.setText("");
                    System.out.println("Message on client: " + tfLogin.getText() + ": " + tfMessage.getText());
                } else {
                    log.append("No connect to server.\n");
                    System.out.println("No connect to server.");
                }
            }
        });

        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        add(scrollLog);

        setVisible(true);
    }

    public void writeLog(String message) {
        log.append(message + "\n");
    }

    public String getLogin() {
        return tfLogin.getText();
    }
}
