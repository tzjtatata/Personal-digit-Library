package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author 开发
 */
public class Main {

    public static void main(String[] args) throws Exception {
        gui.SetUp.Init();
        try (BufferedReader br = new BufferedReader(new FileReader("main/backtable/flag.pdl"))) {
            if (br.readLine().equals("0")) {
                backtable.NewSearch.Init(0);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("main/backtable/flag.pdl"))) {
                    bw.write("1");
                }
            } else {
                backtable.NewSearch.Init(1);
            }
        }
        new gui.MainFrame();
    }
}
