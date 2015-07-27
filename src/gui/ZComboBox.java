/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.nio.sctp.AbstractNotificationHandler;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * JPanel上加上以空参数初始化的JTextField,配合JList以替代JComboBox
 *
 * @author ZouKaifa
 */
public class ZComboBox extends JPanel {

    private final JTextField textField;
    private JScrollPane scrollPane;
    private JList<String> list;

    /**
     * 用于JList的字符串数组
     *
     * @param strings
     */
    public ZComboBox(String[] strings) {
        super();
        this.setLayout(null);
        textField = new JTextField();
        list = new JList<>(strings);
        scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
        scrollPane.setVisible(false);

        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText(list.getSelectedValue());
                scrollPane.setVisible(false);
                setSize(textField.getWidth(), textField.getHeight());
            }
        });
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createLineBorder(SetUp.FORE_COLOR));
        textField.setOpaque(false);
        textField.addMouseListener(new TextListener(this));
        this.add(textField);
        this.setVisible(true);
        list.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setListUnVisible();
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setListUnVisible();
            }
        });
    }

    /**
     * 设置textField的大小
     *
     * @param width
     * @param height
     */
    public void setTextSize(int width, int height) {
        textField.setSize(width, height);
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public boolean isListVisible() {
        return scrollPane.isVisible();
    }

    public void setListUnVisible() {
        textField.setText(list.getSelectedValue());
        scrollPane.setVisible(false);
        setSize(textField.getWidth(), textField.getHeight());
    }

    public void setSelectedItem(String string) {
        textField.setText(string);
        list.setSelectedValue(string, true);
    }

    public String getSelectedItem() {
        return textField.getText();
    }

    public void setBorder() {
        textField.setBorder(BorderFactory.createLineBorder(SetUp.FORE_COLOR));
    }

    @Override
    public void setFont(Font font) {
        if (textField != null && list != null) {
            textField.setFont(font);
            list.setFont(font);
        }
    }

    public void addClickListener(ZComboBox another) {
        textField.addMouseListener(new ClickListener(another));
    }
    /*
     public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, AWTException {
     JFrame jFrame = new JFrame();
     JPanel rootPanel = new JPanel();
     jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     rootPanel.setBounds(200, 100, 300, 200);
     rootPanel.setLayout(null);
     jFrame.setLayout(null);
     jFrame.setBounds(200, 100, 300, 200);
     jFrame.setContentPane(rootPanel);
     JLabel label = new JLabel("fsdjfhsjdaf?");
     label.setBounds(0, 60, 130, 20);
     rootPanel.add(label);
     ZComboBox zComboBox = new ZComboBox(new String[]{"1", "2", "3", "4", "5", "6", "7", "34", "44", "4", "43", "2332"});
     zComboBox.setBounds(0, 0, 130, 60);
     zComboBox.setTextSize(100, 20);
     zComboBox.setText("1");
     rootPanel.add(zComboBox);
     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     SwingUtilities.updateComponentTreeUI(jFrame);
     jFrame.setVisible(true);
     }*/

    class TextListener extends MouseAdapter {

        private final ZComboBox comboBox;

        public TextListener(ZComboBox comboBox) {
            this.comboBox = comboBox;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (scrollPane.isVisible()) {
                scrollPane.setVisible(false);
                comboBox.setSize(textField.getWidth(), textField.getHeight());
            } else {
                scrollPane.setBounds(textField.getX(), textField.getY() + textField.getHeight(),
                        textField.getWidth(), 120);
                comboBox.setSize(textField.getWidth(), textField.getHeight() + scrollPane.getHeight());
                list.setSelectedValue(textField.getText(), true);
                scrollPane.setVisible(true);
                comboBox.repaint();
            }
        }
    }

    class ClickListener extends MouseAdapter {

        private final ZComboBox another;

        public ClickListener(ZComboBox another) {
            this.another = another;
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            another.setListUnVisible();
        }
    }
}
