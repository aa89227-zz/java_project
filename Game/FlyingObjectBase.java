package Game;

public abstract class FlyingObjectBase 
{
    private int health = 0; // health of object
    private int attack = 1;
    private double px = 0;    // position x
    private double py = 0;    // position y

    private int width = 0;  // object width
    private int height = 0; // object height

    private double mx = 0; // vector x
    private double my = 0; // vector y

    private int ObjectType = 0;

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
    /**
     * 
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setMx(double mx) {
        this.mx = mx;
    }
    public void setMy(double my) {
        this.my = my;
    }
    public void setHealth(int Health){
        health = Health;
    }

    public void addHealth(int off){
        health += off;
    }

    /**
     * 移動 一個單位 * 向量
     * @param   unit 單位
    */
    public void moveUnit(int unit){
        px += unit * mx;
        py += unit * my;
    }
    
    /**
     * 設定 Object 的 position，(x,y)
     * @param x
     * @param y
     */
    public void setPosition(double x, double y){
        px = x;
        py = y;
    }

    final public void setAttack(int num){
        attack = num;
    }

    final public void setVector(double px, double py) {
        this.px = px;
        this.py = py;
    }

    final public void setObjectType(int objectType) {
        ObjectType = objectType;
    }

    final public int getHealth() {
        return health;
    }

    final public double getPx() {
        return px;
    }

    final public double getPy() {
        return py;
    }

    final public int getAttack() {
        return attack;
    }

    /**
     * 
     * @return <code>ObjectType</code>
     */
    final public int getObjectType() {
        return ObjectType;
    }
}