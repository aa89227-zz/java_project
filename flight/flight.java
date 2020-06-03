package flight;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import java.lang.Thread;

public class flight
{
    private long score;
    private long health;
    private int level;    

    private static JFrame frame = new JFrame("雷霆戰機");
    private static Flight_Body f_body;
    public static void main(String[] args)
    {
        f_body = new Flight_Body(2); 
        frame.setVisible(true);
    }

    /*
        - get mouse coodinate and repaint the flight body
        - find if body contact bullet or enemy
        
        * type : flight's shape
    */
    static class Flight_Body extends Thread
    {
        private int type;
        Flight_Body()
        {
            type = 0;
        }

        Flight_Body(int t)
        {
            type = t;
        }

        public void run()
        {
            
        }

        // int x, y : coordinate of bullet
        // return true if get damaged
        public boolean getDamage(int x, int y)
        {
            
            return false;
        }
    }

}