/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 函数接口
 *
 * @author ZouKaifa
 */
@FunctionalInterface
interface Function {

    public void fun();

}

/**
 *
 * @author ZouKaifa
 */
public class ZMenu extends JMenu {

    private int nowItems;  //现在的菜单数
    private int up;  //最上面显示的JMenuItem计数
    private final int preferItems;
    private final ArrayList<JMenuItem> menuItems;
    private final JButton upItem, downItem;
    private final String upString = "▲", downString = "▼";
    private final JMenuItem[] nowShowingItems;
    private String selectedString;

    /**
     * 带有下拉框或滚动条的菜单
     *
     * @param s 菜单显示的字符串
     * @param preferItems 最多显示的JMenuItems数目（即完全不需要上下拉框
     */
    public ZMenu(String s, int preferItems) {
        super(s);
        this.preferItems = preferItems;
        nowItems = 0;
        menuItems = new ArrayList<>();
        upItem = new JButton(upString);
        downItem = new JButton(downString);
        upItem.setFocusPainted(false);
        downItem.setFocusPainted(false);
        upItem.setContentAreaFilled(false);
        downItem.setContentAreaFilled(false);
        up = 0;
        nowShowingItems = new JMenuItem[preferItems - 2];
        //addMoveListener();
    }

    /**
     * 可以用一个ArrayList<JMenuItem>来初始化ZMenu，自动设置组件。
     *
     * @param s
     * @param preferItems
     * @param items
     */
    public ZMenu(String s, int preferItems, ArrayList<JMenuItem> items) {
        this(s, preferItems);
        items.stream().forEach((item) -> {
            ZMenu.this.add(item);
        });
    }

    /**
     * 用JMenuItems数组初始化ZMenu
     *
     * @param s
     * @param preferItems
     * @param items
     */
    public ZMenu(String s, int preferItems, JMenuItem[] items) {
        this(s, preferItems);
        for (JMenuItem item : items) {
            ZMenu.this.add(item);
        }
    }

    /**
     * 重载父类的add方法，检查数量
     *
     * @param menuItem
     * @return
     */
    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItems.add(menuItem);
        nowItems++;
        if (nowItems > preferItems) {  //超过就全部去掉重新添加
            for (JMenuItem item : nowShowingItems) {
                if (item != null) {
                    remove(item);
                }
            }
            remove(downItem);
            super.add(upItem);
            for (int i = 0; i < nowShowingItems.length; i++) {
                if (nowShowingItems[i] == null) {
                    nowShowingItems[i] = new JMenuItem();
                }
                super.add(nowShowingItems[i]);
            }
            super.add(downItem);
            //只显示部分
            upItem.setForeground(Color.gray);
            for (int i = 0; i < nowShowingItems.length; i++) {
                nowShowingItems[i].setText(menuItems.get(i).getText());
            }
            downItem.setForeground(Color.BLACK);
        } else {
            super.add(menuItem);
        }
        return menuItem;
    }

    /**
     * 移动
     */
    private void addMoveListener() {
        downItem.addActionListener((ActionEvent e) -> {
            if (downItem.getForeground() == Color.black) {
                upItem.setForeground(Color.BLACK);
                up++;
                for (int i = 0; i < preferItems - 2; i++) {
                    nowShowingItems[i].setText(menuItems.get(i + up).getText());
                }
                if (up + preferItems - 2 == menuItems.size()) {
                    downItem.setForeground(Color.gray);
                }
            }
        }
        );
        upItem.addActionListener((ActionEvent e) -> {
            if (upItem.getForeground() == Color.BLACK) {
                --up;
                if (up == 0) {
                    upItem.setForeground(Color.gray);
                }
                for (int i = 0; i < preferItems - 2; i++) {
                    nowShowingItems[i].setText(menuItems.get(i + up).getText());
                }
                downItem.setForeground(Color.BLACK);
            }
        });
        for (int i = 0; i < nowShowingItems.length; i++) {
            if (nowShowingItems[i] == null) {
                nowShowingItems[i] = new JMenuItem();
            }
        }
        for (JMenuItem nowShowingItem : nowShowingItems) {
            nowShowingItem.addActionListener((ActionEvent e) -> {
                selectedString = nowShowingItem.getText();
            });
        }
    }

    /**
     * 传递一个响应函数给ZMenu的菜单项。从而避免在ZMenu外部单独加监听器,同时，只 有调用了该方法，才会为ZMenu的每一项加上监听器
     *
     * @param function 点击菜单项后响应的函数
     */
    void addFuntionForItem(Function function) {
        if (nowItems > preferItems) {
            for (JMenuItem nowShowingItem : nowShowingItems) {
                nowShowingItem.addActionListener((ActionEvent e) -> {
                    function.fun();
                });
            }
            addMoveListener();
        } else {
            menuItems.stream().forEach((menuItem) -> {
                menuItem.addActionListener((ActionEvent e) -> {
                    selectedString = menuItem.getText();
                    function.fun();
                });
            });
        }
    }

    public String getSelectedItemString() {
        return selectedString;
    }
}
