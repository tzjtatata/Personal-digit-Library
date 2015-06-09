/*
 * Lyz is a big Dolby.
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JFrame,各个JPanel的容器
 *
 * @author 开发
 */
public class MainFrame extends JFrame {

    private SetUp setPanel;
    private final Search searchPanel;
    private final ShelfPanel shelf;
    //private JPanel bookshelfPanel;
    //private JPanel indexPanel;
    private final CalenderJPanel calenderHint;
    JPanel changeJPanel = new JPanel();  //切换用的JPanel
    CardLayout cl = new CardLayout();  //切换书架,设置等JPanel
    JPanel mainJPanel = new JPanel();  //主JPanel
    IndexTest index;  //测试

    public MainFrame() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //测试代码段
        /*shelf = new ShelfTest(this) {
         @Override
         protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         ImageIcon img = new ImageIcon("gui/source/书架背景.png");
         img.paintIcon(this, g, 0, 0);
         }
         };*/
        setPanel = new SetUp(this);
        shelf = new ShelfPanel();
        index = new IndexTest() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("gui/source/主页_背景.png");
                img.paintIcon(this, g, 0, 0);
            }
        };
        index.setLayout(null);
        JButton sousuo = new JButton(new ImageIcon("gui/source/搜索.jpg"));
        sousuo.setBounds(263, 348, 54, 28);
        sousuo.setBorder(null);
        sousuo.addActionListener((ActionEvent e) -> {
            cl.show(changeJPanel, "search");
        });
        JButton sousuo2 = new JButton(new ImageIcon("gui/source/书架.jpg"));
        sousuo2.setBounds(140, 215, 56, 28);
        sousuo2.setBorder(null);
        sousuo2.addActionListener((ActionEvent e) -> {
            cl.show(changeJPanel, "shelf");
        });
        JButton set = new JButton(new ImageIcon("gui/source/设置.jpg"));
        set.setBounds(390, 215, 54, 27);
        set.setBorder(null);
        set.addActionListener((ActionEvent e) -> {
            cl.show(changeJPanel, "set");
        });
        index.add(sousuo);
        index.add(sousuo2);
        index.add(set);
        //测试代码段结束

        mainJPanel.setLayout(null);
        changeJPanel.setLayout(cl);
        calenderHint = new CalenderJPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("gui/source/日历小部件.jpg");
                img.paintIcon(this, g, 0, 0);
            }
        };
        searchPanel = new Search(this);
        cl.addLayoutComponent(shelf, "shelf");
        cl.addLayoutComponent(index, "index");
        cl.addLayoutComponent(searchPanel, "search");
        cl.addLayoutComponent(setPanel, "set");
        changeJPanel.add(shelf);
        changeJPanel.add(index);
        changeJPanel.add(searchPanel);
        changeJPanel.add(setPanel);
        cl.show(changeJPanel, "index");

        changeJPanel.setBounds(0, 0, 950, 522);
        mainJPanel.add(changeJPanel);
        calenderHint.setBounds(0, 522, 950, 100);
        mainJPanel.add(calenderHint);

        this.setIconImage(this.getToolkit().getImage("gui/source/digital_library.png"));  //logo
        this.setContentPane(mainJPanel);  //放置
        this.setFocusTraversalPolicyProvider(false);  //暂时没用,似乎可以取消tab键控制焦点
        this.setBounds(200, 50, 950, 650);
        this.setResizable(false);  //大小不可变
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * 将JPanel组件添加到卡片布局中，并为其加上返回主页的按钮
     *
     * @param self 需要添加到卡片布局中的JPanel
     * @param index 需要返回的主页
     */
    public void addToIndex(JPanel self, MainFrame index) {
        JButton returnButton = new JButton(new ImageIcon("gui/source/returnToIndex.png"));
        returnButton.setBounds(750, 80, 30, 30);
        returnButton.addActionListener((ActionEvent e) -> {
            cl.show(changeJPanel, "index");
        });
        self.add(returnButton);
    }
}
