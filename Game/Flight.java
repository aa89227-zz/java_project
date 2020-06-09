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
    private ArrayList<ArrayList<Bullet>> bullets = new ArrayList<ArrayList<Bullet>>();
    Flight(){
        ArrayList<Bullet> bullet = new ArrayList<>();

        bullet.add(new Bullet(-5, 3, 0, 5, img1));
        bullet.add(new Bullet(5, 3, 0, 5, img2));
        bullets.add(bullet);
        bullet.clear();
        int a = (FlightType + 1);
        setImg(toolkit.getImage("picture/1.png"));
        /*
        bullet.add(new Bullet(0, 3, 0, 5, img3));
        bullet.add(new Bullet(0, 3, 0, 5, img4));
        */
    }

    public ArrayList<Bullet> getBullet(){
        return bullets.get(FlightType);
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