package Game;
//TODO:
//為class.draw()定位frame
import javax.swing.*;
import java.lang.Thread;
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;//圖片
import javax.sound.sampled.AudioInputStream;//音樂
import java.io.*;
import javax.sound.sampled.*;//音樂
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Game {
    private static JFrame frame = new JFrame("雷霆戰機");
    private static JLayeredPane pn_mainMenu = new JLayeredPane();
    private static MoveFlight moveFlight = new MoveFlight();
    private static Flight flight = new Flight();
    private static ArrayList<FlyingObjectBase> listOfObj = new ArrayList<FlyingObjectBase>();
    private static boolean game_state = false;
    private static Point point;
    private static int score;
    private static int distance;
    private static Timmer timmer = new Timmer();
    private static Semaphore mutex = new Semaphore(1);
    static void music(){     	
        try{
             File musicPath = new File("bgm.wav");
                 
             if(musicPath.exists())
             {
                 AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                 Clip clip = AudioSystem.getClip();
                 clip.open(audioInput);
                 clip.start();
                 clip.loop(Clip.LOOP_CONTINUOUSLY);
             }
             else {
                 System.out.println("無法播放");
             }
 
         }catch(Exception error){
             System.out.println("File Not Found");
             System.out.println(error);
         }
     }


    public static void main(String[] args)
    {
        frame.setSize(500, 800);
        pn_mainMenu.setLayout(null);
        ImageIcon background = new ImageIcon("board.png");
        JLabel bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
        bgLabel.setBounds(-15, -10, 500, 800);
        ImageIcon logo = new ImageIcon("logo.png");
        JLabel lb = new JLabel(logo);
        lb.setBounds(13, 75, 450, 300);
        JButton bt_start = new JButton("開始遊戲");
        bt_start.setForeground(Color.white);
        bt_start.setBackground(Color.BLACK);
        bt_start.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 28));
        bt_start.setBounds(177, 396, 146, 48);

        JButton bt_option = new JButton("選項");
        bt_option.setForeground(Color.white);
        bt_option.setBackground(Color.BLACK);
        bt_option.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 28));
        bt_option.setBounds(177, 458, 146, 48);
        
        JButton bt_exit = new JButton("結束遊戲");
        bt_exit.setForeground(Color.white);
        bt_exit.setBackground(Color.BLACK);
        bt_exit.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 28));
        bt_exit.setBounds(177, 520, 146, 48);

        bt_start.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){start();}
                }
            );
        bt_option.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){}
                }
            );
        bt_exit.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){System.exit(0);}
                }
            );
        
        pn_mainMenu.add(lb);
        pn_mainMenu.add(bgLabel);
        pn_mainMenu.add(bt_start);
        pn_mainMenu.add(bt_option);
        pn_mainMenu.add(bt_exit);
        pn_mainMenu.setLayer(lb, 3);
        pn_mainMenu.setLayer(bgLabel, 2);
        pn_mainMenu.setLayer(bt_start, 1);
        pn_mainMenu.setLayer(bt_option, 1);
        pn_mainMenu.setLayer(bt_exit, 1);
        frame.add(pn_mainMenu);
        frame.repaint();
        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent event) {
            System.exit(0);}}
            );
        frame.setVisible(true);
        frame.setResizable(false);
        music();
        
        moveFlight.start();
    }

    private static void start()
    {
        //TODO:
        // - sleep 幾秒，顯示起始畫面
        // - game_state 設為 true
        // - 啟動 thread
        //      * 跑計時器(計算路程)
        //      * 讀取計時器，到一定的路程會召喚 enemy
        //      * 計算是否戰機有受到傷害
        //   x   * MoveFlight
        score = 0;
        distance = 0;
        game_state = true;
        return;
    }

    private static void option()
    {

    }

    private void gameover()
    {
        //TODO:
        // - game_state 設為 false
        // - 顯示結算畫面
        //      * 分數(計時器)
        //      * 殺死的 enemy
        //      * 按鈕 => 回到標題畫面
        // - 停止計時器
        game_state = false;

    }

    private static class MoveFlight extends Thread {
        
        MoveFlight()
        {
            
        }

        @Override
        public void run() {
            if (game_state){
                point = frame.getLocation();
                flight.setPosition(point.getX(), point.getY());
                flight.draw();
            }
        }

    }

    private static class Timmer extends Thread{
        Timmer(){

        }

        @Override
        public void run(){
            if (game_state){
                try {
                    mutex.acquire();
                    score+=1;
                    distance+=1;
                    mutex.release();
                } catch (InterruptedException e) {
                    //TODO: handle exception
                }
            }
        } 
    }
    
    private static class DealDamage extends Thread{
        DealDamage(){

        }

        @Override
        public void run(){
            if (game_state){
                /*
                try{
                    for (FlyingObjectBase flyingObject : listOfObj) {
                        //TODO 
                        //如果生命值低於0 必須刪掉，若為敵人則加分
                        //若obj超過邊界，刪除
                    }
                }
                catch (InterruptedException e){

                }
                */
            }
        }
    }
}