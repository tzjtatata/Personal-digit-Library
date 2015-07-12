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

    private final AboutPanel about;
    private final SetUp setPanel;
    private final Search searchPanel;
    private final ShelfPanel shelf;
    private final CalenderJPanel calenderHint;
    private final Index index;
    static JPanel changeJPanel = new JPanel();  //切换用的JPanel
    static CardLayout cl = new CardLayout();  //切换书架,设置等JPanel
    JPanel mainJPanel = new JPanel();  //主JPanel

    public MainFrame() throws Exception {
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

        this.setIconImage(this.getToolkit().getImage("gui/source/digital_library.png"));  //logo
        this.setContentPane(mainJPanel);  //放置
        this.setFocusTraversalPolicyProvider(false);  //暂时没用,似乎可以取消tab键控制焦点
        this.setBounds(200, 50, 950, 650);
        this.setResizable(false);  //大小不可变
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        SetUp.newChangeFont(this);
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
        returnButton.setBounds(820, 80, 30, 30);
        returnButton.addActionListener((ActionEvent e) -> {
            cl.show(changeJPanel, "index");
        });
        self.add(returnButton);
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
}
