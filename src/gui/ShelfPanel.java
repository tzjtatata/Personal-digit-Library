/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

/**
 *
 * @author lyz
 */
public class ShelfPanel extends BasicPanel {

    private HashMap<String,ArrayList<String>> UserClass,Class;
    static ResultPanel[] subjectShow = new ResultPanel[10000];
    private JLabel[] subjectLabel = new JLabel[10000];
    private JLabel left, right;
    private int nowPage = 0, firstPage = 0, len = 0;
    private final int LEN = 5;
    private HashMap<Integer,JLabel> label = new HashMap<>();
    File ujson = new File("setFile/UserClass.json");

    public ShelfPanel(MainFrame index) throws Exception {
        super(index);
        if (!ujson.exists()) {
            ujson.createNewFile();
        }
        else {
            String jsonString;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ujson), "UTF-8"))) {
                jsonString = br.readLine();
            }
            UserClass = JSON.parseObject(jsonString, new TypeReference<HashMap<String,ArrayList<String>>>() { });
        }
        if (UserClass == null) UserClass = new HashMap<String,ArrayList<String>>();
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
        setCategory();
        setContent(nowPage);
        this.addReturnListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForShelfBackground);
        img.paintIcon(this, g, 0, 0);
    }

    private void setCategory() {
        for (int i = 0; i < len; i++) {
            subjectLabel[i] = new JLabel(subjectShow[i].getName());
            label.put(i, subjectLabel[i]);
            subjectLabel[i].setFont(SetUp.GLOBAL_FONT);
            subjectLabel[i].setForeground(SetUp.SHELF_COLOR);  //可能要改
            subjectLabel[i].addMouseListener(new CursorListener());
            subjectLabel[i].addMouseListener(new ChangePage(i));
            subjectLabel[i].setVisible(false);
            this.add(subjectLabel[i]);
        }
        cCategory(firstPage,len>LEN?LEN:len);
    }

    protected void hideLabel(int i) {
        subjectLabel[i].setVisible(false);
    }

    private void getContent() throws Exception {
        String[] boy = new String[2];
        String str;
        File cjson = new File("setFile/class.json");
        String jsonString;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cjson), "UTF-8"))) {
            jsonString = br.readLine();
        }
        Class = JSON.parseObject(jsonString, new TypeReference<HashMap<String,ArrayList<String>>>() { });
        if (!UserClass.isEmpty())
            for (String category:UserClass.keySet()) {
                subjectShow[len] = new ResultPanel(category, UserClass.get(category), "shelf");
                len++;
            }
        if (!Class.isEmpty())
            for (String category:Class.keySet()) {
                subjectShow[len] = new ResultPanel(category, Class.get(category), "shelf");
                len++;
            }
    }

    private void setContent(int n) {
        label.get(n).setForeground(Color.BLACK);
        subjectShow[n].setBounds(130, 165, 500, 325);
        subjectShow[n].setVisible(true);
        this.add(subjectShow[n]);
    }

    protected void hideContent(int n) {
        label.get(n).setForeground(SetUp.SHELF_COLOR);
        subjectShow[n].setVisible(false);
    }

    @Override
    public void imageRepaint() {
        super.imageRepaint();
        this.repaint();
    }

    void changeColor() {
        this.setOpaque(false);
        for (JLabel subjectJLabel : subjectLabel) {
            if (subjectJLabel != null) {
                subjectJLabel.setForeground(SetUp.SHELF_COLOR);
                subjectJLabel.setOpaque(false);
            }
        }
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
                temp = (len - LEN) > 0 ? len - LEN : 0;
            } else if (temp < 0) {
                temp = 0;
            }
            cCategory(temp,len>LEN?LEN:len);
        }
    }
    public void cCategory(int n,int range) {
         for (int i = 0;i<range;i++) {
            subjectLabel[firstPage + i].setVisible(false);
        }
        for (int i = 0;i<range;i++) {
            subjectLabel[n+i].setBounds(270 + i * 58, 133, 55, 28);
            subjectLabel[n+i].setVisible(true);
        }
        firstPage = n;
    }
}
