package windosdemo;

import java.awt.image.BufferedImage;
/**
 * 飞行物类
 * */
public abstract class FlyingObject {
    private int x;
    private int y;
    protected BufferedImage img;
    private int width;
    private int height;
    protected int blood;
    protected abstract void move();
    protected BufferedImage[] imgs;


    protected FlyingObject(int x, int y, BufferedImage img){
        this.img = img;
        this.height = img.getHeight();
        this.width = img.getWidth();
        this.x = x;
        this.y = y;

    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
