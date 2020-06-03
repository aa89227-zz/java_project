package Game;

public abstract class FlyingObjectBase 
{
    private int health = 0; // health of object

    private int px = 0;    // position x
    private int py = 0;    // position y

    private int width = 0;  // object width
    private int height = 0; // object height

    private int mx = 0; // vector x
    private int my = 0; // vector y

    FlyingObjectBase()
    {
        health = 0;
        px = 0;
        py = 0;
        width = 0;
        height = 0;
        mx = 0;
        my = 0;
    }

    FlyingObjectBase(int Health, int Width, int Height, int p_x, int p_y, int m_x, int m_y)
    {
        health = Health;
        px = p_x;
        py = p_y;
        width = Width;
        height = Height;
        mx = m_x;
        my = m_y;
    }

    void setHealth(int Health){
        health = Health;
    }

    void addHealth(int off){
        health += off;
    }

    void moveUnit(int unit){
        px += unit * mx;
        py += unit * my;
    }

    void setPosition(int x, int y){
        px = x;
        py = y;
    }

    
}