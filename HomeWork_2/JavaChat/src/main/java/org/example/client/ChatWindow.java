package org.example.client;

import org.example.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ChatWindow extends JFrame implements ClientView{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private Client client;
    private ChatWindow thisChatWindow;


    private JTextArea log;
    private JScrollPane scrollLog;
    private JPanel panelTop, panelBottom;
    private JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    private JPasswordField tfPassword;
    private JButton btnLogin, btnSend;

    public ChatWindow(Client client, int x, int y) {
        this.client = client;
        thisChatWindow = this;

        setting(x, y);
        add(createTopPanel(), BorderLayout.NORTH);
        add(createBottomPanel(), BorderLayout.SOUTH);
        log = new JTextArea();
        log.setEditable(false);
        scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);
    }

    private Component createBottomPanel() {
        panelBottom = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        btnSend = new JButton("Send");

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        tfMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client.postMessage(tfLogin.getText() + ": " + tfMessage.getText())) {
                    tfMessage.setText("");
                    System.out.println("Message on client: " + tfLogin.getText() + ": " + tfMessage.getText());
                } else {
                    log.append("Server does not answer.\n");
                    System.out.println("Server does not answer.");
                }
            }
        });
        panelBottom.add(btnSend, BorderLayout.EAST);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client.postMessage(tfLogin.getText() + ": " + tfMessage.getText())) {
                    tfMessage.setText("");
                    System.out.println("Message on client: " + tfLogin.getText() + ": " + tfMessage.getText());
                } else {
                    log.append("Server does not answer.\n");
                    System.out.println("Server does not answer.");
                }
            }
        });

        return panelBottom;
    }

    private Component createTopPanel() {
        panelTop = new JPanel(new GridLayout(2,3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan");
        tfPassword = new JPasswordField("12345678");
        btnLogin = new JButton("Login");

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.connectUser(thisChatWindow);
            }
        });

        return panelTop;
    }

    private void setting(int x, int y) {
        //При закрытии окна, оно скрывается и приложение не останавливается
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocation(x, y);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");
    }

    // Обработчик событий окна (закрытия)
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            client.disconnectFromServer();
        }
    }

    @Override
    public void writeLog(String text) {
        log.append(text + "\n");
    }

    @Override
    public String getLogin() {
        return tfLogin.getText();
    }

}
