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
import javax.swing.*;
import java.awt.Cursor;
import java.awt.TextArea;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lyz
 */
public class ShelfPanel extends BasicPanel {

    private static JTextArea noteArea;  //帅z的笔记
    private HashMap<String, ArrayList<String>> UserClass, Class;
    static ResultPanel[] subjectShow = new ResultPanel[10000];
    private JLabel[] subjectLabel = new JLabel[10000];
    private JLabel left, right;
    private int nowPage = 0, firstPage = 0, len = 0;
    private final int LEN = 5;
    private HashMap<Integer, JLabel> label = new HashMap<>();
    private JLabel addJLabel, menuJLabel;
    private File ujson = new File("setFile/UserClass.json");
    private File cjson = new File("setFile/class.json");
    private JDialog classChoser = new JDialog();
    private JPopupMenu popupMenu;
    private JMenuItem menu1, menu2;
    private ClassChooser2 cc2;
    private JMenu moveClass;
    private ArrayList<JMenuItem> cateItems;

    public ShelfPanel(MainFrame index, int nowpage) throws Exception {
        super(index);
        nowPage = nowpage;
        popupMenu = new JPopupMenu();
        // 增加菜单项到菜单上
        menu1 = new JMenuItem("删除选定书籍");
        menu1.addActionListener(new deleteBook());
        menu2 = new JMenuItem("将选定书籍移至...");
        menu2.addActionListener(new moveBook());
        popupMenu.add(menu1);
        //popupMenu.add(menu2);
        if (!ujson.exists()) {
            ujson.createNewFile();
        } else {
            String jsonString;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ujson), "UTF-8"))) {
                jsonString = br.readLine();
            }
            UserClass = JSON.parseObject(jsonString, new TypeReference<HashMap<String, ArrayList<String>>>() {
            });
        }
        if (UserClass == null) {
            UserClass = new HashMap<String, ArrayList<String>>();
        }
        addJLabel = new JLabel(new ImageIcon(SetUp.imageForAddClass));
        menuJLabel = new JLabel(new ImageIcon(SetUp.imageForMenu));
        addJLabel.setBounds(606, 133, 25, 26);
        menuJLabel.setBounds(631, 133, 25, 26);
        addJLabel.addMouseListener(new CursorListener());
        menuJLabel.addMouseListener(new CursorListener());
        addJLabel.addMouseListener(new AddClass());
        menuJLabel.addMouseListener(new showMenu());
        add(addJLabel);
        add(menuJLabel);
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

        //帅z的笔记以及菜单
        noteArea = new JTextArea();
        noteArea.setBounds(674, 162, 174, 353);
        noteArea.setOpaque(false);
        noteArea.setWrapStyleWord(true);
        noteArea.setLineWrap(true);
        ArrayList<String> cateArrayList = new ArrayList(Class.keySet());
        cateArrayList.addAll(UserClass.keySet());
        moveClass = new JMenu("移动选定书籍至");
        cateArrayList.stream().map((cateArrayList1) -> {
            JMenuItem item = new JMenuItem(cateArrayList1);
            item.addActionListener((ActionEvent e) -> {
                ClassChooser2.category = cateArrayList1;
                ShelfPanel.this.movebooks();
            });
            return item;
        }).forEach((item) -> {
            moveClass.add(item);
        });
        popupMenu.add(moveClass);
        //
        JScrollPane textPane = new JScrollPane(noteArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textPane.setBounds(noteArea.getBounds());
        textPane.setOpaque(false);
        textPane.setBorder(null);
        textPane.getViewport().setBorder(null);
        textPane.getViewport().setOpaque(false);
        add(textPane);
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
        cCategory(firstPage, len > LEN ? LEN : len);
    }

    protected void hideLabel(int i) {
        subjectLabel[i].setVisible(false);
    }

    private void getContent() throws Exception {
        String[] boy = new String[2];
        String str;
        String jsonString;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cjson), "UTF-8"))) {
            jsonString = br.readLine();
        }
        Class = JSON.parseObject(jsonString, new TypeReference<HashMap<String, ArrayList<String>>>() {
        });
        if (!UserClass.isEmpty()) {
            for (String category : UserClass.keySet()) {
                subjectShow[len] = new ResultPanel(category, UserClass.get(category), "shelf");
                len++;
            }
        }
        if (!Class.isEmpty()) {
            for (String category : Class.keySet()) {
                subjectShow[len] = new ResultPanel(category, Class.get(category), "shelf");
                len++;
            }
        }
    }

    private void setContent(int n) {
        label.get(n).setForeground(SetUp.SPECIAL_COLOR);
        subjectShow[n].setBounds(130, 165, 500, 320);
        subjectShow[n].setVisible(true);
        this.add(subjectShow[n]);
    }

    protected void hideContent(int n) {
        label.get(n).setForeground(SetUp.SHELF_COLOR);
        subjectShow[n].setVisible(false);
    }

    public void setUserClass(String str) throws Exception {
        ArrayList<String> temp = new ArrayList<>();
        UserClass.put(str, temp);
        Update(UserClass, ujson);
    }

    public void Update(HashMap<String, ArrayList<String>> data, File json) throws Exception {
        String jsonString = JSON.toJSONString(data);
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(json), "UTF-8"))) {
            br.write(jsonString);
        }
    }

    @Override
    public void imageRepaint() {
        super.imageRepaint();
        menuJLabel.setIcon(new ImageIcon(SetUp.imageForMenu));
        addJLabel.setIcon(new ImageIcon(SetUp.imageForAddClass));
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
        for (ResultPanel s : subjectShow) {
            if (s != null) {
                s.changeColor();
            }
        }
        subjectLabel[nowPage].setForeground(SetUp.SPECIAL_COLOR);
        noteArea.setForeground(SetUp.FORE_COLOR);
    }

    public static void setNote() {
        noteArea.setEditable(true);
        String note = "";
        ArrayList<String> al = new ArrayList<>(ZCalendar.noteMap.keySet());
        al.sort(null);
        if (al.isEmpty()) {
            note = "无读书笔记信息";
        } else {
            note = al.stream().map((date) -> date + "\n" + ZCalendar.noteMap.get(date) + "\n").reduce(note, String::concat);
        }
        noteArea.setText(note);
        noteArea.setEditable(false);
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
            cCategory(temp, len > LEN ? LEN : len);
        }
    }

    public HashMap<String, ArrayList<String>> getUserClass() {
        return UserClass;
    }

    public HashMap<String, ArrayList<String>> getclass() {
        return Class;
    }

    public void cCategory(int n, int range) {
        for (int i = 0; i < range; i++) {
            subjectLabel[firstPage + i].setVisible(false);
        }
        for (int i = 0; i < range; i++) {
            subjectLabel[n + i].setBounds(270 + i * 58, 133, 55, 28);
            subjectLabel[n + i].setVisible(true);
        }
        firstPage = n;
    }

    public void movebooks() {
        ResultPanel tempr = subjectShow[nowPage];
        ArrayList<String> books = tempr.getSelectedBook();
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(ShelfPanel.this, "请选择要移动的书籍!", "警告", JOptionPane.WARNING_MESSAGE);
        } else {
            String str = ClassChooser2.category;
            if (UserClass.containsKey(str)) {
                UserClass.put(str, books);
                UserClass.get(tempr.getName()).removeAll(books);
                try {
                    Update(UserClass, ujson);
                } catch (Exception ex) {
                    Logger.getLogger(ShelfPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Class.put(str, books);
                Class.get(tempr.getName()).removeAll(books);
                try {
                    Update(Class, cjson);
                } catch (Exception ex) {
                    Logger.getLogger(ShelfPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                index.ReShelf(null);
            } catch (Exception ex) {
                Logger.getLogger(ShelfPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getnowpage() {
        return nowPage;
    }

    class AddClass extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            new ClassChooser(index).show();
        }
    }

    class showMenu extends MouseAdapter {

        private int flag = 1;

        public void mouseClicked(MouseEvent e) {
            if (flag == 1) {
                JLabel temp = (JLabel) e.getSource();
                popupMenu.show(temp.getRootPane(), temp.getX(), temp.getY() + temp.getHeight());
            } else {
                popupMenu.setVisible(false);
            }
            flag = 1 - flag;
        }
    }

    class deleteBook implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ResultPanel temp = subjectShow[nowPage];
            ArrayList<String> books = temp.getSelectedBook();
            if (books.isEmpty()) {
                JOptionPane.showMessageDialog(ShelfPanel.this, "请选择要删除的书籍!", "警告", JOptionPane.WARNING_MESSAGE);
            } else {
                String category = temp.getName();
                if (UserClass.containsKey(category)) {
                    for (String book : books) {
                        UserClass.get(category).remove(book);
                    }
                    try {
                        Update(UserClass, ujson);
                    } catch (Exception ex) {
                        Logger.getLogger(ShelfPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    for (String book : books) {
                        Class.get(category).remove(book);
                    }

                    try {
                        Update(Class, cjson);
                    } catch (Exception ex) {
                        Logger.getLogger(ShelfPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    index.ReShelf(null);
                } catch (Exception ex) {
                    Logger.getLogger(ShelfPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class moveBook implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cc2 = new ClassChooser2(index);
            cc2.show();
        }
    }
}
