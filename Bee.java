package windosdemo;

import java.awt.image.BufferedImage;

/**
 * 蜜蜂类
 * */
public class Bee extends FlyingObject implements Award,Enemy{
    private int x_speed;
    private int y_speed;
    private int awardType;
    public Bee(){
        super((int)(Math.random()*(Main.WIDTH- Main.bee0.getWidth())),-Main.bee0.getHeight(),Main.bee0);
        x_speed = 2;
        y_speed = 2;
        this.blood = 1;
        awardType = (int)(Math.random() * 2);
        this.imgs = new BufferedImage[]{Main.bee_ember0,Main.bee_ember1,Main.bee_ember2,Main.bee_ember3};
    }
    @Override
    protected void move() {
        this.setX(this.getX() + x_speed);
        this.setY(this.getY() + y_speed);
        if(this.getX() >= Main.WIDTH - this.getWidth()){
            x_speed = -x_speed;
        }
    }


    @Override
    public int speedsUp() {
        return 3;
    }

    @Override
    public int awards() {
        return awardType;
    }

    @Override
    public int getScore() {
        return ADD_SCORE_A;
    }
}
