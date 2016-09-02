package com.github.gaddsky.extradrop;

import com.badlogic.gdx.Game;
import com.github.gaddsky.extradrop.gamehelpers.AssetLoader;


public class ExtraDrop extends Game {

    private int screenWidth = 800;
    private int screenHeight = 480;

    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        AssetLoader.dispose();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
