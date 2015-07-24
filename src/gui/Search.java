/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import backtable.NewSearch;
import backtable.SearchContent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author lyz
 */
public class Search extends BasicPanel {

    private final JLabel option1, option2, option3, option4;
    private final JRadioButton point1, point2, point3, point4;
    private final JTextField entry1, entry2, entry3, entry4;
    private final JButton bt1, bt2;
    private final ButtonGroup pointGroup;
    MainFrame index;

    public Search(MainFrame index) {
        super(index);
        this.index = index;
        this.setLayout(null);
        //index.setTitle("搜索");
        bt1 = new JButton(new ImageIcon(SetUp.imageForSetButton));
        bt1.setBorder(null);
        bt1.addMouseListener(new CursorListener());
        bt2 = new JButton(new ImageIcon(SetUp.imageForSearchButton));
        bt2.addMouseListener(new CursorListener());
        bt2.addMouseListener(new Result());
        bt2.setBorder(null);
        //bt2.addActionListener();
        option1 = new JLabel("按作者搜索");
        option1.setFont(SetUp.GLOBAL_FONT);
        option1.setForeground(SetUp.FORE_COLOR);
        option2 = new JLabel("按类型搜索");
        option2.setFont(SetUp.GLOBAL_FONT);
        option2.setForeground(SetUp.FORE_COLOR);
        option3 = new JLabel("按文件名搜索");
        option3.setFont(SetUp.GLOBAL_FONT);
        option3.setForeground(SetUp.FORE_COLOR);
        option4 = new JLabel("按关键字搜索");
        option4.setFont(SetUp.GLOBAL_FONT);
        option4.setForeground(SetUp.FORE_COLOR);
        entry1 = new JTextField(15);
        entry1.addMouseListener(new EntryListener());
        entry2 = new JTextField(15);
        entry2.addMouseListener(new EntryListener());
        entry3 = new JTextField(15);
        entry3.addMouseListener(new EntryListener());
        entry4 = new JTextField(15);
        entry4.addMouseListener(new EntryListener());
        pointGroup = new ButtonGroup();
        point1 = new JRadioButton();
        point2 = new JRadioButton();
        point3 = new JRadioButton();
        point4 = new JRadioButton();
        pointGroup.add(point1);
        pointGroup.add(point2);
        pointGroup.add(point3);
        pointGroup.add(point4);
        point1.addMouseListener(new ActionLis());
        point2.addMouseListener(new ActionLis());
        point3.addMouseListener(new ActionLis());
        point4.addMouseListener(new ActionLis());
        point1.addMouseListener(new CursorListener());
        point2.addMouseListener(new CursorListener());
        point3.addMouseListener(new CursorListener());
        point4.addMouseListener(new CursorListener());
        //this.add(bt1);
        this.add(bt2);
        this.add(option1);
        this.add(option2);
        this.add(option3);
        this.add(option4);
        this.add(point1);
        this.add(point2);
        this.add(point3);
        this.add(point4);

        this.add(entry1);
        this.add(entry2);
        this.add(entry3);
        this.add(entry4);
        entry1.setVisible(true);
        point1.setSelected(true);
        entry2.setVisible(false);
        entry3.setVisible(false);
        entry4.setVisible(false);

        point1.setBounds(160, 173, 20, 20);
        point1.setOpaque(true);
        point1.setBackground(new Color(39, 158, 218));
        point2.setBounds(160, 223, 20, 20);
        point2.setOpaque(true);
        point2.setBackground(new Color(39, 158, 218));
        point3.setBounds(160, 273, 20, 20);
        point3.setOpaque(true);
        point3.setBackground(new Color(39, 158, 218));
        point4.setBounds(160, 323, 20, 20);
        point4.setOpaque(true);
        point4.setBackground(new Color(39, 158, 218));

        bt1.setBounds(240, 393, 50, 22);
        bt2.setBounds(340, 393, 50, 22);
        option1.setBounds(220, 173, 200, 20);
        option2.setBounds(220, 223, 200, 20);
        option3.setBounds(220, 273, 200, 20);
        option4.setBounds(220, 323, 200, 20);
        entry1.setBounds(350, 173, 200, 20);
        entry2.setBounds(350, 223, 200, 20);
        entry3.setBounds(350, 273, 200, 20);
        entry4.setBounds(350, 323, 200, 20);
        this.addReturnListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForSearchBackground);
        img.paintIcon(this, g, 0, 0);
    }

    @Override
    public void imageRepaint() {
        super.imageRepaint();
        this.repaint();
        bt1.setIcon(new ImageIcon(SetUp.imageForSetButton));
        bt2.setIcon(new ImageIcon(SetUp.imageForSearchButton));
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

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == point1) {
                entry1.setVisible(true);
                entry2.setVisible(false);
                entry3.setVisible(false);
                entry4.setVisible(false);
                entry1.setFocusable(true);
            } else if (e.getSource() == point2) {
                entry1.setVisible(false);
                entry2.setVisible(true);
                entry3.setVisible(false);
                entry4.setVisible(false);
                entry2.setFocusable(true);
            } else if (e.getSource() == point3) {
                entry1.setVisible(false);
                entry2.setVisible(false);
                entry3.setVisible(true);
                entry4.setVisible(false);
                entry3.setFocusable(true);
            } else if (e.getSource() == point4) {
                entry1.setVisible(false);
                entry2.setVisible(false);
                entry3.setVisible(false);
                entry4.setVisible(true);
                entry4.setFocusable(true);
            }
        }
    }

    class Result extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                ArrayList<String> result = new ArrayList<>();
                ResultPanel RESULT = null;
                for (JTextField entry : new JTextField[]{entry1, entry2, entry3, entry4}) {
                    if (entry.isVisible()) {
                        if (entry == entry4) {
                            SearchContent Re4 = new SearchContent(entry4.getText());
                            result.addAll(Re4.result);
                        }
                        if (entry == entry3) {
                            result.addAll(NewSearch.SearchTitle(entry3.getText()));
                        }
                        RESULT = new ResultPanel(entry.getText(), result);
                        break;
                    }
                }
                BasicPanel temp = new BasicPanel(index) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        ImageIcon img = new ImageIcon(SetUp.imageForSearchResultBackground);
                        img.paintIcon(this, g, 0, 0);
                    }
                };
                temp.returnButton.removeActionListener(temp.actionListener);
                temp.returnButton.addActionListener((ActionListener) -> {
                    MainFrame.cl.show(MainFrame.changeJPanel, "search");
                });
                RESULT.setBounds(130, 165, 600, 275);
                RESULT.setVisible(true);
                temp.add(RESULT);
                temp.setLayout(null);
                MainFrame.cl.addLayoutComponent(temp, "result");
                MainFrame.changeJPanel.add(temp);
                MainFrame.cl.show(MainFrame.changeJPanel, "result");
            } catch (Exception ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}