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

    private JLabel label;

    public CalenderJPanel() {
        this.setLayout(null);
        label = new JLabel("李沅泽是个大逗比");
        label.setBounds(103, 1, 744, 40);
        label.setOpaque(true);
        label.setBackground(new Color(215, 217, 218));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label);
        this.setSize(950, 100);
        this.setVisible(true);
    }
}
