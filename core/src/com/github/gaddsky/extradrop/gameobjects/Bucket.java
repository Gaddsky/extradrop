package com.github.gaddsky.extradrop.gameobjects;

import com.badlogic.gdx.math.Rectangle;


public class Bucket {
    private int x, y;
    private int width = 64;
    private int height = 64;
    private int speed;
    private int targetX;
    private Rectangle bound;
    private int dropsGathered;


    public Bucket(int x, int y, int speed) {
        this.x = targetX = x;
        this.y = y;
        this.speed = speed;
        bound = new Rectangle(x, y, width, height);
    }

    public void setTargetX(int x) {
        this.targetX = x;
}

    public void update(float delta) {
        if (Math.abs(targetX - x) < (speed * delta)) {
            x = targetX - width/2;
        }
        else if (targetX < x + width / 2) {
            x -= speed * delta;
        } else if (targetX > x + width / 2) {
            x += speed * delta;
        }
        bound.setX(x);
        bound.setY(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void dropGather() {
        dropsGathered += 1;
    }

    public int getDropsGathered() {
        return dropsGathered;
    }
}
