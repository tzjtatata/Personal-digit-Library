/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author lyz
 */
public class Search extends BasicPanel {

    private JLabel option1, option2, option3, option4;
    private JLabel point1, point2, point3, point4, backg1, backg2, backg3, backg4;
    private JTextField entry1, entry2, entry3, entry4;
    private JButton bt1, bt2;

    public Search(MainFrame index) {
        super(index);
        this.setLayout(null);
        //index.setTitle("搜索");
        bt1 = new JButton(new ImageIcon(SetUp.imageForSetButton));
        bt1.setBorder(null);
        bt1.addMouseListener(new CursorListener());
        bt2 = new JButton(new ImageIcon(SetUp.imageForSearchButton));
        bt2.addMouseListener(new CursorListener());
        bt2.setBorder(null);
        //bt2.addActionListener();
        option1 = new JLabel("按作者搜索");
        option1.setFont(SetUp.GLOBAL_FONT);
        option1.setForeground(new Color(39, 158, 218));
        option2 = new JLabel("按类型搜索");
        option2.setFont(SetUp.GLOBAL_FONT);
        option2.setForeground(new Color(39, 158, 218));
        option3 = new JLabel("按文件名搜索");
        option3.setFont(SetUp.GLOBAL_FONT);
        option3.setForeground(new Color(39, 158, 218));
        option4 = new JLabel("按关键字搜索");
        option4.setFont(SetUp.GLOBAL_FONT);
        option4.setForeground(new Color(39, 158, 218));
        entry1 = new JTextField(15);
        entry1.addMouseListener(new EntryListener());
        entry2 = new JTextField(15);
        entry2.addMouseListener(new EntryListener());
        entry3 = new JTextField(15);
        entry3.addMouseListener(new EntryListener());
        entry4 = new JTextField(15);
        entry4.addMouseListener(new EntryListener());
        point1 = new JLabel();
        point2 = new JLabel();
        point3 = new JLabel();
        point4 = new JLabel();
        backg1 = new JLabel();
        backg2 = new JLabel();
        backg3 = new JLabel();
        backg4 = new JLabel();
        point1.addMouseListener(new ActionLis());
        point2.addMouseListener(new ActionLis());
        point3.addMouseListener(new ActionLis());
        point4.addMouseListener(new ActionLis());
        backg1.addMouseListener(new ActionLis());
        backg2.addMouseListener(new ActionLis());
        backg3.addMouseListener(new ActionLis());
        backg4.addMouseListener(new ActionLis());
        backg1.addMouseListener(new CursorListener());
        backg2.addMouseListener(new CursorListener());
        backg3.addMouseListener(new CursorListener());
        backg4.addMouseListener(new CursorListener());
        point1.addMouseListener(new CursorListener());
        point2.addMouseListener(new CursorListener());
        point3.addMouseListener(new CursorListener());
        point4.addMouseListener(new CursorListener());
        this.add(bt1);
        this.add(bt2);
        this.add(option1);
        this.add(option2);
        this.add(option3);
        this.add(option4);
        this.add(point1);
        this.add(point2);
        this.add(point3);
        this.add(point4);
        this.add(backg1);
        this.add(backg2);
        this.add(backg3);
        this.add(backg4);

        this.add(entry1);
        this.add(entry2);
        this.add(entry3);
        this.add(entry4);
        entry1.setVisible(false);
        entry2.setVisible(false);
        entry3.setVisible(false);
        entry4.setVisible(false);

        point1.setBounds(160, 180, 5, 5);
        point1.setOpaque(true);
        point1.setBackground(new Color(39, 158, 218));
        point2.setBounds(160, 230, 5, 5);
        point2.setOpaque(true);
        point2.setBackground(new Color(39, 158, 218));
        point3.setBounds(160, 280, 5, 5);
        point3.setOpaque(true);
        point3.setBackground(new Color(39, 158, 218));
        point4.setBounds(160, 330, 5, 5);
        point4.setOpaque(true);
        point4.setBackground(new Color(39, 158, 218));

        backg1.setBounds(150, 170, 30, 30);
        backg1.setOpaque(true);
        backg1.setBackground(new Color(215, 217, 218));
        backg2.setBounds(150, 220, 30, 30);
        backg2.setOpaque(true);
        backg2.setBackground(new Color(215, 217, 218));
        backg3.setBounds(150, 270, 30, 30);
        backg3.setOpaque(true);
        backg3.setBackground(new Color(215, 217, 218));
        backg4.setBounds(150, 320, 30, 30);
        backg4.setOpaque(true);
        backg4.setBackground(new Color(215, 217, 218));

        bt1.setBounds(240, 393, 50, 22);
        bt2.setBounds(380, 393, 50, 22);
        option1.setBounds(220, 173, 200, 20);
        option2.setBounds(220, 223, 200, 20);
        option3.setBounds(220, 273, 200, 20);
        option4.setBounds(220, 323, 200, 20);
        entry1.setBounds(350, 173, 200, 20);
        entry2.setBounds(350, 223, 200, 20);
        entry3.setBounds(350, 273, 200, 20);
        entry4.setBounds(350, 323, 200, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForSearchBackground);
        img.paintIcon(this, g, 0, 0);
    }

    class EntryListener extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    class CursorListener extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @SuppressWarnings("deprecation")
        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    class ActionLis extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == point1 || e.getSource() == backg1) {
                if (entry1.isVisible() == true) {
                    entry1.setVisible(false);
                    point1.setBackground(Color.gray);
                } else {
                    entry1.setVisible(true);
                    point1.setBackground(Color.WHITE);
                }
            }
            if (e.getSource() == point2 || e.getSource() == backg2) {
                if (entry2.isVisible() == true) {
                    entry2.setVisible(false);
                    point2.setBackground(Color.gray);
                } else {
                    entry2.setVisible(true);
                    point2.setBackground(Color.WHITE);
                }
            }
            if (e.getSource() == point3 || e.getSource() == backg3) {
                if (entry3.isVisible() == true) {
                    entry3.setVisible(false);
                    point3.setBackground(Color.gray);
                } else {
                    entry3.setVisible(true);
                    point3.setBackground(Color.WHITE);
                }
            }
            if (e.getSource() == point4 || e.getSource() == backg4) {
                if (entry4.isVisible() == true) {
                    entry4.setVisible(false);
                    point4.setBackground(Color.gray);
                } else {
                    entry4.setVisible(true);
                    point4.setBackground(Color.WHITE);
                }
            }
        }
    }
}
