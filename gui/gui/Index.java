/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author 开发
 */
public class Index extends JPanel {

    public Index() {
        super();

        //以下为某Z的临时代码，QQ你重写的时候要干掉/重构这些
        this.setLayout(null);
        JButton sousuo = new JButton(new ImageIcon(SetUp.imageForSearchButton));  //按钮的具体功能参照图片名...
        sousuo.addMouseListener(new CursorListener());
        sousuo.setBounds(263, 348, 54, 28);
        sousuo.setBorder(null);
        sousuo.addActionListener((ActionEvent e) -> {
            MainFrame.cl.show(MainFrame.changeJPanel, "search");  //这个search单词不要改。
        });
        JButton sousuo2 = new JButton(new ImageIcon(SetUp.imageForShelfButton));
        sousuo2.addMouseListener(new CursorListener());
        sousuo2.setBounds(140, 215, 56, 28);
        sousuo2.setBorder(null);
        sousuo2.addActionListener((ActionEvent e) -> {
            MainFrame.cl.show(MainFrame.changeJPanel, "shelf");
        });
        JButton set = new JButton(new ImageIcon(SetUp.imageForSetButton));
        set.addMouseListener(new CursorListener());
        set.setBounds(390, 215, 54, 27);
        set.setBorder(null);
        set.addActionListener((ActionEvent e) -> {
            MainFrame.cl.show(MainFrame.changeJPanel, "set");
        });
        JButton about = new JButton(new ImageIcon(SetUp.imageForAboutButton));
        about.setBorder(null);
        about.addMouseListener(new CursorListener());
        about.setBounds(518, 348, 49, 26);
        about.addActionListener((ActionEvent e) -> {
            MainFrame.cl.show(MainFrame.changeJPanel, "about");
        });
        add(sousuo);
        add(sousuo2);
        add(set);
        add(about);
        //以上代码写的实在不忍直视，77你要好好重写。。
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForIndexBackground);
        img.paintIcon(this, g, 0, 0);
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
}
