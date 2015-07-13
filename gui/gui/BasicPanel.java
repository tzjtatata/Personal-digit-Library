/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JPanel;

/**
 *
 * @author lyz
 */
public class BasicPanel extends JPanel {

    private final MainFrame index;

    public BasicPanel(MainFrame index) {
        this.index = index;
        index.addToIndex(this, index);
    }

}
