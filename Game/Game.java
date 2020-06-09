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
import java.util.Random;
import java.util.List;
import java.awt.Graphics;

public class Game {
    //private static JFrame frame = new JFrame("雷霆戰機");
    private static Panelextend frm = new Panelextend();
    //private static JPanel drawPane = new JPanel();//draw使用
    //private static Graphics g = drawPane.getGraphics();//draw使用

    public static Flight flight = new Flight();
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
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image background = toolkit.getImage("background.jpg");
    private static Image skillImage = toolkit.getImage("bullet/rocket.png");
    public static int round = 0;
    private static boolean skill = false;
    private static boolean exeSkill = false;
    private static JPanel drawPane = new JPanel(){
        @Override
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            //畫背景
            g.drawImage(background, 0, 0,gW, gH,null);
  
            //敵人戰機
            for (FlyingObjectBase flyingObjectBase : enemies) {
                g.drawImage(flyingObjectBase.getImg(),(int)(flyingObjectBase.getPx() - flyingObjectBase.getWidth()/2),
                (int)(flyingObjectBase.getPy() - flyingObjectBase.getHeight()/2),flyingObjectBase.getWidth(),flyingObjectBase.getHeight(),null);}

            //玩家子彈
            for (FlyingObjectBase flyingObjectBase : flightBullets) {
                g.drawImage(flyingObjectBase.getImg(),(int)(flyingObjectBase.getPx() - flyingObjectBase.getWidth()/2),
                (int)(flyingObjectBase.getPy() - flyingObjectBase.getHeight()/2),flyingObjectBase.getWidth(),flyingObjectBase.getHeight(),null);}


            //玩家戰機
            g.drawImage(flight.getImg(),(int)(flight.getPx() - flight.getWidth()/2),
            (int)(flight.getPy() - flight.getHeight()/2),flight.getWidth(),flight.getHeight(),null);

            //敵人子彈
            for (FlyingObjectBase flyingObjectBase : enemyBullets) {
                g.drawImage(flyingObjectBase.getImg(),(int)(flyingObjectBase.getPx() - flyingObjectBase.getWidth()/2),
                (int)(flyingObjectBase.getPy() - flyingObjectBase.getHeight()/2),
                10,30,null);}

            g.setColor(Color.RED); 
             //分數
             g.setFont(new Font(String.valueOf(score), Font.BOLD, 30));
             g.drawString("分數:"+String.valueOf(score), gW - 180, 30);

            //血量
            g.setFont(new Font(String.valueOf(flight.getHealth()),Font.BOLD, 30));
            g.drawString("血量:"+String.valueOf(flight.getHealth()), 20, 30);

            //技能
            g.setColor(Color.YELLOW);
            g.setFont(new Font(String.valueOf((double)(score % 1000) / 10),Font.BOLD, 30));
            if(skill)
                g.drawString("READY", 25, gH-50);
            else
                g.drawString(String.valueOf((double)(score % 1000) / 10)+"%", 25, gH-50);

        }        
    };

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
        Enemy.setInit();
        //frame.setSize(500, 800);
        drawPane.setPreferredSize(new Dimension(gW, gH));//設定panel大小
        //-------------------------------add
        frm.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == ' '){
                    if (skill){
                        exeSkill = true;
                        skill = false;
                    }
                }
            }
        });

        frm.setFocusable(true);
        frm.requestFocusInWindow();
        //--------------------add finish
        Panelextend.container.add(drawPane, "drawpane");
        flight.setWidth(80);
        flight.setHeight(80);
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
                    for (Enemy enemy : enemies) {
                        enemy.resetBullet();
                    }
                    if ((++round) % 10 == 0)
                        flightBullets.addAll(flight.getBullet());
                    
                    if ((new Random().nextInt(10)) == 0){
                        for (Enemy enemy : enemies) {
                            if ((new Random().nextInt(3)) == 0){
                                enemyBullets.addAll(enemy.getBullet());
                            }
                        }
                    }
                    if (exeSkill){
                        for(int i = 0; i < gW / 30; ++i)
                            flightBullets.add(new Bullet(0, i * 30, flight.getPy(), 0, -5, skillImage, 50, 30, 90));
                        exeSkill = false;
                    }
                    
                        
                    if (score > 500 * speed){ 
                        if (speed % 2 == 0)
                            skill = true;
                        speed += 1;
                    }
                    
                    //TODO:
                    //里程數到，釋放enemy
                    double rx = new Random().nextDouble() * gW;
                    int rt = new Random().nextInt(6);
                    if ((new Random().nextInt(20)) == 0){
                        enemies.add(new Enemy(rx, 9, rt));
                    }
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
        drawPane.addMouseMotionListener(new MouseMotionListener(){
            public void mouseDragged(MouseEvent e){}
            public void mouseMoved(MouseEvent e){
                px = e.getX();
                py = e.getY();
            }
            
        });
        
    }

    public static void start()
    {
        score = 0;
        distance = 0;
        flight.setHealth(300);
        flightBullets.clear();
        enemyBullets.clear();
        enemies.clear();
        game_state = true;
        speed = 1;
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
        Over.write(score);
        Score.showscore();
        Panelextend.card.show(Panelextend.container, "Over");
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

        //update
        drawPane.repaint();
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
            boolean isIn = false;
            if ((enemy.getPx() - enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() - enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() - enemy.getHeight()/2 > flight.getPy() - flight.getHeight()/2
                && enemy.getPy() - enemy.getHeight()/2 < flight.getPy() + flight.getHeight()/2)) isIn = true;
            else if ((enemy.getPx() + enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() + enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() - enemy.getHeight()/2 > flight.getPy() - flight.getHeight()/2
                && enemy.getPy() - enemy.getHeight()/2 < flight.getPy() + flight.getHeight()/2)) isIn = true;
            else if ((enemy.getPx() + enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() + enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() + enemy.getHeight()/2 > flight.getPy() - flight.getHeight()/2
                && enemy.getPy() + enemy.getHeight()/2 < flight.getPy() + flight.getHeight()/2)) isIn = true;
            else if ((enemy.getPx() - enemy.getWidth()/2 > flight.getPx() - flight.getWidth()/2 
                && enemy.getPx() - enemy.getWidth()/2 < flight.getPx() + flight.getWidth()/2
                && enemy.getPy() + enemy.getHeight()/2 > flight.getPy() - flight.getHeight()/2
                && enemy.getPy() + enemy.getHeight()/2 < flight.getPy() + flight.getHeight()/2)) isIn = true;
            if (isIn){
                enemy.addHealth(-(flight.getAttack()));
                flight.addHealth(-(enemy.getAttack()));
            }
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
                score += enemies.get(i).getPoint();
                enemies.remove(i);
                --i;
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
            enemy.moveUnit(speed);
        }
        for (FlyingObjectBase bullet : enemyBullets) {
            bullet.moveUnit(speed);
        }
        for (FlyingObjectBase bullet : flightBullets) {
            bullet.moveUnit(speed);
        }
    }
}