package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;
import com.nicolas.gdxbeispiel.utils.Utilities;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class BasicsInputPolling extends BeispielBase {

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsInputPolling.class);

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Utilities.CORNFLOWER_BLUE);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        draw();

        batch.end();
    }

    private void draw(){
        // Maus / Touch x/y
        int mouseX = Gdx.input.getX();
        int mouseY =Gdx.input.getY();

        // Buttons
        boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);

        font.draw(batch,
            "Maus/Touch : x = " + mouseX + " y = " + mouseY,
            20f,
            720 - 20f);

        font.draw(batch,
            leftPressed ? "linke Maustaste gedrueckt" : "linke Maustaste nicht gedrueckt",
            20f,
            720 - 50f);

        font.draw(batch,
            rightPressed ? "rechte Maustaste gedrueckt" : "rechte Maustaste nicht gedrueckt",
            20f,
            720 - 80f);

        // Tasten
        boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        font.draw(batch,
            wPressed ? "w-Taste gedrueckt" : "w-Taste nicht gedrueckt",
            20f,
            720 - 110f);

        font.draw(batch,
            sPressed ? "s-Taste gedrueckt" : "s-Taste nicht gedrueckt",
            20f,
            720 - 140f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
