/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.BasicPanel;
import gui.MainFrame;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import me.ResultPanel;

/**
 *
 * @author lyz
 */
public class ShelfPanel extends  JPanel{
    private ResultPanel subjectShow;
    private JLabel[] subjectLabel = new JLabel[5];
    private final int LEN = 5;
    public ShelfPanel(/*MainFrame index*/) {
        //super(index);
        this.setLayout(null);
        setCategory();
        
    }
    protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("gui/source/书架背景.PNG");
                img.paintIcon(this, g, 0, 0);
            }
    protected void setCategory(){
        for (int i=0;i<LEN;i++)
        {
            subjectLabel[i] = new JLabel("Label"+i);
            subjectLabel[i].setBounds(270 + i * 58, 133, 55, 28);
            this.add(subjectLabel[i]);
        }
    }
}
