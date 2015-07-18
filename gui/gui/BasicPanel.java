/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author lyz
 */
public class BasicPanel extends JPanel {

    private final MainFrame index;
    private JButton returnButton;

    public BasicPanel(MainFrame index) {
        this.index = index;  //好吧这个参数只是SetUp在用
        addToIndex(this);
    }

    /**
     * 将JPanel组件添加到卡片布局中，并为其加上返回主页的按钮
     *
     * @param self 需要添加到卡片布局中的JPanel
     * @param index 需要返回的主页
     */
    private void addToIndex(JPanel self) {
        returnButton = new JButton(new ImageIcon(SetUp.imageForReturnToIndex));
        returnButton.setBounds(820, 80, 30, 30);
        returnButton.addActionListener((ActionEvent e) -> {
            MainFrame.cl.show(MainFrame.changeJPanel, "index");
        });
        returnButton.addMouseListener(new CursorListener());
        self.add(returnButton);
    }

    /**
     * 并不是很成功。。
     */
    protected void addReturnListener() {
        this.setFocusable(true);
        for (Component c : this.getComponents()) {
            c.addKeyListener(new ReturnListener());
        }
    }

    /**
     * 按esc返回主页
     */
    protected class ReturnListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {
                MainFrame.cl.show(MainFrame.changeJPanel, "index");
            }
        }
    }

    class CursorListener extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    //重绘图片
    public void imageRepaint() {
        returnButton.setIcon(new ImageIcon(SetUp.imageForReturnToIndex));
    }
}
