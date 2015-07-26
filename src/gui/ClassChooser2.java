/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author lyz
 */
class ClassChooser2 extends BasicPanel{
        JDialog content = new JDialog();
        JMenu jt = new JMenu();
        JButton jb = new JButton("确定");
        JLabel jl = new JLabel("新建类别名字");
        public static String category;
        public ClassChooser2(MainFrame index) {
            super(index);
            this.setLayout(null);
            jl.setBounds(50,50,100,50);
            jt.setBounds(150,50,150,40);
            jb.setBounds(175,155,80,30);
            jl.setFont(SetUp.GLOBAL_FONT);
            jb.setFont(SetUp.GLOBAL_FONT);
            jb.addActionListener(new SimpleListener());
            this.add(jt);
            this.add(jb);
            this.add(jl);
            content.setResizable(false);
            content.setSize(400, 310);
            content.setLocation(380, 120);
            content.add(this);
        }
        public void show() {
            content.setVisible(true);
        }
        private class SimpleListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
               String str = jt.getText();
               category = str;
                try {
                    index.ReShelf(category);
                } catch (Exception ex) {
                    Logger.getLogger(ClassChooser2.class.getName()).log(Level.SEVERE, null, ex);
                }
               content.dispose();
            }
        }
    }
