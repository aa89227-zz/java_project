package Game;
import java.util.ArrayList;

public class Flight extends FlyingObjectBase
{
    int FlightType = 0;
    final int MAXTYPE = 5;
    final ArrayList<Bullet> bullet0 = new ArrayList<Bullet>();
    final ArrayList<Bullet> bullet1 = new ArrayList<Bullet>();
    final ArrayList<Bullet> bullet2 = new ArrayList<Bullet>();
    final ArrayList<Bullet> bullet3 = new ArrayList<Bullet>();
    final ArrayList<Bullet> bullet4 = new ArrayList<Bullet>();

    ArrayList<Bullet> bullet; //從bullet_n 複製，用於shoot
    Flight(){
        FlightType = 0;
    }

    public void NextType(){
        ++FlightType;
        if (FlightType == MAXTYPE)
            FlightType = 0;
        draw();
    }
    public void LastType(){
        --FlightType;
        if (FlightType == -1)
            FlightType = MAXTYPE - 1;
        draw();
    }
    public void draw(){
        //TODO:重繪戰機於 (px, py)
    }
    public void shoot(){
        //TODO:召喚子彈
        //記得，每個子彈都是基於戰機的座標，因此需要修改子彈座標(加上offset)
        //繪製時呼叫Bullet.draw()
    }
}