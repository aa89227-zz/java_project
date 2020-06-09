package Game;
import java.util.ArrayList;
import java.awt.*;
public class Flight extends FlyingObjectBase
{
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image img1 = toolkit.getImage("bullet/bullet1.png");
    Image img2 = toolkit.getImage("bullet/bullet2.png");
    Image img3 = toolkit.getImage("bullet/bullet3.png");
    Image img4 = toolkit.getImage("bullet/bullet4.png");
    Image img5 = toolkit.getImage("bullet/bullet5.png");
    private int FlightType = 0;
    private static ArrayList<ArrayList<Bullet>> bullets = new ArrayList<ArrayList<Bullet>>();
    Flight(){
        ArrayList<Bullet> bullet = new ArrayList<Bullet>();

        bullet.add(new Bullet(0, 1, 0, -5, toolkit.getImage("bullet/bullet1.png"), 4));
        bullet.add(new Bullet(20, 1, 0, -5, toolkit.getImage("bullet/bullet1.png"), 4));
        bullet.add(new Bullet(-20, 1, 0, -5, toolkit.getImage("bullet/bullet1.png"), 4));
        bullet.add(new Bullet(40, 1, 0, -5, toolkit.getImage("bullet/bullet1.png"), 4));
        bullet.add(new Bullet(-40, 1, 0, -5, toolkit.getImage("bullet/bullet1.png"), 4));
        bullets.add(bullet);
        bullet = new ArrayList<Bullet>();

        bullet.add(new Bullet(0, 1, 0, -8, toolkit.getImage("bullet/bullet2.png"), 8, 30, 30));
        bullet.add(new Bullet(20, 1, -8, -8, toolkit.getImage("bullet/bullet2.png"), 8, 30, 30));
        bullet.add(new Bullet(-20, 1, 8, -8, toolkit.getImage("bullet/bullet2.png"), 8, 30, 30));
        bullets.add(bullet);
        bullet = new ArrayList<Bullet>();

        bullet.add(new Bullet(0, 1, 0, -5, toolkit.getImage("bullet/bullet1.png"), 2));
        bullets.add(bullet);
        bullet = new ArrayList<Bullet>();

        bullet.add(new Bullet(0, 1, 0, -5, toolkit.getImage("bullet/bullet1.png"), 2));
        bullets.add(bullet);
        bullet = new ArrayList<Bullet>();

        setImg(toolkit.getImage("picture/1.png"));
        setHealth(300);
        setAttack(30);
        /*
        bullet.add(new Bullet(0, 3, 0, 5, img3));
        bullet.add(new Bullet(0, 3, 0, 5, img4));
        */
    }

    public ArrayList<Bullet> getBullet(){
        ArrayList<Bullet> buffer = new ArrayList<Bullet>();
        ArrayList<Bullet> a = bullets.get(FlightType);
        for (Bullet bullet : a) {
            buffer.add(new Bullet(bullet));
        }
        return buffer;
    }
    public void setFlightType(int flightType){
        FlightType = flightType;
        setImg(toolkit.getImage("picture/" + (FlightType + 1) + ".png"));
    }

    public void resetBullet(){
        for(int i = 0; i < bullets.get(FlightType).size(); ++i){
            bullets.get(FlightType).get(i).resetPos(this.getPx(), this.getPy());;
        }
    }
}                            