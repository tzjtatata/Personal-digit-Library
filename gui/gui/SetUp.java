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

    public static Font DEFAULT_FONT = new Font("宋体", Font.PLAIN, 9);
    private final JLabel styleJLabel, fontJLabel, shareJLabel;

    public SetUp(MainFrame index) {
        super(index);
        this.setLayout(null);

        styleJLabel = new JLabel("风格");
        styleJLabel.setFont(DEFAULT_FONT);
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
