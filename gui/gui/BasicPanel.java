/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import javax.smartcardio.CardTerminals;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author lyz
 */
public class BasicPanel extends JPanel{
    public BasicPanel(MainFrame index) {
        
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("gui/source/搜索背景.png");
        img.paintIcon(this, g, 0, 0);
    }
    protected void back() {
        
    }
}
