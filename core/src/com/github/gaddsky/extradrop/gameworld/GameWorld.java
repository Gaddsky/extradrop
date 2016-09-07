package com.github.gaddsky.extradrop.gameworld;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.gaddsky.extradrop.gamehelpers.AssetLoader;
import com.github.gaddsky.extradrop.gameobjects.Bucket;
import com.github.gaddsky.extradrop.gameobjects.Drop;
import com.github.gaddsky.extradrop.gameobjects.LifeScore;

import java.util.Iterator;


public class GameWorld {
    private Array<Drop> raindrops;
    private Array<Drop> fireballs;
    private Bucket bucket;
    private LifeScore lifescore;
    private long lastDropTime;
    private float gameWidth;
    private float gameHeight;
    private GameState currentState;
    private Iterator<Drop> iter;
    private OrthographicCamera camera;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        currentState = GameState.MENU;
        bucket = new Bucket((int) gameWidth / 2, 0, 400);
        raindrops = new Array<Drop>();
        fireballs = new Array<Drop>();
        lifescore = new LifeScore(5);
        spawnRaindrop();
    }

    public void update(float delta) {
        switch (currentState) {
            case MENU:
                updateMenu();
                break;
            case RUNNING:
                updateRunning(delta);
                break;
        }
    }


    public void updateRunning(float delta) {
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            if (Gdx.input.getAccelerometerY() < -1) bucket.setTargetX(0);
            else if (Gdx.input.getAccelerometerY() > 1) bucket.setTargetX((int) gameWidth);
            else bucket.setTargetX(bucket.getX());
        }
        bucket.update(delta, gameWidth);
        iter = raindrops.iterator();
        while (iter.hasNext()) {
            Drop drop = iter.next();
            drop.update(delta);
            if (bucket.getBound().overlaps(drop.getBound())) {
                iter.remove();
                AssetLoader.dropSound.play();
                bucket.dropGather();
            }
            if (drop.isFalled()) {
                iter.remove();
            }
        }

        iter = fireballs.iterator();
        while (iter.hasNext()) {
            Drop drop = iter.next();
            drop.update(delta);
            if (bucket.getBound().overlaps(drop.getBound())) {
                iter.remove();
                AssetLoader.crashSound.play();
                lifescore.lostOfLife();
                clearScreen();
                if (!lifescore.isAlive()) gameOver();
            }
            if (drop.isFalled()) {
                iter.remove();
            }
        }

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            if (MathUtils.random(0, 10) < 9) {
                spawnRaindrop();
            } else spawnFireball();

        }
    }

    private void updateMenu() {
    }

    private void spawnRaindrop() {
        Drop raindrop = new Drop(MathUtils.random(0, (int) gameWidth - 64), (int) gameHeight, 200);
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void spawnFireball() {
        Drop fireball = new Drop(MathUtils.random(0, (int) gameWidth - 64), (int) gameHeight, 300);
        fireballs.add(fireball);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void clearScreen() {
        raindrops.clear();
        fireballs.clear();
    }

    private void gameOver() { // dummy game over
        System.out.println("Game over!");
        System.exit(0);
    }

    public Array<Drop> getRaindrops() {
        return raindrops;
    }

    public Array<Drop> getFireballs() {
        return fireballs;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public LifeScore getLifeScore() {
        return lifescore;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void setCamera(OrthographicCamera camera) { // this is dirty hack
        this.camera = camera;
    }

    public OrthographicCamera getCamera() { // this is dirty hack
        return camera;
    }
}
