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

    private JLabel help, copyright;

    public AboutPanel(MainFrame index) {
        super(index);
        this.setLayout(null);
        help = new JLabel("<html><body>·通过主页的书架、搜索等四个按钮可以访问对应页面，通过右上角的图标返回主页。<br>·书架页面可以查阅电子书分类，打开电子书，还可以自定义添加分类。<br>·搜索页面可以按照需求对电子书进行搜索，搜索到的结果可以直接打开。<br>·设置页面可以更改电子书搜索范围，但仅当您重置初始搜索时，更改有效。<br>·设置页面还可以更改字体显示，风格主题等。<br>·主页的日历可以查阅特定日期的读书笔记（当天的读书笔记会一直在下方显示）。<br>·当您的电子书文件夹发生较大改动时，请记得重置初始搜索。</body></html>");
        help.setFont(SetUp.GLOBAL_FONT);
        help.setForeground(new Color(1, 158, 218));
        help.setBounds(150, 150, 650, 300);
        help.setBackground(new Color(215, 217, 218));
        help.setOpaque(true);
        copyright = new JLabel("copyright by HIT 李沅泽 邹开发 唐梦研 高琦琦", JLabel.CENTER);
        copyright.setFont(SetUp.GLOBAL_FONT);
        copyright.setForeground(new Color(1, 158, 218));
        copyright.setBounds(105, 470, 747, 30);
        copyright.setBackground(new Color(215, 217, 218));
        copyright.setOpaque(true);
        this.add(help);
        this.add(copyright);
        this.addReturnListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForAboutBackground);
        img.paintIcon(this, g, 0, 0);
    }

    @Override
    public void imageRepaint() {
        super.imageRepaint();
        this.repaint();
    }
}
