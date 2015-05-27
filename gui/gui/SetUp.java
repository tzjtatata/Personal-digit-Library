/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import javax.swing.*;

/**
 * 设置面板
 *
 * @author 开发
 */
public class SetUp extends BasicPanel {

    public static String FONT_FOR_LIST = "微软雅黑";  //列表中的字体
    public static String FONT_FOR_GLOBAL = "微软雅黑";  //全局字体
    public static int WIDTH_FOR_LIST = 0;  //各种搜索结果书架结果的宽度
    public static int WIDTH_FOR_GLOBAL = 0;
    public static int SIZE_MIN = 10;
    public static int SIZE_MID = 15;
    public static int SIZE_MAX = 30;
    public static String FONT_DEFAULT = "Consolas";
    public static int WIDTH_DEFAULT = 0;
    public static int SIZE_DEFAULT = 15;

    private JLabel styleJLabel, fontJLabel, shareJLabel;
    private JComboBox styleBox, sizeBox, fontBox;

    public SetUp(MainFrame index) {
        super(index);
        this.setLayout(null);

        styleJLabel = new JLabel("风格");
        styleJLabel.setFont(new Font(FONT_DEFAULT, WIDTH_DEFAULT, SIZE_MAX));
        styleJLabel.setBounds(315, 200, 100, 50);
        this.add(styleJLabel);

        fontJLabel = new JLabel("字体");
        fontJLabel.setFont(styleJLabel.getFont());
        fontJLabel.setBounds(500, 200, 100, 50);
        this.add(fontJLabel);

        shareJLabel = new JLabel("共享");
        shareJLabel.setFont(styleJLabel.getFont());
        shareJLabel.setBounds(680, 200, 100, 50);
        this.add(shareJLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("gui/source/设置2.png");
        img.paintIcon(this, g, 0, 0);
    }
}
