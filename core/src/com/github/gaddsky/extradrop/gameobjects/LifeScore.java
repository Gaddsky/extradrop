package com.github.gaddsky.extradrop.gameobjects;

import com.github.gaddsky.extradrop.gamehelpers.NoMoreLifesException;


public class LifeScore {
    private int restOfLifes;
    private int width = 8;
    private int height = 8;

    public LifeScore(int initialRestOfLifes) {
        restOfLifes = initialRestOfLifes;
    }

    public int getRestOfLifes() {
        return restOfLifes;
    }

    public void lostOfLife() throws NoMoreLifesException {
        restOfLifes -= 1;
        if (restOfLifes < 0) throw new NoMoreLifesException();
    }

    public boolean isAlive() {
        return restOfLifes > 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
