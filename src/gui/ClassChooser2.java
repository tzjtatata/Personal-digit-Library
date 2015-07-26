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
import java.util.*;
/**
 *
 * @author lyz
 */
class ClassChooser2 extends BasicPanel{
        JDialog content = new JDialog();
        JComboBox jc = new JComboBox();
        JButton jb = new JButton("确定");
        JLabel jl = new JLabel("移动到...");
        public static String category;
        public ClassChooser2(MainFrame index) {
            super(index);
            this.setLayout(null);
            jl.setBounds(50,50,100,50);
            
            ArrayList<String> cates = new ArrayList<>();
            for (String cate:index.getShelf().getUserClass().keySet()) {
                cates.add(cate);
            }
            for (String cate:index.getShelf().getclass().keySet()) {
                cates.add(cate);
            }
            ArrayListComboBoxModel model = new ArrayListComboBoxModel(cates);
            jc = new JComboBox(model);
            jc.setBounds(150,50,150,40);
            jb.setBounds(175,155,80,30);
            jl.setFont(SetUp.GLOBAL_FONT);
            jb.setFont(SetUp.GLOBAL_FONT);
            jb.addActionListener(new SimpleListener());
            this.add(jc);
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
        class ArrayListComboBoxModel extends AbstractListModel implements ComboBoxModel {   
            private Object selectedItem;   

            private ArrayList anArrayList;   

            public ArrayListComboBoxModel(ArrayList arrayList) {   
              anArrayList = arrayList;   
            }   

            public Object getSelectedItem() {   
              return selectedItem;   
            }   

            public void setSelectedItem(Object newValue) {   
              selectedItem = newValue;   
            }   

            public int getSize() {   
              return anArrayList.size();   
            }   

            public Object getElementAt(int i) {   
              return anArrayList.get(i);   
            }   

        }
        private class SimpleListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
               category = (String) jc.getSelectedItem();
               index.getShelf().movebooks();
               content.dispose();
            }
        }
    }
