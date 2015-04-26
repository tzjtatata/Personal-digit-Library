/*
 * Lyz is a big Dolby.
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JFrame,各个JPanel的容器
 * @author 开发
 */
public class MainFrame extends JFrame{
    JPanel mainJPanel = new JPanel();  //主JPanel
    CardLayout cl = new CardLayout();
    public MainFrame(){
		this.setIconImage(this.getToolkit().getImage("gui/source/digital_library.png"));  //logo
        this.setContentPane(mainJPanel);  //放置
    }
}
