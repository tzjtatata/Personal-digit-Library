/*
 * 日历小组件
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author 开发
 */
public class CalenderJPanel extends JPanel {

    private static JLabel label;

    public CalenderJPanel() {
        this.setLayout(null);
        label.setBounds(103, 1, 744, 40);
        label.setOpaque(true);
        label.setBackground(new Color(215, 217, 218));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label);
        this.setSize(950, 100);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForCalenderHint);
        img.paintIcon(this, g, 0, 0);
    }

    public void imageRepaint() {
        this.repaint();
    }

    public static void setText(String s) {
        if (label == null) {
            label = new JLabel(s, JLabel.CENTER);
        } else {
            label.setText(s);
        }
    }
}
