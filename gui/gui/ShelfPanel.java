/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 *
 * @author lyz
 */
public class ShelfPanel extends BasicPanel {

    static ResultPanel[] subjectShow = new ResultPanel[100];
    private JLabel[] subjectLabel = new JLabel[5];
    private JLabel left, right;
    private int nowPage = 0, firstPage = 0, len = 0;
    private final int LEN = 5;

    public ShelfPanel(MainFrame index) throws Exception {
        super(index);
        this.setLayout(null);
        left = new JLabel();
        left.setBounds(250, 138, 20, 20);
        right = new JLabel();
        right.setBounds(555, 138, 20, 20);
        left.addMouseListener(new changeSubject(-1));
        left.addMouseListener(new CursorListener());
        right.addMouseListener(new CursorListener());
        right.addMouseListener(new changeSubject(1));
        this.add(left);
        this.add(right);
        getContent();
        setCategory(firstPage);
        setContent(nowPage);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForShelfBackground);
        img.paintIcon(this, g, 0, 0);
    }

    private void setCategory(int a) {
        //System.out.println(a);
        for (int i = 0; i < LEN; i++) {
            if (subjectLabel[i] != null) {
                subjectLabel[i].setVisible(false);
            }
            subjectLabel[i] = new JLabel(subjectShow[a + i].getName());
            subjectLabel[i].setFont(SetUp.GLOBAL_FONT);
            subjectLabel[i].setBounds(270 + i * 58, 133, 55, 28);
            subjectLabel[i].addMouseListener(new CursorListener());
            subjectLabel[i].addMouseListener(new ChangePage(a + i));
            this.add(subjectLabel[i]);
        }
    }

    protected void hideLabel(int i) {
        subjectLabel[i].setVisible(false);
    }

    private void getContent() throws Exception {
        String[] boy = new String[2];
        String str;
        File f = new File("gui/backtable/class.pdl");
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (br.ready()) {
            str = br.readLine();
            boy = str.split("/");
            subjectShow[len] = new ResultPanel(boy[0], boy[1].split(","));
            len++;
        }
    }

    private void setContent(int n) {
        subjectShow[n].setBounds(130, 165, 500, 275);
        subjectShow[n].setVisible(true);
        this.add(subjectShow[n]);
    }

    protected void hideContent(int n) {
        subjectShow[n].setVisible(false);
    }

    @Override
    public void imageRepaint() {
        super.imageRepaint();
        this.repaint();
    }

    class ChangePage extends MouseAdapter {

        private final int id;

        public ChangePage(int id) {
            super();
            this.id = id;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            hideContent(nowPage);
            nowPage = id;
            setContent(id);
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

    class changeSubject extends MouseAdapter {

        private int change;

        public changeSubject(int change) {
            super();
            this.change = change;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int temp;
            temp = firstPage + change;
            if (temp + LEN >= len) {
                firstPage = len - LEN;
            } else if (temp < 0) {
                firstPage = 0;
            } else {
                firstPage = temp;
            }
            setCategory(firstPage);
        }
    }
}
