/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

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
