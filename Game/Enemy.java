package Game;

import java.util.ArrayList;
import java.awt.*;
import java.awt.Toolkit;
public class Enemy extends FlyingObjectBase
{

    int EnemyType = 0;
    private final ArrayList<Bullet> bullet0 = new ArrayList<Bullet>();
    private final ArrayList<Bullet> bullet1 = new ArrayList<Bullet>();
    private final ArrayList<Bullet> bullet2 = new ArrayList<Bullet>();
    private final ArrayList<Bullet> bullet3 = new ArrayList<Bullet>();
    private final ArrayList<Bullet> bullet4 = new ArrayList<Bullet>();
    private final ArrayList<Bullet> bullet5 = new ArrayList<Bullet>();
    private final ArrayList<Bullet> bullet6 = new ArrayList<Bullet>();
    private static Image img0;
    private static Image img1;
    private static Image img2;
    private static Image img3;
    private static Image img4;
    private static Image img5;
    private static Image img6;
    
    ArrayList<Bullet> bullet; //從bullet_n 複製，用於shoot
    Enemy(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        img0 = toolkit.getImage("enemy/Duterte.png");
        img1 = toolkit.getImage("enemy/Huang.png");
        img2 = toolkit.getImage("enemy/King.png");
        img3 = toolkit.getImage("enemy/Liu.png");
        img4 = toolkit.getImage("enemy/Puting.png");
        img5 = toolkit.getImage("enemy/Trump.png");
        img6 = toolkit.getImage("enemy/Xi.png");
        bullet.add(new Bullet(-5, 3, 0, 5, img1));
        bullet.add(new Bullet(5, 3, 0, 5, img2));
        //bullets.add(bullet);
    }
    public ArrayList<Bullet> getBullet(){
        return bullet;
    }
    public void setEnemyType(int flightType){
        EnemyType = flightType;
        if (EnemyType == 0){
            bullet = bullet0;
            setImg(img0);
        }
        else if (EnemyType == 1){
            bullet = bullet1;
            setImg(img1);
        }
        else if (EnemyType == 2){
            bullet = bullet2;
            setImg(img2);
        }
        else if (EnemyType == 3){
            bullet = bullet3;
            setImg(img3);
        }
        else if (EnemyType == 4){
            bullet = bullet4;
            setImg(img4);
        }
        else if (EnemyType == 5){
            bullet = bullet5;
            setImg(img5);
        }
        else if (EnemyType == 6){
            bullet = bullet6;
            setImg(img6);
        }
    }
    //TODO:
    //制定一個BOSS與小怪的範圍
    //與Flight一樣設定不同的子彈類型
}