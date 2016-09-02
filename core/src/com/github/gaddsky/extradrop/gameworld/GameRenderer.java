package com.github.gaddsky.extradrop.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.github.gaddsky.extradrop.gamehelpers.AssetLoader;
import com.github.gaddsky.extradrop.gameobjects.Bucket;
import com.github.gaddsky.extradrop.gameobjects.Drop;


public class GameRenderer {
    private GameWorld world;
    private int gameWidth, gameHeight;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Bucket bucket;
    private Array<Drop> raindrops;
    private Array<Drop> fireballs;


    public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
        this.world = world;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        bucket = world.getBucket();
        raindrops = world.getRaindrops();
        fireballs = world.getFireballs();


        // create the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameWidth, gameHeight);
        this.world.setCamera(camera); // this is dirty hack

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        font = new BitmapFont();

    }

    public void render(float runTime) { // float runTime - for animation
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        batch.begin();

        if (world.isMenu()) {
            drawMenu();
        } else {
            drawGame(runTime);
        }

        batch.end();
    }

    private void drawGame(float runTime) {

        font.draw(batch, "Drops Collected: " + bucket.getDropsGathered(), 0, gameHeight);
        batch.draw(AssetLoader.bucketImage, bucket.getX(), bucket.getY(), bucket.getWidth(), bucket.getHeight());
        for (Drop raindrop : raindrops) {
            batch.draw(AssetLoader.dropImage, raindrop.getX(), raindrop.getY());
        }
        for (Drop fireball : fireballs) {
            batch.draw(AssetLoader.fireballImage, fireball.getX(), fireball.getY());
        }
    }

    private void drawMenu() {
        font.draw(batch, "Welcome to ExtraDrop!!! ", 100, 150);
        font.draw(batch, "Tap anywhere to begin!", 100, 100);
    }
}
