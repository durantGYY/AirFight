package windosdemo;

import java.awt.image.BufferedImage;

/**
 * 敌机类
 * */
public class EnemyPlane extends FlyingObject implements Enemy{
    private int speed;
    public  EnemyPlane(){
        super((int)(Math.random()*(Main.WIDTH- Main.enemyPlane.getWidth())) ,-Main.enemyPlane.getHeight(),Main.enemyPlane);
        speed = 2;
        this.blood = 1;
        this.imgs = new BufferedImage[]{Main.enemyPlane_ember0, Main.enemyPlane_ember1,Main.enemyPlane_ember2, Main.enemyPlane_ember3};
    }
    @Override
    protected void move() {
        this.setY(this.getY() + speed);
    }


    @Override
    public int getScore() {
        return ADD_SCORE_A;
    }
}
