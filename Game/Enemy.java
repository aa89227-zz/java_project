package Game;

import java.util.ArrayList;


import java.awt.*;
import java.awt.Toolkit;
public class Enemy extends FlyingObjectBase
{

    int EnemyType = 0;
    private static ArrayList<Bullet> bullet0 = new ArrayList<Bullet>();
    private static ArrayList<Bullet> bullet1 = new ArrayList<Bullet>();
    private static ArrayList<Bullet> bullet2 = new ArrayList<Bullet>();
    private static ArrayList<Bullet> bullet3 = new ArrayList<Bullet>();
    private static ArrayList<Bullet> bullet4 = new ArrayList<Bullet>();
    private static ArrayList<Bullet> bullet5 = new ArrayList<Bullet>();
    private static ArrayList<Bullet> bullet6 = new ArrayList<Bullet>();
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Image img0 = toolkit.getImage("enemy/Duterte.png");
    public static Image img1 = toolkit.getImage("enemy/Huang.png");
    public static Image img2 = toolkit.getImage("enemy/King.png");
    public static Image img3 = toolkit.getImage("enemy/Liu.png");
    public static Image img4 = toolkit.getImage("enemy/Puting.png");
    public static Image img5 = toolkit.getImage("enemy/Trump.png");
    public static Image img6 = toolkit.getImage("enemy/Xi.png");
    
    ArrayList<Bullet> bullet; //從bullet_n 複製，用於shoot
    public static void setInit(){
        bullet0.add(new Bullet(10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet2.png"), 5));
        bullet0.add(new Bullet(-10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet2.png"), 5));

        bullet1.add(new Bullet(10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet3.png"), 5));
        bullet1.add(new Bullet(-10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet3.png"), 5));

        bullet2.add(new Bullet(10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet2.png"), 5));
        bullet2.add(new Bullet(-10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet2.png"), 5));

        bullet3.add(new Bullet(10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet3.png"), 5));
        bullet3.add(new Bullet(-10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet3.png"), 5));

        bullet4.add(new Bullet(10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet4.png"), 5));
        bullet4.add(new Bullet(-10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet4.png"), 2));

        bullet5.add(new Bullet(10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet5.png"), 5));
        bullet5.add(new Bullet(-10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet5.png"), 2));

        bullet6.add(new Bullet(10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet5.png"), 5));
        bullet6.add(new Bullet(-10, 30, 0, 5, toolkit.getImage("enemyBullet/bullet5.png"), 2));
    }
    Enemy(double px, double py, int enemyType){
        super.setPosition(px, py);
        setEnemyType(enemyType);
    }
    public ArrayList<Bullet> getBullet(){
        ArrayList<Bullet> buffer = new ArrayList<Bullet>();
        for (Bullet b : bullet) {
            buffer.add(new Bullet(b));
        }
        return buffer;
    }
    public void setEnemyType(int enemyType){
        EnemyType = enemyType;
        super.setHeight(80);
        super.setWidth(80);
        if (EnemyType == 0){
            bullet = bullet0;
            setImg(img0);
            super.setHealth(40);
            super.setAttack(60);
            super.setPoint(100);
            super.setMx(0);
            super.setMy(1);
        }
        else if (EnemyType == 1){
            bullet = bullet1;
            setImg(img1);
            super.setHealth(30);
            super.setAttack(60);
            super.setPoint(100);
            super.setMx(0);
            super.setMy(1);
        }
        else if (EnemyType == 2){
            bullet = bullet2;
            setImg(img2);
            super.setHealth(35);
            super.setAttack(60);
            super.setPoint(100);
            super.setMx(0);
            super.setMy(1);
        }
        else if (EnemyType == 3){
            bullet = bullet3;
            setImg(img3);
            super.setHealth(45);
            super.setAttack(60);
            super.setPoint(50);
            super.setMx(0);
            super.setMy(1);
        }
        else if (EnemyType == 4){
            bullet = bullet4;
            setImg(img4);
            super.setHealth(30);
            super.setAttack(60);
            super.setPoint(100);
            super.setMx(0);
            super.setMy(1);
        }
        else if (EnemyType == 5){
            bullet = bullet5;
            setImg(img5);
            super.setHealth(35);
            super.setAttack(60);
            super.setPoint(100);
            super.setMx(0);
            super.setMy(1);
        }
        else if (EnemyType == 6){
            bullet = bullet6;
            setImg(img6);
            super.setHealth(40);
            super.setAttack(60);
            super.setPoint(100);
            super.setMx(0);
            super.setMy(1);
        }
    }

    public void resetBullet(){
        for(int i = 0; i < bullet.size(); ++i){
            bullet.get(i).resetPos(this.getPx(), this.getPy());;
        }
    }
    //TODO:
    //制定一個BOSS與小怪的範圍
    //與Flight一樣設定不同的子彈類型
}