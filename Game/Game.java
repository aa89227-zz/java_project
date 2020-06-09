package Game;
import javax.swing.*;
import java.lang.Thread;
import java.sql.Time;
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;//圖片


import javax.sound.sampled.AudioInputStream;//音樂
import java.io.*;
import javax.sound.sampled.*;//音樂
import java.util.ArrayList;
import java.awt.Graphics;

public class Game {
    //private static JFrame frame = new JFrame("雷霆戰機");
    private static Panelextend frm = new Panelextend();
    private static JPanel drawPane = new JPanel();//draw使用
    private static Graphics g = drawPane.getGraphics();//draw使用
    private static MoveFlight moveFlight = new MoveFlight(); // thread of detect mouse position

    private static Flight flight = new Flight();
    private static ArrayList<Bullet> flightBullets = new ArrayList<Bullet>(); 
    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>(); 
    private static ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>(); 

    private static Timer timer;
    private static boolean game_state = false; //true if game start

    private static Point point; //滑鼠座標
    private static int px;
    private static int py;
    private static int score; //分數
    private static int distance; //里程數
    private static int speed = 1; //遊戲速度
    final private static int gW = 500; //screen width
    final private static int gH = 800; //screen height
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
        frm.setVisible(true);

        //frame.setSize(500, 800);
        drawPane.setPreferredSize(new Dimension(gW, gH));//設定panel大小

        Panelextend.container.add(drawPane, "drawpane");

        /**
         * timer
         */
        timer = new Timer(30,
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    checkDamage();
                    removeLowerHealth();
                    removeOuter();
                    ++distance;
                    ++score;
                    flight.setPosition(px, py);
                    flight.resetBullet();
                    flightBullets.addAll(flight.getBullet());
                    for (Enemy enemy : enemies) {
                        enemyBullets.addAll(enemy.getBullet());
                    }
                    //TODO:
                    //里程數到，釋放enemy
                    moveUnit();
                    draw();
                    if (flight.getHealth() < 0)
                        gameover();
                }
            });
        timer.setInitialDelay(0);
        //frame.setVisible(true);
        //frame.setResizable(false);
        music();
        
        moveFlight.start();
    }

    public static void start()
    {
        score = 0;
        distance = 0;
        game_state = true;
        timer.start();
        
        return;
    }

    private static void gameover()
    {
        //TODO:
        // - game_state 設為 false
        // - 顯示結算畫面
        //      * 分數(計時器)
        //      * 殺死的 enemy
        //      * 按鈕 => 回到標題畫面
        // - 停止計時器
        game_state = false;
        timer.stop();
    }

    /** 
     * 畫背景
     * 畫子彈(enemyBullets, flightBullets)
     * 畫戰機(flight)
     * 畫敵人(enemies)
     */
    private static void draw(){
        //TODO:
        //draw on drawpane
        //參數 drawImage(圖片,x座標,y座標,寬,高,null)

        //玩家戰機 
        g.drawImage(flight.getImg(),(int)flight.getPx(),(int)flight.getPy(),20,35,null);
        
        //敵人戰機
        for (FlyingObjectBase flyingObjectBase : enemies) {
            g.drawImage(flyingObjectBase.getImg(),(int)flyingObjectBase.getPx(),
                        (int)flyingObjectBase.getPy(),20,35,null);}

        //玩家子彈
        for (FlyingObjectBase flyingObjectBase : flightBullets) {
            g.drawImage(flyingObjectBase.getImg(),(int)flyingObjectBase.getPx(),
                        (int)flyingObjectBase.getPy(),10,30,null);}

        //敵人子彈
        for (FlyingObjectBase flyingObjectBase : enemyBullets) {
            g.drawImage(flyingObjectBase.getImg(),(int)flyingObjectBase.getPx(),
                        (int)flyingObjectBase.getPy(),10,30,null);}
        //寫完但不確定
    }
    
    /**
     * 檢查是否超出邊界
     * @param flyingObject
     * @return <code>true</code> if <code>flyingObject</code> out of border
     */
    private static boolean isOutOfBorder(FlyingObjectBase flyingObject){
        if (flyingObject.getPx() + flyingObject.getWidth() > 0) return false;
        else if (flyingObject.getPx() - flyingObject.getWidth() < gW) return false;
        else if (flyingObject.getPy() + flyingObject.getHeight() > 0) return false;
        else if (flyingObject.getPy() - flyingObject.getHeight() < gH) return false;
        return true;
    } 
    
    /**
     * 得到滑鼠座標
     */
    private static class MoveFlight extends Thread {
        
        MoveFlight()
        {
            
        }

        @Override
        public void run() {
            if (game_state){
                point = drawPane.getLocation();
                px = (int)point.getX();
                py = (int)point.getY();
            }
        }
    }
    
    /**
     * 把碰撞到的扣掉生命值
     */
    private static void checkDamage(){
        for (FlyingObjectBase enemy : enemies) {
            for (FlyingObjectBase bullet : flightBullets) {
                if (bullet.getPx() < enemy.getPx() - enemy.getWidth()/2) continue;
                else if (bullet.getPx() > enemy.getPx() + enemy.getWidth()/2) continue;
                else if (bullet.getPy() < enemy.getPy() - enemy.getHeight()/2) continue;
                else if (bullet.getPy() > enemy.getPy() + enemy.getHeight()/2) continue;
                bullet.addHealth(-(enemy.getAttack()));
                enemy.addHealth(-(bullet.getAttack()));
            }
            if ((enemy.getPx() - enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() - enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() - enemy.getHeight()/2 > flight.getPx() - flight.getWidth()/2
                && enemy.getPy() - enemy.getHeight()/2 < flight.getPx() + flight.getWidth()/2)) continue;
            else if ((enemy.getPx() + enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() + enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() - enemy.getHeight()/2 > flight.getPx() - flight.getWidth()/2
                && enemy.getPy() - enemy.getHeight()/2 < flight.getPx() + flight.getWidth()/2)) continue;
            else if ((enemy.getPx() + enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() + enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() + enemy.getHeight()/2 > flight.getPx() - flight.getWidth()/2
                && enemy.getPy() + enemy.getHeight()/2 < flight.getPx() + flight.getWidth()/2)) continue;
            else if ((enemy.getPx() - enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() - enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() + enemy.getHeight()/2 > flight.getPx() - flight.getWidth()/2
                && enemy.getPy() + enemy.getHeight()/2 < flight.getPx() + flight.getWidth()/2)) continue;
            enemy.addHealth(-(flight.getAttack()));
            flight.addHealth(-(enemy.getAttack()));
        }
        for (FlyingObjectBase bullet : enemyBullets) {
            if (bullet.getPx() < flight.getPx() - flight.getWidth()/2) continue;
            else if (bullet.getPx() > flight.getPx() + flight.getWidth()/2) continue;
            else if (bullet.getPy() < flight.getPy() - flight.getHeight()/2) continue;
            else if (bullet.getPy() > flight.getPy() + flight.getHeight()/2) continue;
            bullet.addHealth(-(flight.getAttack()));
            flight.addHealth(-(bullet.getAttack()));
        }
    }

    /**
     * 把生命值低於0的移除
     */
    private static void removeLowerHealth(){
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealth() < 0){
                enemies.remove(i);
                --i;
                score += enemies.get(i).getPoint();
            }
        }
        for (int i = 0; i < flightBullets.size(); i++) {
            if (flightBullets.get(i).getHealth() < 0){
                flightBullets.remove(i);
                --i;
            }
        }
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (enemyBullets.get(i).getHealth() < 0){
                enemyBullets.remove(i);
                --i;
            }
        }
    }
    /**
     * 把超出邊界的移除
     */
    private static void removeOuter(){
        for (int i = 0; i < enemies.size(); i++) {
            if (isOutOfBorder(enemies.get(i))){
                enemies.remove(i);
            }
        }
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (isOutOfBorder(enemyBullets.get(i))){
                enemyBullets.remove(i);
            }
        }
        for (int i = 0; i < flightBullets.size(); i++) {
            if (isOutOfBorder(flightBullets.get(i))){
                flightBullets.remove(i);
            }
        }
    } 

    private static void moveUnit(){
        for (FlyingObjectBase enemy : enemies) {
            enemy.moveUnit(1);
        }
        for (FlyingObjectBase bullet : enemyBullets) {
            bullet.moveUnit(1);
        }
        for (FlyingObjectBase bullet : flightBullets) {
            bullet.moveUnit(1);
        }
    }
}