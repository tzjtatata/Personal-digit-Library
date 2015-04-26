/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
