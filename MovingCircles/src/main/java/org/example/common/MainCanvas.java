package org.example.common;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {
    private final CanvasRepaintListener controller;
    private long lastFrameTime;

    public MainCanvas(CanvasRepaintListener controller) {
        this.controller = controller;
        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        float deltaTime = (System.nanoTime() - lastFrameTime) * 0.000000001f;
        controller.onDrawFrame(this, g, deltaTime);
        lastFrameTime = System.nanoTime();
        repaint();
    }

    public float getLeft() {
        return 0f;
    }

    public float getRight() {
        return (float) controller.getWidth();
    }

    public float getTop() {
        return 0f;
    }

    public float getBottom() {
        return (float) controller.getHeight();
    }
}
