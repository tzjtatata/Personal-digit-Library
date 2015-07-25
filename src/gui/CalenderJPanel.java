/*
 * 日历小组件
 */
package gui;

import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author 开发
 */
public class CalenderJPanel extends JPanel {

    static JLabel label;

    public CalenderJPanel() {
        this.setLayout(null);
        label.setBounds(105, 1, 746, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label);
        this.setSize(950, 100);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForCalenderHint);
        img.paintIcon(this, g, 0, -522);
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
