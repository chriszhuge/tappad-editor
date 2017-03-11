
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.fonts.DefaultMacFontPolicy;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.*;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.theme.SubstanceJadeForestTheme;
import org.jvnet.substance.theme.SubstanceLightAquaTheme;
import org.jvnet.substance.theme.SubstanceOliveTheme;
import org.jvnet.substance.title.Glass3DTitlePainter;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import javax.swing.undo.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



public class Note extends JFrame
{
    int a1=1,a2=1;
    UndoManager manager=new UndoManager();
    JTextArea text1 = new JTextArea();
    JTextPane text = new JTextPane();
    JTextArea linenumber = new JTextArea();
    //Panel t1 = new Panel();
    //Panel t2 = new Panel();
    //Panel a = new Panel();
    JFileChooser chooser;
    String s;
    String sbuffer;
    File file;
    JMenuBar Mb;
    JMenu M1,M2,M3,M4;
    JMenuItem m11,m12,m13,m14,m15,m21,m22,m23,m24,m25,m32,m33,m41;
    JCheckBoxMenuItem m31=new JCheckBoxMenuItem("自动换行",true);



    /**文件格式过滤器**/
    private   class filter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File file) {
            String name=file.getName();
            name.toLowerCase();
            if(name.endsWith(".txt")||name.endsWith(".c")||name.endsWith(".java")||name.endsWith(".doc")||file.isDirectory())
                return true;
            else return false;
        }
        public String getDescription()
        {
            return ".txt";
        }
    }
    /**将菜单项JMenuItem添加到菜单JMenu**/
    public JMenuItem AddItem(String name,JMenu menu)
    {JMenuItem MI=new JMenuItem(name);
        menu.add(MI);
        return MI;
    }
    /**将菜单JMenu添加到菜栏JMenuBar**/
    public JMenu AddBar(String name,JMenuBar mb)
    {JMenu Mb=new JMenu(name);
        mb.add(Mb);
        return Mb;
    }
    public void playpane(int a,int b,String s1,int flag){
        a1 = a;
        a2 =b;
        setTitle(s1+"  [OpenPad 2016.12]");
        setResizable(true); //窗体是否可变
        setVisible(true);   //窗体是否可见
        if(flag == 2)
            SubstanceSaharaLookAndFeel.setSkin(new AutumnSkin());
        System.out.print(flag);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    Note()
    {

        setBounds(400,20,500,700);
        try {
            Image img = ImageIO.read(this.getClass().getResource("/image.png"));
            this.setIconImage(img);
        }catch (IOException ecc) {
            ecc.printStackTrace();
        }
        Mb=new JMenuBar();
        this.setJMenuBar(Mb);
        text.getDocument().addUndoableEditListener(manager);
        text.setFont(new Font("微软雅黑",Font.PLAIN,20));
        text.setCaretColor(Color.BLUE);//光标颜色
        text.setSelectedTextColor(Color.WHITE);//选中字体颜色
        text.setSelectionColor(Color.GRAY);//选中背景颜色
        text.setBackground(Color.WHITE);
        linenumber.setBackground(Color.decode("#F8F8FF"));
        linenumber.setFont(new Font("微软雅黑",Font.PLAIN,20));
        linenumber.setVisible(true);
        linenumber.setForeground(Color.GRAY);
        //a.add(linenumber,BorderLayout.EAST);
        //a.add(text,BorderLayout.WEST);
        //text.add(a,FlowLayout.LEFT);

        //text.setLineWrap(true); //是否换行
        //text.setWrapStyleWord(true);  //是否单词边界换行（即有空白）
        text.setMargin(new Insets(3, 5, 3, 5));//文本区与边框的间距，四个参数分别为上、左、下、右
        text.setDragEnabled(true); //开启或关闭自动拖动处理
        text.addKeyListener(new MyKeyListener());

        text1.setFont(new Font("微软雅黑",Font.PLAIN,15));
        text1.setCaretColor(Color.BLUE);//光标颜色
        text1.setSelectedTextColor(Color.WHITE);//选中字体颜色
        text1.setSelectionColor(Color.GRAY);//选中背景颜色
   //     text1.setLineWrap(true); //是否换行
        text1.setWrapStyleWord(true);  //是否单词边界换行（即有空白）
        text1.setMargin(new Insets(3, 5, 3, 5));//文本区与边框的间距，四个参数分别为上、左、下、右
        text1.setDragEnabled(true); //开启或关闭自动拖动处理
        text1.setBackground(Color.WHITE);
        JScrollPane js1 = new JScrollPane(text1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane js0 = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane js2 = new JScrollPane(linenumber,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js2.setPreferredSize(new Dimension(40, 600));
        js1.setPreferredSize(new Dimension(220, 600));
        js1.setPreferredSize(new Dimension(300, 100));
        add(js2,BorderLayout.WEST);
        add(js0,BorderLayout.CENTER);
        add(js1,BorderLayout.SOUTH);
        linenumber.append("1\r\n");


        text.getDocument().addDocumentListener(new SyntaxHighlighter(text));

        M1=this.AddBar("文件",Mb);
        M2=this.AddBar("编辑",Mb);
        M3=this.AddBar("格式",Mb);
        M4=this.AddBar("帮助",Mb);
        Font font=new Font("微软雅黑",Font.PLAIN,17);
        Font font1=new Font("微软雅黑",Font.PLAIN,15);
        M1.setFont(font);
        M2.setFont(font);
        M3.setFont(font);
        M4.setFont(font);
     /*新建选项*/
        m11=this.AddItem("新建",M1);
        m11.setFont(font1);
        m11.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {text.setText("");
            }
        });
     /*打开选项*/
        m12=this.AddItem("打开",M1);
        m12.setFont(font1);
        m12.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {try{chooser=new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setFileFilter(new filter());
                chooser.showOpenDialog(null);
                file=chooser.getSelectedFile();
                s=file.getName();
                setTitle(s);
                int length=(int)file.length();
                FileReader fr=new FileReader(file);
                char[] ch=new char[length];
                fr.read(ch);
                s=new String(ch);
                text.setText(s.trim());}
            catch(Exception e){JOptionPane.showMessageDialog(null,e);}
            }
        });


      /*保存选项*/
        m13=this.AddItem("保存",M1);
        m13.setFont(font1);
        m13.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            { if(file==null)try{
                chooser=new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                //s=JOptionPane.showInputDialog("请输入文件名：")+".txt";
                //s=JOptionPane.showInputDialog("请输入文件名：")+".doc";
                s = JOptionPane.showInputDialog("请输入文件名：");
                chooser.setSelectedFile(new File(s));
                chooser.setFileFilter(new filter());
                int yn=chooser.showSaveDialog(null);
                if(yn==chooser.APPROVE_OPTION)
                {   file=new File(chooser.getCurrentDirectory(),s);
                    file.createNewFile();
                    FileWriter fw=new FileWriter(file);
                    fw.write(text.getText());
                    fw.close();
                }
            }
            catch(Exception e){JOptionPane.showMessageDialog(null,e);}
            else try{
                    FileWriter fw=new FileWriter(file);
                    fw.write(text.getText());
                    fw.close();
                }
                catch(Exception e){JOptionPane.showMessageDialog(null,e);}
            }
        });
      /*另存为选项*/
        m14=this.AddItem("另存为",M1);
        m14.setFont(font1);
        m14.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                chooser.setDialogTitle("另存为...");
                int returnVal = chooser.showSaveDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                file=chooser.getSelectedFile();
                    try{
                        FileWriter fw=new FileWriter(file);
                        fw.write(text.getText());
                        setTitle(chooser.getSelectedFile().getName());
                        fw.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

        M1.addSeparator(); //横杆
     /*退出选项*/
        m15=this.AddItem("退出",M1);
        m15.setFont(font1);
        m15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
     /*撤消选项*/
        m21=this.AddItem("撤消",M2);
        m21.setFont(font1);
        m21.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {if(manager.canUndo())
                manager.undo();
            }
        });
     /*剪切选项*/
        M2.addSeparator();
        m22=this.AddItem("剪切",M2);
        m22.setFont(font1);
        m22.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {text.cut();
            }
        });
    /*复制选项*/
        m23=this.AddItem("复制",M2);
        m23.setFont(font1);
        m23.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {text.copy();
            }
        });
    /*粘贴选项*/
        m24=this.AddItem("粘贴",M2);
        m24.setFont(font1);
        m24.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {   text.paste();
            }
        });
    /*删除选项*/
        m25=this.AddItem("删除",M2);
        m25.setFont(font1);
        m25.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {//text.replaceRange("",text.getSelectionStart(),text.getSelectionEnd());
            }
        });
    /*自动换行选项*/
        M3.add(m31);
        m31.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {///if(m31.getState())
                //text.setLineWrap(true);
            //else //text.setLineWrap(false);
            }
        });
        m31.setFont(font1);
    /*字体格式设置选项*/
        m32=this.AddItem("字体选择",M3);
        m32.setFont(font1);
        m32.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            { GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                JList fontNames = new JList(ge.getAvailableFontFamilyNames());
                int response = JOptionPane.showConfirmDialog(null, new JScrollPane(fontNames),"请选择字体",JOptionPane.OK_CANCEL_OPTION);
                Object selectedFont = fontNames.getSelectedValue();
                if (response == JOptionPane.OK_OPTION && selectedFont != null)
                    text.setFont(new Font(fontNames.getSelectedValue().toString(),Font.PLAIN,20));
            }
        });
    /*字体颜色设置选项*/
        m33=this.AddItem("字体颜色",M3);
        m33.setFont(font1);
        m33.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        Color color = JColorChooser.showDialog(null, "文字颜色选择", Color.WHITE);
                        text.setForeground(color);
                    }
                }
        );

        m41=this.AddItem("关于记事本",M4);
        m41.setFont(font1);
        m41.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {JOptionPane.showMessageDialog(null,"记事本\n开发语言：JAVA\n","关于",JOptionPane.PLAIN_MESSAGE);
            }
        });

        }
    /*键盘监听*/
    class MyKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {

            Calendar cal = Calendar.getInstance();
            int c = cal.get(Calendar.SECOND);
            int a=0;
            a = a+c;
            //自动保存！！
            if (a % 2 == 0) {

                if(file==null)try {
                    chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File("."));
                    //s = JOptionPane.showInputDialog("请输入文件名：") + ".txt";
                    //s = JOptionPane.showInputDialog("请输入文件名：") + ".doc";
                    s = JOptionPane.showInputDialog("请输入文件名：");
                    chooser.setSelectedFile(new File(s));
                    chooser.setFileFilter(new filter());
                    int yn = chooser.showSaveDialog(null);
                    if (yn == chooser.APPROVE_OPTION) {
                        file = new File(chooser.getCurrentDirectory(), s);
                        file.createNewFile();
                        FileWriter fw = new FileWriter(file);
                        fw.write(text.getText());
                        fw.close();
                    }
                }
                catch(Exception ea){
                    JOptionPane.showMessageDialog(null, ea);
                }
                else try {
                    FileWriter fw = new FileWriter(file);
                    fw.write(text.getText());
                    fw.close();
                } catch (Exception eb) {
                    JOptionPane.showMessageDialog(null, eb);
                }
            }
            if (a % 10 == 0) {
                //自动另存为！！！
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                try {
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
                    String time = format.format(date);
                    sbuffer =  time.substring(5,16) + ".txt";
                    String strPath = "D:/"+s+"/"+sbuffer;
                    File file = new File(strPath);
                    File fileParentFile = file.getParentFile();
                    if(!fileParentFile.exists()){
                        fileParentFile.mkdirs();
                    }

                    file.createNewFile();

                    try{
                        FileWriter fw=new FileWriter(file);
                        fw.write(text.getText());
                        fw.close();
                    }
                    catch(Exception ec){
                        ec.printStackTrace();
                    }

                } catch (Exception ed) {
                    JOptionPane.showMessageDialog(null, ed);
                }

            }

            char charA = e.getKeyChar();
            if (charA == 8)
            {
                text1.append("删除字符\r\n");
            }
            else if(charA== 10){
                Rectangle rec = null;
                try {
                    rec = text.modelToView(text.getCaretPosition());
                } catch (BadLocationException eee) {
                    eee.printStackTrace();
                }
                int line = rec.y / rec.height + 1;
                String[] lineString = text.getText().split("\n");
                linenumber.append(""+(line+1)+"\r\n");
                text1.append("键入："+lineString[line-1]+"\r\n");
                text1.append("换行\r\n");
            }
        }
    }



    public static void main(String args[])
    {


            try{
                try{
                    UIManager.setLookAndFeel(new SubstanceLookAndFeel());
                    }catch(UnsupportedLookAndFeelException ex){
                        System.out.println(ex.getMessage());
                    }
            }catch(Exception ex){
            System.out.println(ex.getMessage());
            }
        //设置皮肤  
        SubstanceSaharaLookAndFeel.setSkin(new CremeSkin());
        //SubstanceSaharaLookAndFeel.setSkin(new AutumnSkin());
        //SubstanceSaharaLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
        //SubstanceSaharaLookAndFeel.setFontPolicy(new DefaultMacFontPolicy());
        //设置水印  
        SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
        //设置主题  
        SubstanceLookAndFeel.setCurrentTheme(new SubstanceCremeTheme());
        //SubstanceLookAndFeel.setCurrentTheme(new SubstanceLightAquaTheme());
        //SubstanceLookAndFeel.setCurrentTheme(new SubstanceOliveTheme());
        //SubstanceLookAndFeel.setCurrentTheme(new SubstanceJadeForestTheme());
        //设置题头  
        SubstanceLookAndFeel.setCurrentTitlePainter(new Glass3DTitlePainter());


        Note n=new Note();
        class Setting extends JFrame {
            int flag;
            File file;
            JLabel l1 = new JLabel("请设置自动保存时间间隔（单位：分） ");
            JLabel l2 = new JLabel("请设置版本控制时间间隔（单位：分） ");
            JLabel l3 = new JLabel("       请设置界面皮肤  ");
            JTextField j1 = new JTextField(9);
            JTextField j2 = new JTextField(9);
            Button b1 = new Button(" OK ");
            Button b2 = new Button(" cancel ");
            String s1;
            int a1, a2;
            Setting() {
                JRadioButton radioButton1 = new JRadioButton(" 蓝色(默认) ");// 创建单选按钮
                JRadioButton radioButton2 = new JRadioButton("   粉色             ");// 创建单选按钮
                ButtonGroup group = new ButtonGroup();// 创建单选按钮组
                group.add(radioButton1);// 将radioButton1增加到单选按钮组中
                group.add(radioButton2);// 将radioButton2增加到单选按钮组中
                setTitle("初始设置");
                setBounds(500, 100, 380, 200);
                setLayout(new FlowLayout());
                add(l1);
                add(j1);
                add(l2);
                add(j2);
                add(l3);
                add(radioButton1);
                add(radioButton2);
                add(b1);
                add(b2);
                radioButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        flag =1;
                    }
                });
                radioButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        flag =2;
                    }
                });
                try {
                    Image img = ImageIO.read(this.getClass().getResource("/image.png"));
                    this.setIconImage(img);
                }catch (IOException ecc) {
                    ecc.printStackTrace();
                }
                MyListener mylistener = new MyListener();
                //MyListener2 mylistener2 = new MyListener2();
                b1.addActionListener(mylistener);
                //b2.addActionListener(mylistener2);
                setVisible(true);
            }
            class MyListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    String str1 = j1.getText();
                    String str2 = j2.getText();
                    try {
                        JFileChooser chooser;
                        chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new File("."));
                        String s1;
                        s1 = JOptionPane.showInputDialog("请设置 title：");
                        //chooser.setSelectedFile(new File(s1));
                        n.playpane(a1,a2,s1,flag);
                    } catch (Exception ea) {
                        JOptionPane.showMessageDialog(null, ea);
                    }
                }
            }
        }
        Setting set = new Setting();

    }
}

class SyntaxHighlighter implements DocumentListener {

    //private Set<String> keywords;
    private String[] keywords;
    private Style keywordStyle;
    private Style keywordStyle1;
    private Style keywordStyle2;
    private Style keywordStyle3;
    private Style keywordStyle4;
    private Style keywordStyle5;
    private Style keywordStyle6;
    private Style keywordStyle7;
    private Style normalStyle;


    public SyntaxHighlighter(JTextPane editor) {

        // 准备着色使用的样式

        keywordStyle = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        keywordStyle1 = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        keywordStyle2 = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        keywordStyle3 = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        keywordStyle4 = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        keywordStyle5 = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        keywordStyle6 = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        keywordStyle7 = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);
        normalStyle = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);

        StyleConstants.setForeground(keywordStyle, Color.BLUE);
        StyleConstants.setForeground(keywordStyle1, Color.decode("#90EE90"));
        StyleConstants.setForeground(keywordStyle2, Color.MAGENTA);
        StyleConstants.setForeground(keywordStyle3, Color.decode("#668B8B"));
        StyleConstants.setForeground(keywordStyle4, Color.orange);
        StyleConstants.setForeground(keywordStyle5, Color.decode("#FF6EB4"));
        StyleConstants.setForeground(keywordStyle6, Color.decode("#836FFF"));
        StyleConstants.setForeground(keywordStyle7, Color.decode("#CD69C9"));
        StyleConstants.setForeground(normalStyle, Color.BLACK);


        // 准备关键字

        keywords = new String[30];
        keywords[0] = "include";
        keywords[1] = "for";
        keywords[2] = "if";
        keywords[3] = "while";
        keywords[4] = "do";
        keywords[5] = "public";
        keywords[6] = "protected";
        keywords[7] = "private";
        keywords[8] = "int";
        keywords[9] = "void";
        keywords[10] = "main";
        keywords[11] = "float";
        keywords[12] = "double";
        keywords[13] = "struct";
        keywords[14] = "abstract";
        keywords[15] = "boolean";
        keywords[16] = "break";
        keywords[17] = "case";
        keywords[18] = "class";
        keywords[19] = "continue";
        keywords[20] = "try";
        keywords[21] = "catch";
        keywords[22] = "throw";
        keywords[23] = "return";
        keywords[24] = "new";
        keywords[25] = "static";
        keywords[26] = "else";
        keywords[27] = "long";
        keywords[28] = "sizeof";
        keywords[29] = "switch";
    }


    public void colouring(StyledDocument doc, int pos, int len) throws BadLocationException {

        // 取得插入或者删除后影响到的单词.

        // 例如"public"在b后插入一个空格, 就变成了:"pub lic", 这时就有两个单词要处理:"pub"和"lic"

        // 这时要取得的范围是pub中p前面的位置和lic中c后面的位置

        int start = indexOfWordStart(doc, pos);

        int end = indexOfWordEnd(doc, pos + len);


        char ch;

        while (start < end) {

            ch = getCharAt(doc, start);

            if (Character.isLetter(ch) || ch == '_') {

                // 如果是以字母或者下划线开头, 说明是单词

                // pos为处理后的最后一个下标

                start = colouringWord(doc, start);

            } else {

                SwingUtilities.invokeLater(new ColouringTask(doc, start, 1, normalStyle));

                ++start;

            }

        }

    }


    /**
     * 对单词进行着色, 并返回单词结束的下标.
     */

    public int colouringWord(StyledDocument doc, int pos) throws BadLocationException {

        int wordEnd = indexOfWordEnd(doc, pos);

        String word = doc.getText(pos, wordEnd - pos);

        int i;
        int flag = 1;
        for (i = 0; i < 30; i++) {
            if (word.equals(keywords[i])) {
                if (i < 4) {

                    // 如果是关键字, 就进行关键字的着色, 否则使用普通的着色.
                    // 这里有一点要注意, 在insertUpdate和removeUpdate的方法调用的过程中, 不能修改doc的属性.

                    // 但我们又要达到能够修改doc的属性, 所以把此任务放到这个方法的外面去执行.

                    // 实现这一目的, 可以使用新线程, 但放到swing的事件队列里去处理更轻便一点.

                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle));

                }
                if (3 < i && i < 7)
                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle1));
                if (6 < i && i < 10)
                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle2));
                if (9 < i && i < 14)
                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle3));
                if (13 < i && i < 19)
                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle4));
                if (18 < i && i < 24)
                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle5));
                if (23 < i && i < 26)
                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle6));
                if (25 < i && i < 30)
                    SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle7));
                flag = 0;
                break;
            }
        }
        if (flag == 1)
            SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, normalStyle));
        return wordEnd;
    }
    /**
     * 取得在文档中下标在pos处的字符.
     * <p>
     * <p>
     * <p>
     * 如果pos为doc.getLength(), 返回的是一个文档的结束符, 不会抛出异常. 如果pos<0, 则会抛出异常.
     * <p>
     * 所以pos的有效值是[0, doc.getLength()]
     */
    public char getCharAt(Document doc, int pos) throws BadLocationException {

        return doc.getText(pos, 1).charAt(0);
    }
    /**
     * 取得下标为pos时, 它所在的单词开始的下标. Â±wor^dÂ± (^表示pos, Â±表示开始或结束的下标)
     */

    public int indexOfWordStart(Document doc, int pos) throws BadLocationException {

        // 从pos开始向前找到第一个非单词字符.

        for (; pos > 0 && isWordCharacter(doc, pos - 1); --pos) ;


        return pos;

    }


    /**
     * 取得下标为pos时, 它所在的单词结束的下标. Â±wor^dÂ± (^表示pos, Â±表示开始或结束的下标)
     *
     * @param doc
     * @param pos
     * @return
     * @throws BadLocationException
     */

    public int indexOfWordEnd(Document doc, int pos) throws BadLocationException {

        // 从pos开始向前找到第一个非单词字符.

        for (; isWordCharacter(doc, pos); ++pos) ;


        return pos;

    }


    /**
     * 如果一个字符是字母, 数字, 下划线, 则返回true.
     */

    public boolean isWordCharacter(Document doc, int pos) throws BadLocationException {

        char ch = getCharAt(doc, pos);

        if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_') {
            return true;
        }

        return false;

    }


    public void changedUpdate(DocumentEvent e) {


    }
    public void insertUpdate(DocumentEvent e) {

        try {

            colouring((StyledDocument) e.getDocument(), e.getOffset(), e.getLength());

        } catch (BadLocationException e1) {

            e1.printStackTrace();

        }

    }


    public void removeUpdate(DocumentEvent e) {

        try {

            // 因为删除后光标紧接着影响的单词两边, 所以长度就不需要了

            colouring((StyledDocument) e.getDocument(), e.getOffset(), 0);

        } catch (BadLocationException e1) {

            e1.printStackTrace();

        }

    }


    /**
     * 完成着色任务
     *
     * @author Biao
     */

    private class ColouringTask implements Runnable {

        private StyledDocument doc;

        private Style style;

        private int pos;

        private int len;


        public ColouringTask(StyledDocument doc, int pos, int len, Style style) {

            this.doc = doc;

            this.pos = pos;

            this.len = len;

            this.style = style;

        }


        public void run() {

            try {
                doc.setCharacterAttributes(pos, len, style, true);

            } catch (Exception e) {
            }

        }

    }
}
