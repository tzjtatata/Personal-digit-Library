package gui;

import com.sun.javafx.runtime.VersionInfo;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultPanel extends JPanel {

    private HashMap<Integer, String> JLHashMap;
    private ArrayList<String> selectedBook = new ArrayList<>();
    private ArrayList<JLabel> labelListJLabels = new ArrayList<>();
    private JLabel labelHintJLabel = new JLabel();
    private JRadioButton[] jRadioButtons = new JRadioButton[20];
    private JLabel next, front, tail, foreward;
    private ArrayList<JLabel> Page = new ArrayList<JLabel>();
    private int nowPage = 0;
    private int pagelength = 10, length = 10;
    private int countpage = 0;
    private static int startwidth = 0, startheight = -30, width = 400, height = 30;
    private int now = 0;
    private int size;
    private String name;

    /*protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     ImageIcon img = new ImageIcon("gui/source/搜索结果背景.png");
     img.paintIcon(this, g, 0, 0);
     }*/
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 元神请看这里！
     *
     * @param str
     * @param result
     * @param where 为了加长搜索结果的显示，所以加了个这个，where为"shelf"或者"search"
     */
    public ResultPanel(String str, ArrayList<String> result, String where) {
        if (where.equals("search")) {
            width += 200;
        }
        JLabel temLabel;
        this.name = str;
        int i;
        JLHashMap = new HashMap<>();
        this.setLayout(null);
        this.setOpaque(false);
        addMove();
        // 设定每页显示的结果序号
        for (i = 0; i < pagelength; i++) {
            if (jRadioButtons[i] == null) {
                jRadioButtons[i] = new JRadioButton();
            }
            jRadioButtons[i].setBounds(startwidth, startheight + height * (i + 1), height, height); //用于项目查找的注释
            jRadioButtons[i].setOpaque(false);
            //jRadioButtons[i].setBackground(new Color(39, 158, 218));
            jRadioButtons[i].setVisible(false);
            this.add(jRadioButtons[i]);
        }
        //判定是否有结果，并显示结果
        //show函数用于显示第n+1页的结果
        size = result.size();
        for (i = 0; i < size; i++) {
            JLabel temp = new JLabel();
            temp.setText(result.get(i));
            JLHashMap.put(temp.hashCode(), temp.getText());
            temp.setFont(SetUp.SHELF_FONT);
            this.add(temp);
            labelListJLabels.add(temp);
            //判定页数，并设定表现页数的标签
        }
        do {
            temLabel = new JLabel();
            Page.add(temLabel);
            Page.get(countpage).addMouseListener(new CursorListener());
            Page.get(countpage).setText("" + (countpage + 1));
            Page.get(countpage).setFont(SetUp.GLOBAL_FONT);
            Page.get(countpage).setVisible(false);
            Page.get(countpage).addMouseListener(new ChangePage());
            this.add(Page.get(countpage));
            countpage++;
        } while (size / pagelength >= countpage && countpage * pagelength != size);
        //length = countpage;
        move(0);
        show(0);
        this.add(labelHintJLabel);
    }

    /**
     * Z神的改颜色！耶！
     */
    public void changeColor() {
        Page.get(now).setForeground(SetUp.SPECIAL_COLOR);
    }

    public void show(int n) {
        int i, temp;
        for (i = 0; i < pagelength; i++) {
            temp = pagelength * n + i;
            if (labelListJLabels.size() > temp) {
                labelListJLabels.get(temp).addMouseListener(new ShowAdapter());
                labelListJLabels.get(temp).addMouseListener(new CursorListener());
                labelListJLabels.get(temp).setFont(SetUp.SHELF_FONT);
                labelListJLabels.get(temp).setBounds(startwidth + height, startheight + height * (i + 1), width, height);
                labelListJLabels.get(temp).setVisible(true);
                jRadioButtons[i].addActionListener(new Selected(labelListJLabels.get(temp).getText()));
                jRadioButtons[i].setVisible(true);
            }
        }
        Page.get(n).setForeground(SetUp.SPECIAL_COLOR);
    }

    public void hide(int n) {
        int i, temp;
        Page.get(n).setForeground(SetUp.FORE_COLOR);
        for (i = 0; i < pagelength; i++) {
            temp = pagelength * n + i;
            if (temp < size) {
                labelListJLabels.get(temp).setFont(SetUp.SHELF_FONT);
                labelListJLabels.get(temp).setVisible(false);
            }
            jRadioButtons[i].setVisible(false);
            jRadioButtons[i].setSelected(false);
        }
    }

    public void move(int x) {
        int i, len, size;
        size = Page.size();
        len = size >= (nowPage + length) ? length : size;
        for (i = 0; i < len; i++) {
            Page.get(nowPage + i).setVisible(false);
        }
        nowPage = x;
        len = size >= (nowPage + length) ? length : size;
        for (i = 0; i < len; i++) {
            Page.get(x + i).setBounds(startwidth + height * 3 + (height * i), 10 * height, height, height);
            Page.get(x + i).setVisible(true);
        }
        next.setBounds(startwidth + height * 3 + (height * len), 10 * height, height, height);
        tail.setBounds(startwidth + height * 4 + (height * len), 10 * height, height, height);
        front.setBounds(startwidth + height, 10 * height, height, height);
        foreward.setBounds(startwidth + height * 2, 10 * height, height, height);
    }

    public void addMove() {
        next = new JLabel(">");
        foreward = new JLabel("<");
        next.addMouseListener(new movePage(1));
        foreward.addMouseListener(new movePage(-1));
        foreward.addMouseListener(new CursorListener());
        next.addMouseListener(new CursorListener());
        this.add(next);
        this.add(foreward);
        tail = new JLabel(">>");
        front = new JLabel("<<");
        tail.addMouseListener(new movePage(2));
        front.addMouseListener(new movePage(3));
        front.addMouseListener(new CursorListener());
        tail.addMouseListener(new CursorListener());
        this.add(front);
        this.add(tail);
    }

    public ArrayList<String> getSelectedBook() {
        return selectedBook;
    }

    class ChangePage extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel I = (JLabel) e.getSource();
            int temp = Integer.parseInt(I.getText()) - 1;
            hide(now);
            show(temp);
            now = temp;
        }
    }

    class ShowAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent me) {
            String cmd = "cmd.exe /c start " + JLHashMap.get(me.getSource().hashCode());
            Runtime run = Runtime.getRuntime();
            try {
                run.exec(cmd);
            } catch (IOException ex) {
                Logger.getLogger(ResultPanel.class.getName()).log(Level.SEVERE, null, ex);
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

    class movePage extends MouseAdapter {

        int change;

        public movePage(int change) {
            this.change = change;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int temp, t;
            t = Page.size() - length;
            if (change == 2) {
                temp = t > 0 ? t : 0;
            } else if (change == 3) {
                temp = 0;
            } else {
                temp = nowPage + change;
                if (temp + length > Page.size()) {
                    temp = Page.size() - length;
                    temp = temp > 0 ? temp : 0;
                } else if (temp < 0) {
                    temp = 0;
                }
            }
            move(temp);
        }
    }

    class Selected implements ActionListener {

        private String book;
        private int flag = 0;

        public Selected(String str) {
            this.book = str;
        }

        public void actionPerformed(ActionEvent e) {
            if (flag == 0) {
                selectedBook.add(book);
            } else {
                selectedBook.remove(book);
            }
            flag = 1 - flag;
            System.out.println(selectedBook);
        }
    }
}
