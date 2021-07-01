package GamePlay;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener{
    public static final int ROWS=8;
    public static final int COLS=18;
    public static final int deletetotal=72;//you need to delete 72 couples of image to win a game
    public static boolean restartgame=true;
    private static int currentdelete=0;//your current delete
    private int time=0;
    //image
    public BufferedImage temimage=null;
    public List temImages = new ArrayList();
    public static BufferedImage readyImage = null;
    public static BufferedImage WinImage = null;

    GamePanel gamePanel=this;
    private JFrame mainFrame=null;  //initate frame
    JMenuBar menu=null;// initate menu
    private Thread mainThread=null;
        //backgroud music
    private int image =12;




    public GamePanel(JFrame frame){
        setBackground(Color.white);
        this.setLayout(null);
        mainFrame=frame;
        initMenu();
        Music.background();
        ready();


    }
    private void initMenu(){
        menu=new JMenuBar();
        JMenu jm1=new JMenu("Help");
        JMenu jm2=new JMenu("Game");
        JMenuItem jm11 =new JMenuItem("Rule");
        JMenuItem jm12 = new JMenuItem("mute music");
        jm1.add(jm11);
        jm1.add(jm12);
        JMenuItem jm21=new JMenuItem("Restart");
        JMenuItem jm22 = new JMenuItem("Exit");
        jm2.add(jm21);
        jm2.add(jm22);
        menu.add(jm1);
        menu.add(jm2);
        mainFrame.setJMenuBar(menu);
        jm11.addActionListener(this);
        jm11.setActionCommand("Rule");
        jm12.addActionListener(this);
        jm12.setActionCommand("Mute");
        jm21.addActionListener(this);
        jm21.setActionCommand("Restart");
        jm22.addActionListener(this);
        jm22.setActionCommand("Exit");


    }

    public void paint(Graphics g){

    }
    private void ready(){
        readyimage();

        temImages.add(readyImage);
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                temImages.remove(readyImage);

            }

        }).start();

    }
    //this method has program
    private void restart(){
        mainThread.interrupt();

        currentdelete=0;
        time=0;



    }
    @Override
    public void actionPerformed(ActionEvent e){
        String command= e.getActionCommand();
        Object[]options={"Yes","No"};
        if("Exit".equals(command)){

            int response= JOptionPane.showOptionDialog(this, "Are you sure?", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response==0){
                System.exit(0);

            }
        }else if("Restart".equals(command)){
            int response=JOptionPane.showOptionDialog(this, "Are you sure?", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response==0){
                restart();

            }
        }else if("Rule".equals(command)){
            JOptionPane.showMessageDialog(null,"Lianliankan tests your eyesight. \n In a " +
                    "limited time, as long as you find all the same patterns \nthat can be connected in pairs, " +
                    "they will automatically disappear every time you find a pair. \nJust get all the patterns. " +
                    "You can win when you finish the elimination. The so-called being \nable to connect means that " +
                    "no matter whether it is horizontal or vertical, the line from one\n pattern to another cannot" +
                    " exceed two bends, and the line cannot pass through the pattern \nthat has not been eliminated.","Rule",
                    JOptionPane.INFORMATION_MESSAGE);

        }


    }
    public static void initimage(){

    }

    public static void readyimage(){
        try{
            readyImage= ImageIO.read(GamePanel.class.getResource("/gameimage/ready.png"));
            System.out.println(readyImage.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }








}

