/*
 * Lyz is a big Dolby.
 */
package gui;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame,各个JPanel的容器
 *
 * @author 开发
 */
public class MainFrame extends JFrame {

    private final AboutPanel about;
    private final SetUp setPanel;
    private final Search searchPanel;
    private final ShelfPanel shelf;
    private final CalenderJPanel calenderHint;
    static Index index;
    static JPanel changeJPanel = new JPanel();  //切换用的JPanel
    static CardLayout cl = new CardLayout();  //切换书架,设置等JPanel
    JPanel mainJPanel = new JPanel();  //主JPanel

    public MainFrame() throws Exception {
        super("个人数字图书馆");
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
        setPanel = new SetUp(this);
        shelf = new ShelfPanel(this);
        index = new Index();
        about = new AboutPanel(this);

        mainJPanel.setLayout(null);
        changeJPanel.setLayout(cl);
        calenderHint = new CalenderJPanel();
        searchPanel = new Search(this);
        cl.addLayoutComponent(shelf, "shelf");
        cl.addLayoutComponent(index, "index");
        cl.addLayoutComponent(searchPanel, "search");
        cl.addLayoutComponent(setPanel, "set");
        cl.addLayoutComponent(about, "about");
        changeJPanel.add(shelf);
        changeJPanel.add(index);
        changeJPanel.add(searchPanel);
        changeJPanel.add(setPanel);
        changeJPanel.add(about);
        cl.show(changeJPanel, "index");

        changeJPanel.setBounds(0, 0, 950, 522);
        mainJPanel.add(changeJPanel);
        calenderHint.setBounds(0, 522, 950, 100);
        mainJPanel.add(calenderHint);

        this.setIconImage(this.getToolkit().getImage(SetUp.imageForLogo));  //logo
        this.setContentPane(mainJPanel);  //放置
        this.setFocusTraversalPolicyProvider(false);  //暂时没用,似乎可以取消tab键控制焦点
        this.setBounds(200, 50, 950, 650);
        this.setResizable(false);  //大小不可变
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        SetUp.newChangeFont(this);
        this.setVisible(true);
    }

    //调用所有的imageRepaint从而重新绘制需要的图片
    public void imageRepaint() {
        about.imageRepaint();
        calenderHint.imageRepaint();
        index.imageRepaint();
        searchPanel.imageRepaint();
        setPanel.imageRepaint();
        shelf.imageRepaint();
    }
}
