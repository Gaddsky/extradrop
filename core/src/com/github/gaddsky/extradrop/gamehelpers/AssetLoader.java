package com.github.gaddsky.extradrop.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class AssetLoader {
    public static Texture dropImage;
    public static Texture bucketImage;
    public static Texture fireballImage;
    public static Texture lifeScoreImage;
    public static Sound dropSound;
    public static Sound crashSound;

    public static void load() {
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        fireballImage = new Texture(Gdx.files.internal("fireball.png"));
        lifeScoreImage = new Texture(Gdx.files.internal("heart.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        crashSound = Gdx.audio.newSound(Gdx.files.internal("crash.wav"));
    }

    public static void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        fireballImage.dispose();
        lifeScoreImage.dispose();
        dropSound.dispose();
        crashSound.dispose();
    }
}
