package windosdemo;

import java.awt.image.BufferedImage;

/**
 * 大敌机类
 * */
public class EnemyPlaneBig extends FlyingObject implements Award,Enemy{
    private int speed;
    public  EnemyPlaneBig(){
        super((int)(Math.random()*(Main.WIDTH - Main.enemyPlaneBig.getWidth())) ,-Main.enemyPlaneBig.getHeight(),Main.enemyPlaneBig);
        this.blood = 5;
        speed = 1;
        this.imgs = new BufferedImage[]{Main.enemyPlaneBig_ember0,Main.enemyPlaneBig_ember1,Main.enemyPlaneBig_ember2,Main.enemyPlaneBig_ember3};
    }

    @Override
    protected void move() {
        this.setY(this.getY() + speed);
    }

    @Override
    public int awards() {
        return ADD_LIFE;
    }

    @Override
    public int speedsUp() {
        return 0;
    }

    @Override
    public int getScore() {
        return ADD_SCORE_B;
    }
}
