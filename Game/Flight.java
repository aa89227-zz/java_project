package Game;
import java.util.ArrayList;

public class Flight extends FlyingObjectBase
{

    private int FlightType = 0;
    private ArrayList<ArrayList<Bullet>> bullets = new ArrayList<ArrayList<Bullet>>();
    Flight(){
        ArrayList<Bullet> bullet = new ArrayList<>();
    }

    public ArrayList<Bullet> getBullet(){
        return bullets.get(FlightType);
    }
    public void setFlightType(int flightType){
        FlightType = flightType;
    }
}                            