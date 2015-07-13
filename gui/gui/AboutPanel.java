/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author 开发
 */
public class AboutPanel extends BasicPanel {

	private JLabel help,copyright;
    
	public AboutPanel(MainFrame index) {
        super(index);
        this.setLayout(null);
        help = new JLabel("<html><p>操作说明如下：</p><p>Balabalabala...谁来帮忙写一个使用说明</p><p>你们觉得是用图片直接打字好，还是用html写文本好？</p></html>");
        help.setFont(new java.awt.Font("微软雅黑", 1, 28));
        help.setForeground(new Color(1, 158, 218));
        help.setBounds(150, 150, 650,300);
        help.setBackground(new Color(215, 217, 218));
        help.setOpaque(true);
        copyright = new JLabel("copyright by HIT 李沅泽 邹开发 唐梦研 高琦琦");
        copyright.setFont(new java.awt.Font("微软雅黑", 1, 28));
        copyright.setForeground(new Color(1, 158, 218));
        copyright.setBounds(300, 470, 350,30);
        copyright.setBackground(new Color(215, 217, 218));
        copyright.setOpaque(true);
        this.add(help);
        this.add(copyright);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("gui/source/about.png");
        img.paintIcon(this, g, 0, 0);
    }
}
