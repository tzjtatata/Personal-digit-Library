/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.HashMap;
import javax.swing.*;
import com.alibaba.fastjson.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 设置面板
 *
 * @author 开发
 */
public class SetUp extends BasicPanel {

    private static File setFile;
    private static String jsonString;
    public static HashMap<String, HashMap<String, Object>> setMap;
    public static Font GLOBAL_FONT;
    public static Font SHELF_FONT;
    public static Color FORE_COLOR, SHELF_COLOR;
    static final Color[] foreColor = {new Color(39, 158, 218), Color.ORANGE, new Color(101, 0, 3)};
    static final Color[] shelfColors = {Color.WHITE, Color.ORANGE, Color.gray};
    static final Font[] globalFonts = new Font[foreColor.length];
    static final Font[] shelfFonts = new Font[foreColor.length];
    private final JLabel themeJLabel, fontJLabel, styleJLabel, startJLabel;
    private final JRadioButton[] styleButtons, themeButtons;
    private final ButtonGroup stylebButtonGroup, themeButtonGroup;
    private final UIManager.LookAndFeelInfo[] info;
    private final JButton globalButton, shelfButton, reseButton, rangeButton, resetRangeButton;
    private ZFontChooser z;
    //以下图片路径
    public static String imageForLogo;
    public static String imageForReturn;
    public static String imageForReturnToIndex;
    public static String imageForWelcome;
    public static String imageForIndexBackground;
    public static String imageForShelfBackground;
    public static String imageForShelfButton;
    public static String imageForSaveButton;
    public static String imageForAboutButton;
    public static String imageForSearchButton;
    public static String imageForSearchResultBackground;
    public static String imageForSearchBackground;
    public static String imageForCalenderHint;
    public static String imageForQueryButton;
    public static String imageForCleanButton;
    public static String imageForSetButton;
    public static String imageForSetBackground;
    public static String imageForAboutBackground;

    public SetUp(MainFrame index) throws Exception {
        super(index);
        this.setLayout(null);

        styleJLabel = new JLabel("风格设置", JLabel.CENTER);
        styleJLabel.setFont(GLOBAL_FONT);
        styleJLabel.setBounds(185, 210, 100, 50);
        this.add(styleJLabel);

        stylebButtonGroup = new ButtonGroup();
        //获取系统支持的风格
        info = UIManager.getInstalledLookAndFeels();
        styleButtons = new JRadioButton[info.length + 1];
        for (int i = 0; i < info.length; i++) {
            //设置风格名
            if (info[i].toString().contains("Metal")) {
                styleButtons[i] = new JRadioButton("Metal风格");
                //加监听器
                styleButtons[i].addActionListener(new RadioButtonListener(i, "javax.swing.plaf.metal.MetalLookAndFeel", this));
            } else if (info[i].toString().contains("Nimbus")) {
                styleButtons[i] = new JRadioButton("Nimbus风格");
                styleButtons[i].addActionListener(new RadioButtonListener(i, "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel", this));
            } else if (info[i].toString().contains("Motif")) {
                styleButtons[i] = new JRadioButton("Motif风格");
                styleButtons[i].addActionListener(new RadioButtonListener(i, "com.sun.java.swing.plaf.motif.MotifLookAndFeel", this));
            } else if (info[i].toString().contains("Classic")) {
                styleButtons[i] = new JRadioButton("Classic风格");
                styleButtons[i].addActionListener(new RadioButtonListener(i, "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", this));
            } else if (info[i].toString().contains("Windows")) {
                styleButtons[i] = new JRadioButton("Windows风格");
                styleButtons[i].addActionListener(new RadioButtonListener(i, "com.sun.java.swing.plaf.windows.WindowsLookAndFeel", this));
            } else if (info[i].toString().contains("Mac")) {
                styleButtons[i] = new JRadioButton("Mac风格");
                styleButtons[i].addActionListener(new RadioButtonListener(i, "com.sun.java.swing.plaf.mac.MacLookAndFeel", this));
            }
            styleButtons[i].setBounds(300 + 130 * (i % 4), 210 + (i / 4) * 40, 130, 50);
            styleButtons[i].setOpaque(false);
            styleButtons[i].setFont(GLOBAL_FONT);
            this.add(styleButtons[i]);
            stylebButtonGroup.add(styleButtons[i]);
        }
        //默认系统风格
        styleButtons[styleButtons.length - 1] = new JRadioButton("系统风格");
        styleButtons[styleButtons.length - 1].setBounds(300 + 130 * ((styleButtons.length - 1) % 4),
                210 + (styleButtons.length - 1) / 4 * 40, 130, 50);
        styleButtons[styleButtons.length - 1].setOpaque(false);
        styleButtons[styleButtons.length - 1].setFont(GLOBAL_FONT);
        styleButtons[styleButtons.length - 1].addActionListener(new RadioButtonListener(styleButtons.length - 1,
                UIManager.getSystemLookAndFeelClassName(), this));
        this.add(styleButtons[styleButtons.length - 1]);
        stylebButtonGroup.add(styleButtons[styleButtons.length - 1]);
        styleButtons[(int) setMap.get("style").get("num")].setSelected(true);

        fontJLabel = new JLabel("字体设置", JLabel.CENTER);
        fontJLabel.setFont(GLOBAL_FONT);
        fontJLabel.setBounds(185, 350, 100, 50);
        this.add(fontJLabel);

        globalButton = new JButton("普通字体设置");
        shelfButton = new JButton("书架字体设置");
        globalButton.setFont(GLOBAL_FONT);
        shelfButton.setFont(GLOBAL_FONT);
        globalButton.setBounds(390, 360, 150, 30);
        shelfButton.setBounds(560, 360, 150, 30);
        globalButton.addActionListener((ActionEvent e) -> {
            z = new ZFontChooser("global", index);
            try {
                z.newWindow();
            } catch (Exception ex) {
                Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        shelfButton.addActionListener((ActionEvent e) -> {
            z = new ZFontChooser("shelf", index);
            try {
                z.newWindow();
            } catch (Exception ex) {
                Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.add(globalButton);
        this.add(shelfButton);

        themeJLabel = new JLabel("主题设置", JLabel.CENTER);
        themeJLabel.setFont(styleJLabel.getFont());
        themeJLabel.setBounds(185, 290, 100, 50);
        this.add(themeJLabel);

        themeButtonGroup = new ButtonGroup();
        themeButtons = new JRadioButton[3];
        themeButtons[0] = new JRadioButton("蓝灰商务");
        //搞怪系列
        themeButtons[1] = new JRadioButton("Z神最帅");
        themeButtons[2] = new JRadioButton("粉嫩青春");
        themeButtons[(int) setMap.get("style").get("theme")].setSelected(true);
        //主题系列RadioButton
        for (int i = 0; i < themeButtons.length; i++) {
            themeButtons[i].setBounds(300 + 130 * (i % 4), 290 + (i / 4) * 40, 130, 50);
            themeButtons[i].setFont(GLOBAL_FONT);
            themeButtons[i].setOpaque(false);
            themeButtons[i].addActionListener(new ThemeListener(i, index));
            this.add(themeButtons[i]);
            themeButtonGroup.add(themeButtons[i]);
        }

        startJLabel = new JLabel("启动设置", JLabel.CENTER);
        startJLabel.setBounds(185, 410, 100, 30);
        this.add(startJLabel);
        //让flag.pdl置为0
        reseButton = new JButton("重置初始搜索");
        reseButton.setBounds(305, 410, 150, 30);
        reseButton.addActionListener((ActionEvent) -> {
            int answer = JOptionPane.showConfirmDialog(this, "重置初始搜索将会在下次启动时重新搜索您的计算机(如果您更改了大量文件，\n可能需要此功能)，这可能为花费较长时间，您确定要这么做吗？",
                    "警告", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("setFile/flag.pdl"), "UTF-8"))) {
                    bw.write("0");
                } catch (IOException ex) {
                    Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.add(reseButton);
        rangeButton = new JButton("搜索范围更改");
        rangeButton.setBounds(475, 410, 150, 30);
        rangeButton.addActionListener((ActionEvent) -> {
            //如何不让选C盘相关？
            File f;
            if (!setMap.get("range").get("range").equals("all")) {
                String s = (String) setMap.get("range").get("range");
                f = showFileChooser(this, new File(s));
            } else {
                f = showFileChooser(this, null);
            }
            if (f != null) {  //选择了文件以后，存储改变信息
                String path = f.getAbsolutePath();
                if (!path.endsWith("\\")) {
                    path += "\\";
                }
                setMap.get("range").put("range", path);
                try {
                    SaveSetInfo();
                } catch (Exception ex) {
                    Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.add(rangeButton);
        //重置至all
        resetRangeButton = new JButton("重置搜索范围");
        resetRangeButton.setBounds(645, 410, 150, 30);
        resetRangeButton.addActionListener((ActionEvent e) -> {
            int answer = JOptionPane.showConfirmDialog(this, "如此将重置初始搜索为全盘(除系统盘)搜索，\n确定要这样吗？", "提示", JOptionPane.OK_CANCEL_OPTION);
            if (answer == 0) {
                setMap.get("range").put("range", "all");
                try {
                    SaveSetInfo();
                } catch (Exception ex) {
                    Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.add(resetRangeButton);
        this.addReturnListener();
    }
//文件选择器

    public static File showFileChooser(SetUp setUp, File path) {
        JFileChooser jfc;
        if (path != null) {
            jfc = new JFileChooser(path);
        } else {
            jfc = new JFileChooser();
        }
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setMultiSelectionEnabled(false);
        jfc.showDialog(setUp, "选择该目录");
        if (jfc.getSelectedFile() != null && jfc.getSelectedFile().getAbsolutePath().startsWith(String.valueOf(System.getProperty("user.home").charAt(0)))) {
            JOptionPane.showMessageDialog(jfc, "对不起,不能选择系统盘目录！", "警告", JOptionPane.WARNING_MESSAGE);
            return showFileChooser(setUp, path);
        }
        return jfc.getSelectedFile();
    }
//初始化操作

    public static void Init() throws Exception {
        //设置主题对应的字体
        globalFonts[0] = new Font("微软雅黑", Font.PLAIN, 15);
        globalFonts[1] = new Font("微软雅黑", Font.PLAIN, 15);
        globalFonts[2] = Font.createFont(Font.TRUETYPE_FONT, //qq的少女字体
                new File("source/2.TTF")).deriveFont(Font.PLAIN, 15);
        shelfFonts[0] = new Font("华文楷体", Font.PLAIN, 15);
        shelfFonts[1] = new Font("微软雅黑", Font.PLAIN, 15);
        shelfFonts[2] = Font.createFont(Font.TRUETYPE_FONT, //qq的少女字体
                new File("source/2s.TTF")).deriveFont(Font.PLAIN, 18);
        //加载json
        setFile = new File("setFile/setInfo.json");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(setFile), "UTF-8"))) {
            jsonString = br.readLine();
        }
        setMap = JSON.parseObject(jsonString, new TypeReference<HashMap<String, HashMap<String, Object>>>() {
        });
        //读取字体图片等设置
        changeImage();
        int theme = (int) setMap.get("style").get("theme");
        FORE_COLOR = foreColor[theme];
        SHELF_COLOR = shelfColors[theme];
        GLOBAL_FONT = globalFonts[theme];  //先加载主题字体
        SHELF_FONT = shelfFonts[theme];
        //如果有，加载用户自己的字体设置
        if ((int) setMap.get("style").get("hasFont") == 1) {
            GLOBAL_FONT = new Font((String) setMap.get("global").get("font"), (int) setMap.get("global").get("style"), (int) setMap.get("global").get("size"));
            SHELF_FONT = new Font((String) setMap.get("shelf").get("font"), (int) setMap.get("shelf").get("style"), (int) setMap.get("shelf").get("size"));
        }
        UIManager.setLookAndFeel((String) setMap.get("style").get("style"));
    }

    public static void changeImage() {
        //更改图片
        String num = "_" + String.valueOf(setMap.get("style").get("theme"));
        imageForAboutButton = "source/关于" + num + ".jpg";
        imageForLogo = "source/digital_library" + num + ".png";
        imageForReturnToIndex = "source/returnToIndex" + num + ".png";
        imageForReturn = "source/return" + num + ".png";
        imageForWelcome = "source/welcomepic" + num + ".png";
        imageForIndexBackground = "source/主页_背景" + num + ".png";
        imageForShelfButton = "source/书架" + num + ".jpg";
        imageForShelfBackground = "source/书架背景" + num + ".PNG";
        imageForSaveButton = "source/储存" + num + ".png";
        imageForSearchButton = "source/搜索" + num + ".jpg";
        imageForSearchResultBackground = "source/搜索结果背景" + num + ".png";
        imageForSearchBackground = "source/搜索背景" + num + ".png";
        imageForCalenderHint = "source/主页_背景" + num + ".png";
        imageForQueryButton = "source/查询" + num + ".png";
        imageForCleanButton = "source/清除" + num + ".png";
        imageForSetButton = "source/设置" + num + ".jpg";
        imageForSetBackground = "source/设置2" + num + ".png";
        imageForAboutBackground = "source/about" + num + ".png";
    }

    /**
     * 存档
     *
     * @throws Exception
     */
    public static void SaveSetInfo() throws Exception {
        jsonString = JSON.toJSONString(setMap);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(setFile), "UTF-8"))) {
            bw.write(jsonString);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(SetUp.imageForSetBackground);
        img.paintIcon(this, g, 0, 0);
    }

    /**
     * 更改字体，c为MainFrame对象。
     *
     * @param c
     * @throws Exception
     */
    public static void newChangeFont(Container c) throws Exception {
        changeFont(c);
        for (ResultPanel resultPanel : ShelfPanel.subjectShow) {
            if (resultPanel != null && resultPanel.getComponents() != null && resultPanel.getComponents().length != 0) {
                for (Component c1 : resultPanel.getComponents()) {
                    c1.setFont(SetUp.SHELF_FONT);
                }
            }
        }
        setMap.get("shelf").put("font", SHELF_FONT.
                getFontName());
        setMap.get("shelf").put("size", SHELF_FONT.getSize());
        setMap.get("shelf").put("style", SHELF_FONT.getStyle());
        SaveSetInfo();
    }

    /**
     * 递归更改字体，通过newChangeFont调用
     *
     * @param c
     * @throws Exception
     */
    public static void changeFont(Container c) throws Exception {
        if (c.getComponents().length != 0) {
            for (Component c1 : c.getComponents()) {
                if (!((c1 instanceof ResultPanel) || (c1 instanceof ZCalendar))) {
                    changeFont((Container) c1);
                }
            }
        } else {
            c.setFont(GLOBAL_FONT);
            setMap.get("global").put("font", GLOBAL_FONT.getFontName());
            setMap.get("global").put("size", GLOBAL_FONT.getSize());
            setMap.get("global").put("style", GLOBAL_FONT.getStyle());
            SaveSetInfo();
        }
    }

    /**
     * 更改颜色
     *
     * @param c
     */
    public static void changeColor(Container c) {
        circleChangeColor(c);
        ((MainFrame) c).changeCalendarColor();
        for (ResultPanel resultPanel : ShelfPanel.subjectShow) {
            if (resultPanel != null) {
                circleChangeColor(resultPanel);
            }
        }
    }

    /**
     * 递归更改颜色
     *
     * @param c
     */
    public static void circleChangeColor(Container c) {
        if (c.getComponents().length != 0) {
            for (Component c1 : c.getComponents()) {
                if (!(c1 instanceof ShelfPanel)) {
                    circleChangeColor((Container) c1);
                }
            }
        } else {
            c.setForeground(FORE_COLOR);
        }
    }

    @Override
    public void imageRepaint() {
        super.imageRepaint();
        this.repaint();
    }

    private class RadioButtonListener implements ActionListener {

        private final int i;
        private final String feel;
        private final SetUp panel;

        public RadioButtonListener(int i, String feel, SetUp panel) {
            this.i = i;
            this.feel = feel;
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                UIManager.setLookAndFeel(feel);
                setMap.get("style").put("style", feel);
                setMap.get("style").put("num", i);
                SaveSetInfo();
            } catch (Exception ex) {
                Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            javax.swing.SwingUtilities.updateComponentTreeUI(panel);
            SwingUtilities.updateComponentTreeUI(MainFrame.index);
        }
    }

    class ThemeListener implements ActionListener {

        private final int n;
        private final MainFrame index;

        public ThemeListener(int i, MainFrame index) {
            n = i;
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setMap.get("style").put("theme", n);
            try {
                SaveSetInfo();
                changeImage();
                imageForCalenderHint = imageForSetBackground;
                index.imageRepaint();
                FORE_COLOR = foreColor[n];
                SHELF_COLOR = shelfColors[n];
                SHELF_FONT = shelfFonts[n];
                GLOBAL_FONT = globalFonts[n];
                newChangeFont(index);
                changeColor(index);
            } catch (Exception ex) {
                Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
