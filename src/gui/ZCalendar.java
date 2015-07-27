/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.xml.internal.fastinfoset.tools.StAX2SAXReader;
import java.awt.Component;

/**
 *
 * @author 开发
 */
public class ZCalendar extends JPanel {

    private JLabel dateJLabel, noteJLabel; // 后两个分别用来提示日期和笔记等
    private JLabel saveLabel, cleanLabel, queryLabel;
    private JLabel yearJLabel, monthJLabel; // 年字和月字
    private JLabel[] dateJLabels, weekJLabels; // 42个日期块和7个星期块
    private JTextArea noteArea; // 笔记
    private Calendar systemCalendar; // 系统时间
    private JScrollPane noteJScrollPane; // 控制文本域的滚动条
    public static HashMap<String, String> noteMap; // 笔记对应的hashmap
    private int[] selected; // 当前被选择的年月，和日期块序号
    private ZComboBox yearComboBox, monthComboBox; // 新的Z下拉框

    public ZCalendar() throws Exception {
        InitComponents();
    }

    // 初始化各种组件
    private void InitComponents() throws Exception {
        this.setLayout(null);
        initMap();
        // 实例化对象
        systemCalendar = Calendar.getInstance();
        saveLabel = new JLabel("存储", JLabel.CENTER);
        cleanLabel = new JLabel("清除", JLabel.CENTER);
        queryLabel = new JLabel("查询", JLabel.CENTER);
        dateJLabel = new JLabel("日期", JLabel.LEFT);
        noteJLabel = new JLabel("笔记", JLabel.LEFT);
        dateJLabels = new JLabel[42];
        for (int i = 0; i < dateJLabels.length; i++) {
            dateJLabels[i] = new JLabel(String.valueOf(i), JLabel.CENTER);
        }
        weekJLabels = new JLabel[7];
        yearJLabel = new JLabel("年", JLabel.CENTER);
        monthJLabel = new JLabel("月", JLabel.CENTER);
        String[] weekStrings = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri",
            "Sat"};
        for (int i = 0; i < weekStrings.length; i++) {
            weekJLabels[i] = new JLabel(weekStrings[i], JLabel.CENTER);
        }
        String[] years = new String[81]; // 1970到2050
        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(1970 + i);
        }

        yearComboBox = new ZComboBox(years);
        String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12"};
        monthComboBox = new ZComboBox(months);
        noteArea = new JTextArea();
        noteJScrollPane = new JScrollPane(noteArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 设置组件,顺手add
        monthComboBox.setBounds(82, 5, 44, 21);
        monthComboBox.setTextSize(44, 21);
        monthComboBox.setOpaque(false);
        monthComboBox.setSelectedItem(String.valueOf(systemCalendar.get(Calendar.MONTH) + 1));
        monthComboBox.setBorder();
        this.add(monthComboBox);
        yearComboBox.setBounds(0, 5, 60, 21);
        yearComboBox.setTextSize(60, 21);
        yearComboBox.setOpaque(false);
        yearComboBox.setSelectedItem(String.valueOf(systemCalendar
                .get(Calendar.YEAR)));
        yearComboBox.setBorder();
        this.add(yearComboBox);

        monthJLabel.setBounds(124, 5, 20, 21);
        this.add(monthJLabel);
        yearJLabel.setBounds(64, 5, 14, 21);
        this.add(yearJLabel);
        for (int i = 0; i < dateJLabels.length; i++) {
            dateJLabels[i].setBounds(29 * (i % 7), 68 + 29 * (i / 7), 29, 29);
            dateJLabels[i].setFont(new java.awt.Font("Leto", 1, 12));
            this.add(dateJLabels[i]);
        }
        saveLabel.setBounds(118, 252, 40, 20);
        this.add(saveLabel);
        cleanLabel.setBounds(153, 252, 40, 20);
        cleanLabel.setBorder(null);
        this.add(cleanLabel);
        queryLabel.setBounds(155, 6, 40, 20);
        queryLabel.setBorder(null);
        this.add(queryLabel);
        for (int i = 0; i < weekJLabels.length; i++) {
            weekJLabels[i].setBounds(i * 29, 44, 29, 21);
            weekJLabels[i].setFont(new Font("Segue", Font.PLAIN, 12));
            this.add(weekJLabels[i]);
        }
        dateJLabel.setBounds(3, 30, 200, 14);
        dateJLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 14));
        dateJLabel.setForeground(SetUp.FORE_COLOR);
        this.add(dateJLabel);
        noteJLabel.setBounds(3, 252, 200, 21);
        noteJLabel.setFont(new java.awt.Font("微软雅黑", 1, 13));
        this.add(noteJLabel);
        noteArea.setBounds(3, 274, 189, 44);
        noteArea.setFont(new java.awt.Font("黑体", Font.BOLD, 10));
        noteArea.setWrapStyleWord(true);
        noteArea.setLineWrap(true);
        noteJScrollPane.setBounds(noteArea.getBounds());
        noteArea.setOpaque(false);
        noteJScrollPane.setOpaque(false);
        noteJScrollPane.getViewport().setOpaque(false);
        this.add(noteJScrollPane);
        // 监听器响应阶段
        queryLabel.addMouseListener(new CursorListener());
        queryLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Integer.parseInt((String) yearComboBox.getSelectedItem()) != selected[0]
                        || Integer.parseInt((String) monthComboBox.getSelectedItem()) != selected[1]) { // 年月日变化才调用函数
                    setDate(0);
                }
            }
        });
        cleanLabel.addMouseListener(new CursorListener());
        saveLabel.addMouseListener(new CursorListener());
        saveLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    saveNote();
                } catch (Exception ex) {
                    Logger.getLogger(ZCalendar.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });
        cleanLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    cleanNote();
                } catch (Exception ex) {
                    Logger.getLogger(ZCalendar.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });
        yearComboBox.addClickListener(monthComboBox);
        monthComboBox.addClickListener(yearComboBox);
        for (Component component : this.getComponents()) {
            if (component instanceof ZComboBox) {
            } else {
                component.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        yearComboBox.setListUnVisible();
                        monthComboBox.setListUnVisible();
                    }
                });
            }
        }
        noteArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                yearComboBox.setListUnVisible();
                monthComboBox.setListUnVisible();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                yearComboBox.setListUnVisible();
                monthComboBox.setListUnVisible();
            }
        });
        this.setOpaque(false);
        setDate(1);
        displayNote();
    }

    /**
     * isFirst:是否是第一次调用 根据已选择日期显示日期标签
     */
    private void setDate(int isFirst) {
        int year = Integer.parseInt((String) yearComboBox.getSelectedItem());
        int month = Integer.parseInt((String) monthComboBox.getSelectedItem()) - 1; // month从0开始
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1);
        int first = c.get(Calendar.DAY_OF_WEEK) - 1; // 第一天的位置（周日视为0）
        for (JLabel dateJLabel1 : dateJLabels) {
            dateJLabel1.setVisible(false); // 全部消失
        }
        for (int i = first, j = 1; j < c.getActualMaximum(Calendar.DATE) + 1; i++, j++) {
            dateJLabels[i].setVisible(true); // 部分显现并设置显示内容
            dateJLabels[i].setText(String.valueOf(j));
            if (dateJLabels[i].getMouseListeners() != null) {
                for (MouseListener mouseListener : dateJLabels[i]
                        .getMouseListeners()) {
                    dateJLabels[i].removeMouseListener(mouseListener); // 先移除之前的监听器
                }
            }
            dateJLabels[i]
                    .addMouseListener(new DateListener(year, month + 1, j));
        }
        if (year == systemCalendar.get(Calendar.YEAR)
                && month == systemCalendar.get(Calendar.MONTH)) { // 为当前月则突出当前日期
            // dateJLabels[first + systemCalendar.get(Calendar.DATE) -
            // 1].setOpaque(true);
        } else {
            for (JLabel dateJLabel1 : dateJLabels) {
                dateJLabel1.setOpaque(false);
            }
        }
        if (isFirst == 1) {
            selected = new int[]{-1, -1, -1};
            setSelect(year, month + 1, systemCalendar.get(Calendar.DATE));
        } else {
            setSelect(year, month + 1, 1);
        }
    }

    /**
     * 设置选中的天及笔记信息
     *
     * @param year
     * @param month
     * @param date
     */
    private void setSelect(int year, int month, int date) {
        selected[0] = year;
        selected[1] = month;
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date);
        int i = 0;
        while (!dateJLabels[i++].isVisible())
			;
        i--; // 计算第一个显示了的日期
        if (selected[2] != -1) { // 是-1说明是第一次调用
            dateJLabels[selected[2]].setForeground(SetUp.FORE_COLOR);
            selected[2] = date + i - 1;
            dateJLabels[selected[2]].setForeground(SetUp.SPECIAL_COLOR); // 选中后前景色突出
        } else {
            int j = 0;
            while (++j != date)
				;
            j--;
            selected[2] = i + j; // 当前日期所代表序号
            dateJLabels[selected[2]].setForeground(SetUp.SPECIAL_COLOR);
        }

        if (noteMap.containsKey(year + "-" + month + "-" + date)) { // 如果有记事的话
            noteArea.setText(noteMap.get(year + "-" + month + "-" + date));
            noteJLabel.setText("当日读书笔记");
        } else {
            noteArea.setText("");
            noteJLabel.setText("当日无读书笔记");
        }
        dateJLabel.setText("已选择:" + year + "年" + month + "月" + date + "日");
    }

    /**
     * 存储信息
     */
    private void saveNote() throws Exception {
        if ("".equals(noteArea.getText())) {
            noteJLabel.setText("请输入内容!");
        } else {
            noteMap.put(
                    yearComboBox.getSelectedItem() + "-"
                    + monthComboBox.getSelectedItem() + "-"
                    + dateJLabels[selected[2]].getText(),
                    noteArea.getText()); // 年-月-日为key，信息为value
            noteJLabel.setText("存储成功!");
            writeJson();
        }
    }

    /**
     * 清除某天笔记
     *
     * @throws Exception
     */
    private void cleanNote() throws Exception {
        if (!"".equals(noteArea.getText())) {
            noteArea.setText("");
            noteJLabel.setText("清除成功");
            noteMap.remove(yearComboBox.getSelectedItem() + "-"
                    + monthComboBox.getSelectedItem() + "-"
                    + dateJLabels[selected[2]].getText());
            writeJson();
        }
    }

    /**
     * 初始化哈希map，读取笔记信息
     *
     */
    private void initMap() throws Exception {
        File f = new File("setFile/calendarNote.json");
        if (!f.exists()) { // 没有就创建新文件
            f.createNewFile();
            noteMap = new HashMap<>();
        } else {
            String line = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f), "UTF-8"))) {
                line = br.readLine();
            }
            if (JSON.parseObject(line,
                    new TypeReference<HashMap<int[], String>>() {
                    }) == null) {
                noteMap = new HashMap<>();
            } else {
                noteMap = JSON.parseObject(line,
                        new TypeReference<HashMap<String, String>>() {
                        }); // 转换为map
            }
        }
    }

    /**
     * 写入json
     */
    private void writeJson() throws Exception {
        displayNote();
        File f = new File("setFile/calendarNote.json");
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "UTF-8"))) {
            bw.write(JSON.toJSONString(noteMap));
        }
    }

    /**
     * 显示当天的读书笔记（给日历小部件用)
     *
     */
    public static void displayNote() {
        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
                + "-" + c.get(Calendar.DATE);
        if (!noteMap.containsKey(date)) {
            CalenderJPanel.setText("当日无读书笔记");
        } else {
            CalenderJPanel.setText(noteMap.get(date));
        }
        ShelfPanel.setNote();
    }

    public void changeColor() {
        for (JLabel weekJLabel : weekJLabels) {
            weekJLabel.setForeground(SetUp.FORE_COLOR);
        }
        noteJLabel.setForeground(SetUp.FORE_COLOR);
        noteArea.setForeground(SetUp.FORE_COLOR);
        yearComboBox.setForeground(SetUp.FORE_COLOR);
        yearComboBox.setBorder();
        monthComboBox.setBorder();
        monthComboBox.setForeground(SetUp.FORE_COLOR);
        yearJLabel.setForeground(SetUp.FORE_COLOR);
        monthJLabel.setForeground(SetUp.FORE_COLOR);
        for (JLabel dateJLabel1 : dateJLabels) {
            dateJLabel1.setForeground(SetUp.FORE_COLOR);
        }
        setDate(1);
    }

    public void imageRepaint() {
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

    /**
     * 日期块的监听器
     */
    class DateListener extends MouseAdapter {

        private final int year, month, date;

        public DateListener(int year, int month, int date) {
            this.year = year;
            this.month = month;
            this.date = date;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setSelect(year, month, date);
            yearComboBox.setListUnVisible();
            monthComboBox.setListUnVisible();
        }
    }

}
