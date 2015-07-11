/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JPanel;

/**
 *
 * @author 开发
 */
public class ShelfTest extends JPanel {

    private MainFrame index;

    public ShelfTest(MainFrame index) {
        super();
        this.setLayout(null);
        this.index = index;
        index.addToIndex(this, index);
    }
}
