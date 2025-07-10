package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;
import com.nicolas.gdxbeispiel.utils.Utilities;

import java.awt.*;

public class BasicsAssetManager extends BeispielBase {

    private static final Logger log = new Logger(BasicsAssetManager.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsAssetManager.class);

    private static final String BACKGROUND_BLUE = "raw/background-blue.png";
    private static final String GREEN_CIRCLE = "raw/circle-green.png";
    private static final String RED_CIRCLE = "raw/circle-red.png";
    private static final String FONT = "fonts/oswald-32.fnt";

    private AssetManager assetManager;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Texture backgroundBlue;
    private Texture greenCircle;
    private Texture redCircle;
    private BitmapFont font;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        batch = new SpriteBatch();

        // load assets
        assetManager.load(BACKGROUND_BLUE, Texture.class);
        assetManager.load(GREEN_CIRCLE, Texture.class);
        assetManager.load(RED_CIRCLE, Texture.class);
        assetManager.load(FONT, BitmapFont.class);

        // block until all is loaded
        assetManager.finishLoading();

        // get asset
        backgroundBlue = assetManager.get(BACKGROUND_BLUE);
        greenCircle = assetManager.get(GREEN_CIRCLE);
        redCircle = assetManager.get(RED_CIRCLE);
        font = assetManager.get(FONT);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Utilities.CORNFLOWER_BLUE);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(backgroundBlue, 0, 0);
        batch.draw(greenCircle, 50, 50);
        batch.draw(redCircle, 200, 200);
        font.draw(batch, "AssetManager Beispiel!", 500, 50);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
    }
}
