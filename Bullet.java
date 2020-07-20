package windosdemo;

import java.awt.image.BufferedImage;

/**
 * 子弹类
 * */
public class Bullet extends FlyingObject{
    protected static int speed;
    public Bullet(int x,int y){
        super(x,y,Main.bullet);
        speed = 10 ;
    }
    @Override
    protected void move() {
        this.setY(this.getY() - speed);
    }


}
