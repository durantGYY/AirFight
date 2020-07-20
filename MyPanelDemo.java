package windosdemo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * 自定义的绘画类
 * */

/**


public class MyPanelDemo extends JPanel {
    protected Hero hero = new Hero();
    //自定义绘画方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //自定义字体
        Font font1 = new Font("华文新魏",0,20);
        Font font2 = new Font("华文彩云",0,45);
        //设置字体1:华文新魏
        g.setFont(font1);
        //设置字体颜色red
        g.setColor(Color.RED);
        //显示背景
        backGround(g);
        //显示预设字体的字符串
        paintHero(g);
        g.drawString("SCORE: "+hero.getScore(),15,25);
        g.drawString("LIFE: "+hero.getLife(),15,45);
        //设置字体2:华文彩云
        g.setFont(font2);
        //设置字体颜色orange
        g.setColor(Color.orange);
        g.drawString("飞机大战",100,325);
        //画英雄机

        paintBee(g);
        paintEnemyPlane(g);
        paintEnemyPlaneBig(g);
        PaintBullet(g);


    }

    private void paintHero(Graphics g){

        g.drawImage(hero.getImg(),hero.getX(),hero.getY(),this);

    }

    private void backGround(Graphics g){
        g.drawImage(Main.backGround,0,0,this);

    }

    private void paintBee(Graphics g){
        Bee bee = new Bee();
        g.drawImage(bee.getImg(),bee.getX(),bee.getY(),this);
    }

    private void paintEnemyPlane(Graphics g){
        EnemyPlane enemyPlane = new EnemyPlane();
        g.drawImage(enemyPlane.getImg(),enemyPlane.getX(),enemyPlane.getY(),this);
    }

    private void paintEnemyPlaneBig(Graphics g){
        EnemyPlaneBig enemyPlaneBig = new EnemyPlaneBig();
        g.drawImage(enemyPlaneBig.getImg(),enemyPlaneBig.getX(),enemyPlaneBig.getY(),this);
    }

    private void PaintBullet(Graphics g){
        Hero hero = new Hero();
        Bullet bullet = new Bullet(hero.getX()+hero.getWidth()/2,hero.getY());
        g.drawImage(bullet.getImg(),bullet.getX(),bullet.getY(),this);
    }



}
*/