package me.calendar.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import me.calendar.frame.MainFrame;
import me.calendar.service.ActionType;
import me.calendar.service.Utility;

public class MainDesktopPane extends JDesktopPane {

    /**
     *
     */
    private static final long serialVersionUID = 8006725966900883482L;

    MainFrame mf;
    public JLabel lab;

    public MainDesktopPane(MainFrame _mf) {
        mf = _mf;
        init();
    }

    void init() {
        setPreferredSize(new java.awt.Dimension(950, 650));//設定桌面大小

        //setBackground(new java.awt.Color(215,217,218));//設定桌面顏色
        initCalenderPane();
        initLabels();
        initNoteArea();
        initButtons();
        initSelect();
        initInput();
        indexButton();
        //initNow();
        Icon icon = new ImageIcon("gui/source/主页_背景.png", "");  //设置背景
        //Icon icon=new ImageIcon("主页.png","");  //设置背景
        lab = new JLabel("", icon, SwingConstants.CENTER);
        lab.setBounds(0, 0, 950, 650);
        add(lab);
    }

    void initCalenderPane() {
        mf.calendarPane = new CalendarPane(mf);
        add(mf.calendarPane);

    }

    void initLabels() {
        mf.lab_sun = new JLabel();
        mf.lab_sun.setText("Sun");
        mf.lab_sun.setBounds(645, 222, 21, 21);
        mf.lab_sun.setFont(Utility.en11);
        mf.lab_sun.setForeground(new java.awt.Color(39, 158, 218));
        add(mf.lab_sun);

        mf.lab_week = new JLabel();
        mf.lab_week.setText("Mon   Tue   Wed   Thu   Fri   Sat");
        mf.lab_week.setForeground(new java.awt.Color(39, 158, 218));
        mf.lab_week.setBounds(675, 222, 189, 21);
        mf.lab_week.setFont(Utility.en11);
        add(mf.lab_week);

        mf.lab_show_test = new JLabel();

        mf.lab_show_test.setText("个人数字图书馆");
        mf.lab_show_test.setBounds(645, 430, 200, 21);
        mf.lab_show_test.setFont(new java.awt.Font("微软雅黑", 1, 13));
        mf.lab_show_test.setForeground(new java.awt.Color(39, 158, 218));
        add(mf.lab_show_test);

        mf.lab_show_tip = new JLabel();
        add(mf.lab_show_tip);
        mf.lab_show_tip.setText("------------------------------------");
        mf.lab_show_tip.setForeground(new java.awt.Color(255, 255, 255));
        mf.lab_show_tip.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 18));
        mf.lab_show_tip.setBounds(645, 208, 200, 14);
        mf.lab_show_tip.setFont(Utility.cn11);

        mf.lab_query_month = new JLabel();

        mf.lab_query_month.setText("月");
        mf.lab_query_month.setForeground(new java.awt.Color(39, 158, 218));
        mf.lab_query_month.setFont(new java.awt.Font("微软雅黑", 1, 14));
        mf.lab_query_month.setBounds(758, 183, 20, 21);

        //jLabel3.setBackground(Color.blue);
        //jLabel3.setOpaque(true);
        //mf.lab_query_month.setFont(Utility.cn12);
        add(mf.lab_query_month);

        mf.lab_query_year = new JLabel();
        add(mf.lab_query_year);
        mf.lab_query_year.setText("年");
        mf.lab_query_year.setForeground(new java.awt.Color(39, 158, 218));
        mf.lab_query_year.setFont(new java.awt.Font("微软雅黑", 1, 14));
        //mf.lab_query_year.setFont(Utility.cn12);
        mf.lab_query_year.setBounds(690, 182, 14, 21);

        mf.jLabel7 = new JLabel();
        add(mf.jLabel7);
        mf.jLabel7.setText("");
        mf.jLabel7.setBounds(0, 0, 0, 0);//設定大小為0
    }

    void initNoteArea() {
        mf.area_note = new JTextArea(182, 42);

        mf.area_note.setText("");//預設內容清空
        mf.area_note.setBounds(645, 452, 182, 42);//設定大小
        mf.area_note.setForeground(new java.awt.Color(39, 158, 218));
        mf.area_note.setFont(new java.awt.Font("黑体", Font.BOLD, 18));//設定字體樣式大小
        mf.area_note.setLineWrap(true);//文字過長自動換行
        mf.area_note.setWrapStyleWord(true);//文字過長自動換行
        mf.area_note.setBackground(new Color(215, 217, 218));
        add(mf.area_note);
        JScrollPane scrollPane = new JScrollPane(mf.area_note, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.red);
        add(scrollPane);//上面為建立scorollpane(視窗旁邊的移動棒);將記事框建立在scrollpane上
        scrollPane.setBounds(645, 452, 182, 42);//設定大小及位置

    }

    void initButtons() {

        Icon i1 = new ImageIcon("gui/source/清除.png", "");
        Icon i2 = new ImageIcon("gui/source/储存.png", "");
        Icon i3 = new ImageIcon("gui/source/查询.png", "");

        mf.btn_clear = new DefaultButton(i1, ActionType.Clear);
        mf.btn_clear.setBorder(null);
        mf.btn_clear.setLocation(795, 430);
        add(mf.btn_clear);

        mf.btn_save = new DefaultButton(i2, ActionType.Save);
        mf.btn_save.setBorder(null);
        mf.btn_save.setLocation(760, 430);
        add(mf.btn_save);

        mf.btn_query = new DefaultButton(i3, ActionType.Query);
        mf.btn_query.setBorder(null);
        mf.btn_query.setLocation(785, 184);
        add(mf.btn_query);
    }

    void initSelect() {
        ComboBoxModel<String> jComboBox1Model = new DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});//內容設定1~12
        mf.cbox_month = new JComboBox<>();
        add(mf.cbox_month);
        mf.cbox_month.setModel(jComboBox1Model);
        mf.cbox_month.setBackground(new Color(215, 217, 218));
        mf.cbox_month.setForeground(new java.awt.Color(39, 158, 218));
        mf.cbox_month.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 12));
        mf.cbox_month.setBounds(710, 184, 40, 18);
        mf.cbox_month.setFont(Utility.en11);
        mf.cbox_month.setSelectedItem(String.valueOf(Utility.getdate()[1]));
    }

    void initInput() {
        mf.text_year = new JTextField();

        mf.text_year.setText(String.valueOf(Utility.getdate()[0]));
        mf.text_year.setBounds(645, 184, 40, 18);
        mf.text_year.setBackground(new Color(215, 217, 218));
        mf.text_year.setForeground(new java.awt.Color(39, 158, 218));
        mf.text_year.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 12));

        add(mf.text_year);
    }

    void indexButton() {
        mf.jp.setLayout(null);

        mf.jb_shelf.setMnemonic('b');
        mf.jb_shelf.setBounds(140, 215, 56, 28);
        mf.jb_shelf.setBorder(null);
        this.add(mf.jb_shelf);

        mf.jb_function.setMnemonic('f');
        mf.jb_function.setBounds(263, 348, 54, 28);
        mf.jb_function.setBorder(null);
        this.add(mf.jb_function);

        mf.jb_help.setMnemonic('h');
        mf.jb_help.setBounds(390, 215, 54, 27);
        mf.jb_help.setBorder(null);
        this.add(mf.jb_help);

        mf.jb_about.setMnemonic('a');
        mf.jb_about.setBounds(518, 348, 49, 26);
        mf.jb_about.setBorder(null);
        this.add(mf.jb_about);

        this.add(mf.jp);
    }

    public void initNow() {
        int[] now = new int[3];
        now = Utility.getdate();//預設為當年當月
        String year5, smonth;
        year5 = String.valueOf(now[0]);
        smonth = String.valueOf(now[1]);
        if (smonth.length() == 1) {
            smonth = "0" + smonth;
        }

        mf.lab_show_date = new JLabel();
        add(mf.lab_show_date);
        mf.lab_show_date.setText(year5 + " 年 " + smonth + " 月");
        mf.lab_show_date.setBounds(645, 200, 120, 21);
        mf.lab_show_date.setForeground(new java.awt.Color(215, 217, 218));//設定字體為白色
        Utility.date_btn_create(now[0], now[1]);//產生日期按鈕
    }
}
