package Game;

import javax.swing.*;
import java.lang.Thread;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {
    private static JFrame frame = new JFrame("雷霆戰機");
    private static boolean game_state = false;
    public static void main(String[] args)
    {
        MoveFlight mf = new MoveFlight();
        frame.addMouseListener(new MoveFlight());
        frame.setVisible(true);
    }

    private void start()
    {
        //TODO:
        // - sleep 幾秒，顯示起始畫面
        // - game_state 設為 true
        // - 啟動 thread
        //      * 跑計時器(計算路程)
        //      * 讀取計時器，到一定的路程會召喚 enemy
        //      * 計算是否戰機有受到傷害

    }

    private void gameover()
    {
        //TODO:
        //遊戲結束，結算畫面 
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