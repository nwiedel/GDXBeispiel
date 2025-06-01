package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.SimpleOrthoGroupStrategy;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;
import com.nicolas.gdxbeispiel.utils.Utilities;


public class BasicsOrthographicCamera extends BeispielBase {

    private static final Logger log = new Logger(BasicsOrthographicCamera.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsOrthographicCamera.class);

    public static final float WORLD_WIDTH = 10.8f; // world units
    public static final float WORLD_HEIGHT = 7.8f; // world units

    private static final float CAMERA_SPEED = 2.0f; // world units
    private static final float CAMERA_ZOOM_SPEED = 2.0f; // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Logger.DEBUG);

        camera = new OrthographicCamera();

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg.png"));
    }

    @Override
    public void render() {
        queryInput();

        ScreenUtils.clear(Utilities.CORNFLOWER_BLUE);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    private void queryInput(){
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            camera.position.x -= CAMERA_SPEED * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.position.x += CAMERA_SPEED * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            camera.position.y += CAMERA_SPEED * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            camera.position.y -= CAMERA_SPEED * deltaTime;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)){
            camera.zoom -= CAMERA_ZOOM_SPEED * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)){
            camera.zoom += CAMERA_ZOOM_SPEED * deltaTime;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            log.debug("position: " + camera.position);
            log.debug("zoom: " + camera.zoom);
        }

        camera.update();
    }

    private void draw(){
        batch.draw(texture,
            0, 0,
            WORLD_WIDTH, WORLD_HEIGHT
        );
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
