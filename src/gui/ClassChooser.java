/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author lyz
 */
public class ClassChooser extends BasicPanel {

    JDialog content = new JDialog();
    JTextField jt = new JTextField();
    JButton jb = new JButton("确定");
    JLabel jl = new JLabel();
    ZComboBox jc;
    String category = "";
    public static String newClass;

    public ClassChooser(MainFrame index, String str, int flag) {
        super(index);
        this.setLayout(null);
        jl.setText(str);
        ArrayList<String> cates = new ArrayList<>();
        for (String cate : index.getShelf().getUserClass().keySet()) {
            cates.add(cate);
        }
        for (String cate : index.getShelf().getclass().keySet()) {
            cates.add(cate);
        }
        String[] a = {"abc"};
        String[] strs = cates.toArray(a);
        jc = new ZComboBox(strs);
        jl.setBounds(50, 50, 100, 50);
        jt.setBounds(150, 50, 150, 40);
        jb.setBounds(175, 155, 80, 30);
        jc.setBounds(150, 50, 150, 40);
        jc.setTextSize(150, 40);
        jc.setBorder();
        jc.setSelectedItem(strs[0]);
        jl.setFont(SetUp.GLOBAL_FONT);
        jb.setFont(SetUp.GLOBAL_FONT);
        jb.addActionListener(new SimpleListener());
        if (flag == 1) {
            this.add(jt);
        } else {
            this.add(jc);
        }
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

    private class SimpleListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = jt.getText();
            newClass = str;
            try {
                index.ReShelf(newClass);
            } catch (Exception ex) {
                Logger.getLogger(ClassChooser.class.getName()).log(Level.SEVERE, null, ex);
            }
            content.dispose();
        }
    }
}
