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

    private ButtonGroup group;
    private JPanel buttonPanel;
    private JLabel label1, label2, label3, dateLabel;
    private JRadioButton bold, plain, italic, font1, font2, font3;
    private JRadioButtonMenuItem style, font;
    private int number_Gongxiang = 0;
    private String[] string_Gongxiang = new String[10];
    private int[] date;

    public SetUp(MainFrame index) {
        super(index);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("gui/source/设置2.png");
        img.paintIcon(this, g, 0, 0);
    }
}
