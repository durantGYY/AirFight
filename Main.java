package windosdemo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JPanel{
    protected Hero hero = new Hero();
    protected static int buff = 0;
    protected ArrayList<FlyingObject> flyingObjects = new ArrayList<FlyingObject>();
    protected ArrayList<FlyingObject> flyingObjectBoom = new ArrayList<FlyingObject>();
    public int state;
    public final int START = 0;
    public final int PAUSE = 2;
    public final int GAME_OVER = 3;
    public final int RUNNING = 1;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage hero_ember0;
    public static BufferedImage hero_ember1;
    public static BufferedImage hero_ember2;
    public static BufferedImage hero_ember3;
    public static BufferedImage bee0;
    public static BufferedImage bee_ember0;
    public static BufferedImage bee_ember1;
    public static BufferedImage bee_ember2;
    public static BufferedImage bee_ember3;
    public static BufferedImage enemyPlane;
    public static BufferedImage enemyPlane_ember0;
    public static BufferedImage enemyPlane_ember1;
    public static BufferedImage enemyPlane_ember2;
    public static BufferedImage enemyPlane_ember3;
    public static BufferedImage enemyPlaneBig;
    public static BufferedImage enemyPlaneBig_ember0;
    public static BufferedImage enemyPlaneBig_ember1;
    public static BufferedImage enemyPlaneBig_ember2;
    public static BufferedImage enemyPlaneBig_ember3;
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage backGround;
    public static BufferedImage gameOver;
    public static BufferedImage bullet;
    public static BufferedImage stateB;
    static{
        try{
            //将img 赋值为从硬盘中读取的图片
            hero0 = ImageIO.read(Main.class.getResourceAsStream("pic/hero0.png"));
            hero1 = ImageIO.read(Main.class.getResourceAsStream("pic/hero1.png"));
            hero_ember0 = ImageIO.read(Main.class.getResourceAsStream("pic/hero_ember0.png"));
            hero_ember1 = ImageIO.read(Main.class.getResourceAsStream("pic/hero_ember1.png"));
            hero_ember2 = ImageIO.read(Main.class.getResourceAsStream("pic/hero_ember2.png"));
            hero_ember3 = ImageIO.read(Main.class.getResourceAsStream("pic/hero_ember3.png"));
            bee0 = ImageIO.read(Main.class.getResourceAsStream("pic/bee.png"));
            bee_ember0 = ImageIO.read(Main.class.getResourceAsStream("pic/bee_ember0.png"));
            bee_ember1 = ImageIO.read(Main.class.getResourceAsStream("pic/bee_ember1.png"));
            bee_ember2 = ImageIO.read(Main.class.getResourceAsStream("pic/bee_ember2.png"));
            bee_ember3 = ImageIO.read(Main.class.getResourceAsStream("pic/bee_ember3.png"));
            enemyPlane = ImageIO.read(Main.class.getResourceAsStream("pic/airplane.png"));
            enemyPlane_ember0 = ImageIO.read(Main.class.getResourceAsStream("pic/airplane_ember0.png"));
            enemyPlane_ember1 = ImageIO.read(Main.class.getResourceAsStream("pic/airplane_ember1.png"));
            enemyPlane_ember2 = ImageIO.read(Main.class.getResourceAsStream("pic/airplane_ember2.png"));
            enemyPlane_ember3 = ImageIO.read(Main.class.getResourceAsStream("pic/airplane_ember3.png"));
            enemyPlaneBig = ImageIO.read(Main.class.getResourceAsStream("pic/bigplane.png"));
            enemyPlaneBig_ember0 = ImageIO.read(Main.class.getResourceAsStream("pic/bigplane_ember0.png"));
            enemyPlaneBig_ember1 = ImageIO.read(Main.class.getResourceAsStream("pic/bigplane_ember1.png"));
            enemyPlaneBig_ember2 = ImageIO.read(Main.class.getResourceAsStream("pic/bigplane_ember2.png"));
            enemyPlaneBig_ember3 = ImageIO.read(Main.class.getResourceAsStream("pic/bigplane_ember3.png"));
            backGround = ImageIO.read(Main.class.getResourceAsStream("pic/background.png"));
            pause = ImageIO.read(Main.class.getResourceAsStream("pic/pause.png"));
            start = ImageIO.read(Main.class.getResourceAsStream("pic/start.png"));
            gameOver = ImageIO.read(Main.class.getResourceAsStream("pic/gameover.png"));
            bullet = ImageIO.read(Main.class.getResourceAsStream("pic/bullet.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static final int WIDTH = 400;
    public static final int HEIGHT = 650;
    public static void main(String[] args) {
        //实例化窗口对象,标题为"飞机大战"
        JFrame window = new JFrame("飞机大战");
        //设置窗口可见
        window.setVisible(true);
        //设置窗口的宽和高
        window.setSize(WIDTH,HEIGHT);
        //实例化主类对象(继承了JPanel类)
        Main p = new Main();
        //在窗口中添加画板
        window.add(p);
        //设置窗口始终置顶
        window.setAlwaysOnTop(true);
        //设置窗口居中
        window.setLocationRelativeTo(null);
        //设置关闭窗口为结束程序
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //调用action方法
        p.action();
    }

    //重写paint方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //自定义字体
        Font font1 = new Font("华文新魏",0,20);
        //设置字体1:华文新魏
        g.setFont(font1);
        //设置字体颜色red
        g.setColor(Color.RED);
        //显示背景
        backGround(g);
        //画英雄机
        paintHero(g);
        //画飞行物
        paintFlyingObject(g);
        if(buff == 0){
            //画子弹
            paintBullets(g);
        }else{
            //画双倍火力的子弹
            paintBullets2(g);
        }
        //画状态B(RUNNING==null/PAUSE=pause)
        stateB(g);
        //判断开始or结束
        if(state == START){
            stateA(g);
        }else if(state == GAME_OVER){
            GameOver(g);
        }
        //画飞行物爆炸效果
        boom2(g);
        //显示预设字体的字符串
        g.drawString("SCORE: "+hero.getScore(),15,25);
        g.drawString("LIFE: "+hero.getLife(),15,45);
    }

    //画英雄机的方法
    private void paintHero(Graphics g){
        g.drawImage(hero.getImg(),hero.getX(),hero.getY(),this);
    }
    //画背景方法
    private void backGround(Graphics g){
        g.drawImage(Main.backGround,0,0,this);

    }
    //画开始状态方法
    private void stateA(Graphics g){
        g.drawImage(Main.start,0,0,this);
    }
    //画暂停状态
    private void stateB(Graphics g){
        g.drawImage(Main.stateB,0,0,this);
    }

    private void GameOver(Graphics g){
        g.drawImage(Main.gameOver,0,0,this);
    }
    //画飞行物方法
    private void paintFlyingObject(Graphics g){
        for(int i =0;i < flyingObjects.size(); i++)
        {
            FlyingObject flyingObject = flyingObjects.get(i);
            g.drawImage(flyingObject.getImg(),flyingObject.getX(),flyingObject.getY(),this);
        }
    }
    //画子弹方法
    private void paintBullets(Graphics g){
        for(int i=0; i < hero.bullets1.size(); i++){
            g.drawImage(hero.bullets1.get(i).getImg(),hero.bullets1.get(i).getX(),hero.bullets1.get(i).getY(),this);
        }
    }
    //画双倍火力子弹方法
    private void paintBullets2(Graphics g){
        for(int i=0; i < hero.bullets1.size(); i++){
            g.drawImage(hero.bullets1.get(i).getImg(),hero.bullets1.get(i).getX(),hero.bullets1.get(i).getY(),this);

        }
        for(int i=0; i < hero.bullets2.size(); i++){
            g.drawImage(hero.bullets2.get(i).getImg(),hero.bullets2.get(i).getX(),hero.bullets2.get(i).getY(),this);

        }
    }
    //响应鼠标动作和游戏循环的方法
    public void action(){
        //添加鼠标监听事件
        MouseAdapter a = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                if(state == START){
                    state = RUNNING;
                }else if(state == GAME_OVER){
                    state = RUNNING;
                    hero.setLife(10);
                    hero.score = 0;
                    flyingObjects.clear();
                    hero.bullets1.clear();
                    hero.bullets2.clear();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(state == PAUSE){
                    state = RUNNING;
                    Main.stateB = null;
                    repaint();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(state == RUNNING){
                    state = PAUSE;
                    Main.stateB = Main.pause;
                    repaint();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                hero.moveTo(x,y);
            }
        };
        this.addMouseListener(a); // 处理鼠标点击操作
        this.addMouseMotionListener(a); // 处理鼠标滑动操作
        //构造定时器
        Timer timer = new Timer();
        //设置延时,延时33ms,每33ms刷新一次-30帧
        int interval = 33;
        //Timer的匿名内部类
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(state == RUNNING)
                {
                    enter();
                    FlyAction();
                    hero.shout();
                    bulletsMove();
                    bulletBit();
                    heroHit();
                    buffTime();
                    outOfBounds();
                }
                hero.step();
                repaint();
            }
        },interval,interval);
    }
    //0.99秒生成一个飞行物
    protected int PlaneTime = 0;
    public void enter(){
        PlaneTime++;
        if(PlaneTime % 30 == 0)
        {
            int ran =(int)(Math.random() * 20);
            if(ran == 0){
                //1/20 的几率生成小蜜蜂
                flyingObjects.add(new Bee());
            }else if(ran == 1 || ran == 2){
                //1/10 的几率生成大飞机
                flyingObjects.add(new EnemyPlaneBig());
            }else
                //17/20 的几率生成小飞机
                flyingObjects.add(new EnemyPlane());

        }

    }
    //飞行物移动
    public void FlyAction(){
       for(int i =0; i < flyingObjects.size(); i++){
           flyingObjects.get(i).move();
       }
    }
    //飞行物超界消失
    public void outOfBounds(){
        for(int i = 0; i < flyingObjects.size(); i++){
            if(flyingObjects.get(i).getY() > HEIGHT){
                flyingObjects.remove(i);
                i--;
            }
        }

    }
    //子弹移动方法
    public void bulletsMove(){
        if(buff == 0){
            for(int i = 0; i < hero.bullets1.size(); i++){
                hero.bullets1.get(i).move();
            }
        }
        //双倍火力子弹移动(需要分开计算两发子弹)
        else{
            for(int i = 0; i < hero.bullets1.size(); i++){
                hero.bullets1.get(i).move();
            }
            for(int i = 0; i < hero.bullets2.size(); i++){
                hero.bullets2.get(i).move();
            }
        }
    }
    //爆照算法1 会有卡顿
    protected void boom(FlyingObject flyingObject) {
        for(int i = 1; i < 50000000; i++){
            if(i % 10000000 ==0 && flyingObject instanceof EnemyPlane )
            {
                int k = i/10000000 - 1;
                EnemyPlane imgss = (EnemyPlane)flyingObject;
                imgss.img = imgss.imgs[k];
                repaint();
            }else if(i % 10000000 ==0 && flyingObject instanceof EnemyPlaneBig){
                int k = i/10000000 - 1;
                EnemyPlaneBig imgss = (EnemyPlaneBig)flyingObject;
                imgss.img = imgss.imgs[k];
                repaint();
            }else if(i % 10000000 ==0 && flyingObject instanceof Bee){
                int k = i/10000000 - 1;
                Bee imgss = (Bee) flyingObject;
                imgss.img = imgss.imgs[k];
                repaint();
            }
        }
    }
    //飞行物与英雄机碰撞的判断
    public void heroHit(){
        for(int i = 0; i < flyingObjects.size(); i++){
            if(hero.getX() - flyingObjects.get(i).getX() > -hero.getWidth()
            && hero.getX() - flyingObjects.get(i).getX() < flyingObjects.get(i).getWidth() - 15
            && hero.getY() - flyingObjects.get(i).getY() > -hero.getHeight()
            && hero.getY() - flyingObjects.get(i).getY() < flyingObjects.get(i).getHeight() - 15){
                hero.setLife(hero.getLife()-1);
                boom(flyingObjects.get(i));
                flyingObjects.remove(i--);
                if(hero.getLife() <= 0){
                    state = GAME_OVER;
                }
            }
        }
    }
    //buff时间
    private int buffTime = 0;
    public void buffTime(){
        if(buff != 0){
            buffTime++;
            //buff时间6.6秒
            if(buffTime % 200 == 0){
                buff--;
                Bullet.speed -= 3;
            }
        }
    }

    //子弹击中飞行物判断
    public void bulletBit(){
        if(buff == 0){
            for(int i = 0;i < hero.bullets1.size();i++){
                for(int j = 0; j < flyingObjects.size(); j++)
                {
                    if(hero.bullets1.get(i).getY() - flyingObjects.get(j).getY() < flyingObjects.get(j).getHeight()
                            && hero.bullets1.get(i).getY()- flyingObjects.get(j).getY() > 0
                            && hero.bullets1.get(i).getX()- flyingObjects.get(j).getX() > 0
                            && hero.bullets1.get(i).getX()- flyingObjects.get(j).getX() < flyingObjects.get(j).getWidth())
                    {
                        hero.bullets1.remove(i--);
                        flyingObjects.get(j).blood--;
                        if(flyingObjects.get(j).blood == 0){
                            if(flyingObjects.get(j) instanceof Award){
                                if( ((Award) flyingObjects.get(j)).awards() == Award.ADD_LIFE){
                                    hero.setLife(hero.getLife()+ ((Award) flyingObjects.get(j)).awards());
                                }else{
                                    buff++;
                                }
                                Bullet.speed += ((Award) flyingObjects.get(j)).speedsUp();
                            }
                            //将要爆炸的飞行物放入一个新的集合
                            flyingObjectBoom.add(flyingObjects.get(j));
                            hero.addScore(((Enemy)flyingObjects.get(j)).getScore());
                            flyingObjects.remove(j--);

                        }
                        break;

                    }
                }
            }
        }else{
                for(int i = 0;i < hero.bullets1.size();i++){
                    for(int j = 0; j < flyingObjects.size(); j++)
                    {
                        if(hero.bullets1.get(i).getY() - flyingObjects.get(j).getY() < flyingObjects.get(j).getHeight()
                                && hero.bullets1.get(i).getY()- flyingObjects.get(j).getY() > 0
                                && hero.bullets1.get(i).getX()- flyingObjects.get(j).getX() > 0
                                && hero.bullets1.get(i).getX()- flyingObjects.get(j).getX() < flyingObjects.get(j).getWidth())
                        {
                            hero.bullets1.remove(i--);
                            flyingObjects.get(j).blood--;
                            if(flyingObjects.get(j).getHeight()== Main.bee0.getHeight()){
                                buff++;
                                Bullet.speed += 3;
                            }
                            if(flyingObjects.get(j).blood == 0){
                                /*boom(flyingObjects.get(j));
                                index.add(j);*/
                                if(flyingObjects.get(j) instanceof EnemyPlaneBig){
                                    hero.setLife(hero.getLife() + 1);
                                }
                                flyingObjectBoom.add(flyingObjects.get(j));
                                hero.addScore(((Enemy)flyingObjects.get(j)).getScore());
                                flyingObjects.remove(j--);
                            }
                            break;

                        }
                    }
                }
                for(int i = 0;i < hero.bullets2.size();i++){
                    for(int j = 0; j < flyingObjects.size(); j++)
                    {
                        if(hero.bullets2.get(i).getY() - flyingObjects.get(j).getY() < flyingObjects.get(j).getHeight()
                                && hero.bullets2.get(i).getY()- flyingObjects.get(j).getY() > 0
                                && hero.bullets2.get(i).getX()- flyingObjects.get(j).getX() > 0
                                && hero.bullets2.get(i).getX()- flyingObjects.get(j).getX() < flyingObjects.get(j).getWidth())
                        {
                            hero.bullets2.remove(i--);
                            flyingObjects.get(j).blood--;
                            if(flyingObjects.get(j).getHeight()== Main.bee0.getHeight()){
                                buff++;
                                Bullet.speed += 3;
                            }
                            if(flyingObjects.get(j).blood == 0){
                                /*boom(flyingObjects.get(j));
                                index.add(j);*/
                                if(flyingObjects.get(j) instanceof EnemyPlaneBig){
                                    hero.setLife(hero.getLife() + 1);
                                }
                                flyingObjectBoom.add(flyingObjects.get(j));
                                hero.addScore(((Enemy)flyingObjects.get(j)).getScore());
                                flyingObjects.remove(j--);
                            }
                            break;

                        }
                    }
                }

        }
    }

    //画飞行物的爆炸效果
    private int i = 0;
    private int boomDelay = 0;
    public void boom2(Graphics g){
        boomDelay++;
        if(flyingObjectBoom.size() > 0){
            //遍历集合中的每个飞行物,分别画他们不同的爆炸效果
            for(int k = 0; k < flyingObjectBoom.size(); k++){
                if(flyingObjectBoom.get(k) instanceof EnemyPlane){

                    EnemyPlane imgb1 = (EnemyPlane)flyingObjectBoom.get(k);
                    g.drawImage(imgb1.imgs[i],flyingObjectBoom.get(k).getX(),flyingObjectBoom.get(k).getY(),this);

                }else if(flyingObjectBoom.get(k) instanceof EnemyPlaneBig){

                    EnemyPlaneBig imgb2 = (EnemyPlaneBig) flyingObjectBoom.get(k);
                    g.drawImage(imgb2.imgs[i],flyingObjectBoom.get(k).getX(),flyingObjectBoom.get(k).getY(),this);

                }else if(flyingObjectBoom.get(k) instanceof Bee){

                    Bee imgb3 = (Bee)flyingObjectBoom.get(k);
                    g.drawImage(imgb3.imgs[i],flyingObjectBoom.get(k).getX(),flyingObjectBoom.get(k).getY(),this);
                }

            }
            //画下一帧效果
            i++;
        }
        //判断是否画完
        if(i >= 4){
            i=0;
            for(int k =0; k < flyingObjectBoom.size();k++){
                flyingObjectBoom.remove(k);
            }

        }

    }


}
