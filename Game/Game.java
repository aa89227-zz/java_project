package Game;

import javax.swing.*;

import java.lang.Thread;
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;
public class Game {
    private static JFrame frame = new JFrame("雷霆戰機");

    private static JButton bt_start = new JButton("開始遊戲");
    private static JButton bt_option = new JButton("選項");
    private static JButton bt_exit = new JButton("結束遊戲");

    private static boolean game_state = false;
    public static void main(String[] args)
    {
        MoveFlight mf = new MoveFlight();
        frame.setSize(500, 800);
        frame.setLayout(new GridLayout(1, 3));
        frame.addMouseListener(new MoveFlight());

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
            
        bt_start.setSize(200, 100);
        bt_option.setSize(200, 100);

        frame.add(bt_start);
        frame.add(bt_option);
        frame.add(bt_exit);
        frame.setVisible(true);
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
    }

    private static class MoveFlight extends MouseAdapter {
        MoveFlight()
        {
            
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
            if (game_state = false) return;
            //TODO:移動滑鼠時重繪戰機
        }
    }
}