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
 *
 * @author lyz
 */
public class ClassChooser extends BasicPanel{
    
    JDialog content = new JDialog();
    JTextField jt = new JTextField();
    JButton jb = new JButton("确定");
    JLabel jl = new JLabel("新建类别名字");
    public static String newClass;
    public ClassChooser(MainFrame index) {
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
           newClass = str;
           index.ReShelf(newClass);
           content.dispose();
        }
    }
}
