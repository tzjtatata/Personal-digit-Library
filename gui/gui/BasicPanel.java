/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author lyz
 */
public class BasicPanel extends JPanel {

    public BasicPanel(MainFrame index) {
        index.addToIndex(this, index);
    }

}
