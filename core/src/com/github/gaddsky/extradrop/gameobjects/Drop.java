package com.github.gaddsky.extradrop.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Drop {
    private Vector2 position;
    private int width = 64;
    private int height = 64;
    private int speed;
    private Rectangle bound;

    public Drop(int x, int y, int speed) {
        position = new Vector2(x, y);
        this.speed = speed;
        bound = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {
        position.y -= speed * delta;
        bound.setY(position.y);
    }

    public boolean isFalled() {
        return position.y + this.height < 0;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBound() {
        return bound;
    }
}
