/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author 开发
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SetUp.Init();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("setFile/flag.pdl"), "UTF-8"))) {
            if (br.readLine().equals("0")) {
                backtable.NewSearch.Init(0);
                new backtable.InitReverseSet();

                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("setFile/flag.pdl"), "UTF-8"))) {
                    bw.write("1");
                }
            } else {
                backtable.NewSearch.Init(1);
            }
        }
        backtable.SearchContent.Readall();
        new MainFrame();
    }
}
