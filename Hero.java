package windosdemo;

import java.util.ArrayList;

/**
 * 英雄机类
 * */
public class Hero extends FlyingObject{
    private int life;
    protected int score;

    public Hero(){
        super(150,350,Main.hero0);
        life = 10;
    }

    @Override
    protected void move() {

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int a){
        this.setScore(this.getScore() + a);
    }

    protected ArrayList<Bullet> bullets1 = new ArrayList();
    protected ArrayList<Bullet> bullets2 = new ArrayList();
    private int shoutTime = 0;
    public void shout(){
        shoutTime++;
        if(shoutTime % 10 == 0)
        {
            if(Main.buff == 0)
            {
                bullets1.add(new Bullet(this.getX()+ Main.hero0.getWidth()/2 - Main.bullet.getWidth()/2,getY() - 10));
            }else{
                bullets1.add(new Bullet(this.getX()+ Main.hero0.getWidth()/2 -Main.bullet.getWidth()/2 - 30,getY() - 10));
                bullets2.add(new Bullet(this.getX()+ Main.hero0.getWidth()/2 -Main.bullet.getWidth()/2 + 30,getY() - 10));
            }
        }
    }

    public void moveTo(int x,int y){
        this.setX(x - Main.hero0.getWidth()/2);
        this.setY(y - Main.hero0.getHeight()/2);
    }

    public void step(){
            if(System.currentTimeMillis()/200 % 2 != 0)
            {
                this.setImg(Main.hero0);
            }else{
                this.setImg(Main.hero1);
            }
    }

}
