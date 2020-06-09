package Game;
import java.awt.*;
public class Bullet extends FlyingObjectBase
{
    private double offx;
    private double offy;

    Bullet(){
        offx = 0;
        offx = 0;
    }

    Bullet(double offx, double offy, double mx, double my, Image img){
        super.setMx(mx);
        super.setMy(my);
        super.setImg(img);
    }
    public double getOffx() {
        return offx;
    }

    public double getOffy() {
        return offy;
    }

    public void resetPos(double x, double y){
        super.setPosition(x + offx, y + offy);
    }
}