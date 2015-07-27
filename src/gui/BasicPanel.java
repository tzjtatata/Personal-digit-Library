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
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author lyz
 */
public class BasicPanel extends JPanel {

    public final MainFrame index;
    protected JButton returnButton;
    protected ActionListener actionListener;

    public BasicPanel(MainFrame index) {
        this.index = index;  //好吧这个参数只是SetUp在用
        returnButton = new JButton(new ImageIcon(SetUp.imageForReturnToIndex));
        returnButton.setBounds(820, 80, 30, 30);
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.cl.show(MainFrame.changeJPanel, "index");
                SetUp.imageForCalenderHint = SetUp.imageForIndexBackground;
                index.imageRepaint();
            }
        };
        returnButton.addActionListener(actionListener);
        SwingUtilities.updateComponentTreeUI(this);
        returnButton.addMouseListener(new CursorListener());
        add(returnButton);
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
