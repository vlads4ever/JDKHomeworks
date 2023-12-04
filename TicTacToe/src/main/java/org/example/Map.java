package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    private static final int DOT_PADDING = 5;

    private boolean isGameOver;
    private boolean isInitialized;

    private int gameOverType;
    private static final int STATE_DRAW = 0;  // Ничья
    private static final int STATE_WIN_HUMAN = 1;  // Игрок победил
    private static final int STATE_WIN_AI = 2;  // Компьютер победил


    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";

    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int fieldSizeY;
    private int fieldSizeX;
    private int winCount;  // Число победных фишек в ряд
    private char[][] field;

    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;

    Map() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        isInitialized = false;
    }

    private void update(MouseEvent e) {
        // нет смысла обрабатывать клики понеинициализированному полю или
        // по полю на, котором закончилась игра
        if (isGameOver || !isInitialized) return;

        // Расчитываем ячейку по координатам клика пользователя
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;

        // update
        if (checkEndGame (HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
        if (checkEndGame(AI_DOT, STATE_WIN_AI)) return;
        // end update
    }

    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        System.out.printf("Mode: %d; \nField Size: x=%d, y=%d; \nWin Length: %d",
                mode, fSzX, fSzY, wLen);
        fieldSizeX = fSzX;
        fieldSizeY = fSzY;
        winCount = wLen;

        // при старте новой игры игра перестаёт быть законченой, а поле становится инициализированным
        initMap();
        isGameOver = false;
        isInitialized = true;

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        //  рендеринг на неинициализированном поле не имеет смысла
        if (!isInitialized) return;

        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        g.setColor(Color.BLACK);
        for (int h = 0; h < fieldSizeY; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        repaint();

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }

        //render
        if (isGameOver) showMessageGameOver(g);
        // end render
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }

    // Заполняем массив поля пустыми ячейками. Его вызов замещен в метод старта новой игры.
    private void initMap() {
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    private void aiTurn() {
        // проверка на опережение на 2 хода
        int checkFieldsCount = winCount - 2;

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (checkHorizontalWin(x, y, HUMAN_DOT, checkFieldsCount)) {
                    if (isValidCell(x - 1, y) && isEmptyCell(x - 1, y)) {
                        field[y][x - 1] = AI_DOT;
                        break;
                    } else if (isValidCell(x + checkFieldsCount, y) && isEmptyCell(x + checkFieldsCount, y)) {
                        field[y][x + checkFieldsCount] = AI_DOT;
                        break;
                    }
                } else if (checkVerticalWin(x, y, HUMAN_DOT, checkFieldsCount)) {
                    if (isValidCell(x, y - 1) && isEmptyCell(x, y - 1)) {
                        field[y - 1][x] = AI_DOT;
                        break;
                    } else if (isValidCell(x, y + checkFieldsCount) && isEmptyCell(x, y + checkFieldsCount)) {
                        field[y + checkFieldsCount][x] = AI_DOT;
                        break;
                    }
                } else if (checkUpDiagonalWin(x, y, HUMAN_DOT, checkFieldsCount)) {
                    if (isValidCell(x - 1, y + 1) && isEmptyCell(x - 1, y + 1)) {
                        field[y + 1][x - 1] = AI_DOT;
                        break;
                    } else if (isValidCell(x + checkFieldsCount, y - checkFieldsCount) &&
                            isEmptyCell(x + checkFieldsCount, y - checkFieldsCount)) {
                        field[y - checkFieldsCount][x + checkFieldsCount] = AI_DOT;
                        break;
                    }
                } else if (checkDownDiagonalWin(x, y, HUMAN_DOT, checkFieldsCount)) {
                    if (isValidCell(x - 1, y - 1) && isEmptyCell(x - 1, y - 1)) {
                        field[y - 1][x - 1] = AI_DOT;
                        break;
                    } else if (isValidCell(x + checkFieldsCount, y + checkFieldsCount) &&
                            isEmptyCell(x + checkFieldsCount, y + checkFieldsCount)) {
                        field[y + checkFieldsCount][x + checkFieldsCount] = AI_DOT;
                        break;
                    }
                }
            }
        }

        // Если играть на опережение не получается, то компьютер ходит случайно
        randomAiTurn();
    }

    private void randomAiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    private boolean checkWin(int dot) {
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if (checkHorizontalWin(x, y, dot, winCount) ||
                        checkVerticalWin(x, y, dot, winCount) ||
                        checkUpDiagonalWin(x, y, dot, winCount) ||
                        checkDownDiagonalWin(x, y, dot, winCount)) return true;
            }
        }
        return false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы по горизонтали
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    private boolean checkHorizontalWin(int x, int y, int dot, int winCount){
        if (!isValidCell(x + winCount - 1, y)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y][x + i] != dot) return false;
        }
        return true;
    }

    /**
     * Проверка победы по вертикали
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    private boolean checkVerticalWin(int x, int y, int dot, int winCount){
        if (!isValidCell(x, y + winCount - 1)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y + i][x] != dot) return false;
        }
        return true;
    }

    /**
     * Проверка победы по диагонали вверх
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    private boolean checkUpDiagonalWin(int x, int y, int dot, int winCount){
        if (!isValidCell(x + winCount - 1, y - winCount + 1)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y - i][x + i] != dot) return false;
        }
        return true;
    }

    /**
     * Проверка победы по диагонали вниз
     * @param x
     * @param y
     * @param dot фишка игрока
     * @param winCount победное количество проверяемых фишек
     * @return
     */
    private boolean checkDownDiagonalWin(int x, int y, int dot, int winCount){
        if (!isValidCell(x + winCount - 1, y + winCount - 1)) return false;
        for (int i = 0; i < winCount; i++) {
            if (field[y + i][x + i] != dot) return false;
        }
        return true;
    }
}
