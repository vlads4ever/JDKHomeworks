package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;

    JButton btnStart = new JButton("Start new game");
    SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);  // Расположить окно настроек относительно основного окна по центру
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // При нажатии на кнопку Старт запускается окно игры с игровыми параметрами
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(0, 3, 3, 3);
                setVisible(false);
            }
        });

        add(btnStart);  // В данном случае кнопка по-умодчанию будет по центру размером во все окно
    }
}
