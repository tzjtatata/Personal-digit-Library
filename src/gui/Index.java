/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 开发
 */
public class Index extends JPanel {

    private final JLabel search, shelf, about, set;
//    private final CalendarDate calendar;
    private final ZCalendar calendar;

    public Index(MainFrame main) throws Exception {
        super();
        this.setLayout(null);
        search = new JLabel("搜索", JLabel.CENTER);
        shelf = new JLabel("书架", JLabel.CENTER);
        about = new JLabel("关于", JLabel.CENTER);
        set = new JLabel("设置", JLabel.CENTER);
//        calendar = new CalendarDate();
        calendar = new ZCalendar();

        search.setBounds(263, 348, 54, 28);
        search.setBorder(null);
        shelf.setBounds(140, 215, 56, 28);
        shelf.setBorder(null);
        set.setBounds(390, 215, 54, 27);
        set.setBorder(null);
        about.setBounds(518, 348, 49, 26);
        about.setBorder(null);
        calendar.setBounds(642, 178, 200, 330);

        add(search);
        add(shelf);
        add(set);
        add(about);
        add(calendar);

        search.addMouseListener(new CursorListener());
        shelf.addMouseListener(new CursorListener());
        set.addMouseListener(new CursorListener());
        about.addMouseListener(new CursorListener());
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cl.show(MainFrame.changeJPanel, "search");  //这个search单词不要改。
                SetUp.imageForCalenderHint = SetUp.imageForSearchBackground;
                main.imageRepaint();
            }
        });
        shelf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cl.show(MainFrame.changeJPanel, "shelf");
                SetUp.imageForCalenderHint = SetUp.imageForShelfBackground;
                main.imageRepaint();
            }
        });
        set.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cl.show(MainFrame.changeJPanel, "set");
                SetUp.imageForCalenderHint = SetUp.imageForSetBackground;
                main.imageRepaint();
            }
        });
        about.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.cl.show(MainFrame.changeJPanel, "about");
                SetUp.imageForCalenderHint = SetUp.imageForAboutBackground;
                main.imageRepaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForIndexBackground);
        img.paintIcon(this, g, 0, 0);
    }

    public void imageRepaint() {
        this.repaint();
        calendar.imageRepaint();
    }

    void changeCalendarColor() {
        calendar.changeColor();
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
