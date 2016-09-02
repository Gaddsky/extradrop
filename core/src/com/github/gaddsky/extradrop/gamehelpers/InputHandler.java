package com.github.gaddsky.extradrop.gamehelpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.github.gaddsky.extradrop.gameobjects.Bucket;
import com.github.gaddsky.extradrop.gameworld.GameWorld;


public class InputHandler implements InputProcessor {
    private Bucket bucket;
    private GameWorld world;

    public InputHandler(GameWorld world) {
        this.world = world;
        this.bucket = world.getBucket();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (world.isMenu()) {
            world.start();
            return true;
        } else if (world.isRunning()) {
            return setTarget(screenX);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (!world.isMenu()) {
            return setTarget(screenX);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private boolean setTarget(int screenX) {
        if ((screenX < bucket.getX()) || (screenX > bucket.getX() + bucket.getWidth())) { // prevent shaking of bucket
            screenX = (int) world.getCamera().unproject(new Vector3(screenX, 0, 0)).x; // this is dirty hack
            bucket.setTargetX(screenX);
            return true;
        }
        else return false;
    }

}
