/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author 开发
 */
public class ZFontChooser extends JPanel {

    private Font font;
    private String defaultName;
    private int defaultStyle;
    private int defaultSize;
    private JDialog dialog;  //窗体
    private JLabel fontJLabel, styleJLabel, sizeJLabel, showJLabel;
    private JTextField fontField, styleField, sizeField;
    private JList fontJList, styleJList, sizeJList;
    private JButton ok, cancel;
    private JScrollPane fontJScrollPane, sizeJScrollPane;
    private JPanel showJPanel;  //显示框
    private Map sizeMap;
    private final String mode;
    private final MainFrame index;

    public ZFontChooser(String mode, MainFrame index) {
        this.mode = mode;
        this.index = index;
        init(mode);
    }

    private void init(String mode) {
        fontJLabel = new JLabel("字体:");
        styleJLabel = new JLabel("字型:");
        sizeJLabel = new JLabel("大小:");
        showJLabel = new JLabel("李沅泽是大逗比。", JLabel.CENTER);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  //当前环境可用字体
        String[] fontStrings = ge.getAvailableFontFamilyNames();
        fontJList = new JList(fontStrings);
        styleJList = new JList(new String[]{"常规", "斜体", "粗体", "粗斜体"});
        String[] sizeStrings = new String[]{
            "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72",
            "初号", "小初", "一号", "小一", "二号", "小二", "三号", "小三", "四号", "小四", "五号", "小五", "六号", "小六", "七号", "八号"
        };  //字号字符串
        int sizeValue[] = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 42, 36, 26, 24, 22, 18, 16, 15, 14, 12, 11, 9, 8, 7, 6, 5};  //对应大小
        sizeMap = new HashMap();
        for (int i = 0; i < sizeStrings.length; ++i) {
            sizeMap.put(sizeStrings[i], sizeValue[i]);
        }
        sizeJList = new JList(sizeStrings);
        fontJScrollPane = new JScrollPane(fontJList);
        sizeJScrollPane = new JScrollPane(sizeJList);
        showJPanel = new JPanel();
        ok = new JButton("确定");
        cancel = new JButton("取消");

        if (mode.equals("global")) {
            fontField = new JTextField(SetUp.GLOBAL_FONT.getFontName());
            showJLabel.setFont(SetUp.GLOBAL_FONT);
            switch (SetUp.GLOBAL_FONT.getStyle()) {
                case Font.PLAIN:
                    styleField = new JTextField("常规");
                    styleJList.setSelectedValue("常规", true);
                    break;
                case Font.BOLD:
                    styleField = new JTextField("粗体");
                    styleJList.setSelectedValue("粗体", true);
                    break;
                case Font.ITALIC:
                    styleField = new JTextField("斜体");
                    styleJList.setSelectedValue("斜体", true);
                    break;
                case Font.BOLD | Font.ITALIC:
                    styleField = new JTextField("粗斜体");
                    styleJList.setSelectedValue("粗斜体", true);
                    break;
            }
            sizeField = new JTextField(Integer.toString(SetUp.GLOBAL_FONT.getSize()));
            defaultName = SetUp.GLOBAL_FONT.getFontName();
            defaultSize = SetUp.GLOBAL_FONT.getSize();
            defaultStyle = SetUp.GLOBAL_FONT.getStyle();
        } else {
            fontField = new JTextField(SetUp.SHELF_FONT.getFontName());
            showJLabel.setFont(SetUp.SHELF_FONT);
            switch (SetUp.SHELF_FONT.getStyle()) {
                case Font.PLAIN:
                    styleField = new JTextField("常规");
                    styleJList.setSelectedValue("常规", true);
                    break;
                case Font.BOLD:
                    styleField = new JTextField("粗体");
                    styleJList.setSelectedValue("粗体", true);
                    break;
                case Font.ITALIC:
                    styleField = new JTextField("斜体");
                    styleJList.setSelectedValue("斜体", true);
                    break;
                case Font.BOLD | Font.ITALIC:
                    styleField = new JTextField("粗斜体");
                    styleJList.setSelectedValue("粗斜体", true);
                    break;
            }
            sizeField = new JTextField(Integer.toString(SetUp.SHELF_FONT.getSize()));
            defaultName = SetUp.SHELF_FONT.getFontName();
            defaultSize = SetUp.SHELF_FONT.getSize();
            defaultStyle = SetUp.SHELF_FONT.getStyle();
        }
        sizeJList.setSelectedValue(Integer.toString(defaultSize), true);
        fontJList.setSelectedValue(defaultName, true);

        showJLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("示例"));
        showJPanel.setLayout(new BorderLayout());
        showJLabel.setBackground(Color.white);
        styleJList.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
        styleField.setEditable(false);
        fontField.setEditable(false);
        sizeField.setEditable(false);

        fontJLabel.setBounds(12, 10, 30, 20);
        styleJLabel.setBounds(175, 10, 30, 20);
        sizeJLabel.setBounds(320, 10, 30, 20);
        fontField.setBounds(10, 30, 155, 20);
        styleField.setBounds(175, 30, 130, 20);
        sizeField.setBounds(320, 30, 60, 20);
        styleJList.setBounds(175, 50, 130, 100);
        fontJScrollPane.setBounds(10, 50, 155, 100);
        sizeJScrollPane.setBounds(320, 50, 60, 100);
        showJPanel.setBounds(5, 150, 380, 100);
        ok.setBounds(250, 250, 60, 20);
        cancel.setBounds(310, 250, 60, 20);

        showJPanel.add(showJLabel);
        this.setLayout(null);
        this.add(fontField);
        this.add(fontJLabel);
        this.add(fontJScrollPane);
        this.add(sizeField);
        this.add(sizeJLabel);
        this.add(sizeJScrollPane);
        this.add(styleField);
        this.add(styleJLabel);
        this.add(styleJList);
        this.add(showJPanel);
        this.add(ok);
        this.add(cancel);
        this.setVisible(true);

        fontJList.addListSelectionListener((ListSelectionEvent e) -> {
            defaultName = (String) fontJList.getSelectedValue();
            fontField.setText(defaultName);
            showJLabel.setFont(new Font(defaultName, defaultStyle, defaultSize));
        });

        styleJList.addListSelectionListener((ListSelectionEvent e) -> {
            String value = (String) ((JList) e.getSource()).getSelectedValue();
            switch (value) {
                case "常规":
                    defaultStyle = Font.PLAIN;
                    break;
                case "斜体":
                    defaultStyle = Font.ITALIC;
                    break;
                case "粗体":
                    defaultStyle = Font.BOLD;
                    break;
                case "粗斜体":
                    defaultStyle = Font.BOLD | Font.ITALIC;
                    break;
            }
            styleField.setText(value);
            showJLabel.setFont(new Font(defaultName, defaultStyle, defaultSize));
        });

        sizeJList.addListSelectionListener((ListSelectionEvent e) -> {
            defaultSize = (Integer) sizeMap.get(sizeJList.getSelectedValue());
            sizeField.setText(String.valueOf(defaultSize));
            showJLabel.setFont(new Font(defaultName, defaultStyle, defaultSize));
        });

        ok.addActionListener((ActionEvent e) -> {
            font = new Font(defaultName, defaultStyle, defaultSize);
            if (mode.equals("global")) {
                SetUp.GLOBAL_FONT = font;
            } else {
                SetUp.SHELF_FONT = font;
            }
            try {
                SetUp.newChangeFont(index);
            } catch (Exception ex) {
                Logger.getLogger(ZFontChooser.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialog.dispose();
            dialog = null;
        });

        cancel.addActionListener((ActionEvent e) -> {
            font = null;
            dialog.dispose();
            dialog = null;
        });
    }

    public Font showDialog(Frame parent, String title) {
        if (title == null) {
            if (mode.equals("global")) {
                title = "普通字体选择器";
            } else {
                title = "书架字体选择器";
            }
        }
        dialog = new JDialog(parent, title, true);
        dialog.add(this);
        dialog.setResizable(false);
        dialog.setSize(400, 310);
        dialog.setLocation(380, 120);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.removeAll();
                dialog.dispose();
                dialog = null;
            }
        });
        dialog.setVisible(true);
        font = new Font(defaultName, defaultStyle, defaultSize);
        return font;
    }

    /**
     *
     * @return @throws Exception
     */
    public Font newWindow() throws Exception {
        return showDialog(null, null);
    }
    /*
     public static void main(String[] args) throws Exception {
     ZFontChooser zFontChooser = new ZFontChooser();
     zFontChooser.newWindow();
     }
     */
}
