package com.github.gaddsky.extradrop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.github.gaddsky.extradrop.gamehelpers.InputHandler;
import com.github.gaddsky.extradrop.gameworld.GameRenderer;
import com.github.gaddsky.extradrop.gameworld.GameWorld;


public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen(final ExtraDrop game) {
        float gameWidth = game.getScreenWidth();
        float gameHeight = game.getScreenHeight();

        world = new GameWorld(gameWidth, gameHeight);
        renderer = new GameRenderer(world, (int) gameWidth, (int) gameHeight);
        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
