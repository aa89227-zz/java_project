package Game;
import java.awt.*;
public final class Bullet extends FlyingObjectBase implements Cloneable
{
    private double offx;
    private double offy;

    Bullet(){
        offx = 0;
        offx = 0;
    }

    Bullet(double offx, double offy, double mx, double my, Image img, int attack){
        this.offx = offx;
        this.offy = offy;
        super.setMx(mx);
        super.setMy(my);
        super.setImg(img);
        super.setAttack(attack);
        super.setWidth(10);
        super.setHeight(30);
    }
    Bullet(double offx, double offy, double mx, double my, Image img, int attack, int width, int height){
        this.offx = offx;
        this.offy = offy;
        super.setMx(mx);
        super.setMy(my);
        super.setImg(img);
        super.setAttack(attack);
        super.setWidth(width);
        super.setHeight(height);
    }


    Bullet(Bullet bullet){
        this.offx = bullet.getOffx();
        this.offx = bullet.getOffy();
        this.setPosition(bullet.getPx(), bullet.getPy());
        this.setImg(bullet.getImg());
        this.setMx(bullet.getMx());
        this.setMy(bullet.getMy());
        super.setAttack(bullet.getAttack());
        super.setWidth(bullet.getWidth());
        super.setHeight(bullet.getHeight());
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

   @Override
   protected Object clone() throws CloneNotSupportedException {
       // TODO Auto-generated method stub
       return super.clone();
   }
}