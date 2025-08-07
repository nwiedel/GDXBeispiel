package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;
import com.nicolas.gdxbeispiel.common.CustomActor;

public class BasicsActions extends BeispielBase {

    private static final Logger log = new Logger(BasicsActions.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsActions.class);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;

    private Viewport viewport;

    private Stage stage;
    private Texture texture;
    private CustomActor customActor;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        customActor = new CustomActor(new TextureRegion(texture));
        customActor.setSize(160, 80);
        customActor.setPosition(
            (WORLD_WIDTH - customActor.getWidth()) / 2f,
            (WORLD_HEIGHT - customActor.getHeight()) / 2f
        );

        stage.addActor(customActor);
        Gdx.input.setInputProcessor(this);

        String LS = System.getProperty("line.separator");
        String TAB = "\t";

        log.debug(LS + "Press keys." +  LS +
            TAB + "1 - RotateBy Action" + LS +
            TAB + "2 - FadeOut Action" + LS +
            TAB + "3 - FadeIn Action" + LS +
            TAB + "4 - ScaleTo Action" + LS +
            TAB + "5 - MoveTo Action" + LS +
            TAB + "6 - Sequential Action" + LS +
            TAB + "7 - Parallel Action");
    }

    @Override
    public boolean keyDown(int keycode) {
        customActor.clearActions();

        if (keycode == Input.Keys.NUM_1){
            log.debug("Rotate Action!");
            customActor.addAction(Actions.rotateBy(90f, 1f));
        } else if (keycode == Input.Keys.NUM_2){
            log.debug("FadeOut Action!");
            customActor.addAction(Actions.fadeOut(2f));
        } else if (keycode == Input.Keys.NUM_3) {
            log.debug("FadeIn Action!");
            customActor.addAction(Actions.fadeIn(2f));
        } else if (keycode == Input.Keys.NUM_4) {
            log.debug("ScaleTo Action!");
            customActor.addAction(Actions.scaleTo(1.5f, 1.5f));
        } else if (keycode == Input.Keys.NUM_5) {
            log.debug("MoveTo Action!");
            customActor.addAction(Actions.moveTo(100f, 100f, 2f));
        } else if (keycode == Input.Keys.NUM_6) {
            log.debug("Sequential Action!");
            Action action = Actions.sequence(
                Actions.fadeIn(1f),
                Actions.fadeOut(0.5f)
            );
            customActor.addAction(action);
        } else if (keycode == Input.Keys.NUM_7) {
            log.debug("Parallel Action!");
            Action action = Actions.parallel(
                Actions.rotateBy(90f, 1f),
                Actions.moveTo(50f, 50f, 2)
            );
            customActor.addAction(action);
        }

        return true;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        texture.dispose();
    }
}
