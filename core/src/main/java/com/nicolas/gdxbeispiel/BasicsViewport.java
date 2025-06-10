package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;
import com.nicolas.gdxbeispiel.utils.Utilities;


public class BasicsViewport extends BeispielBase {

    private static final Logger log = new Logger(BasicsViewport.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsViewport.class);

    private static final float WORLD_WIDTH = 800.0f;
    private static final float WORLD_HEIGHT = 600.0f;

    private OrthographicCamera camera;
    private Viewport currentViewport;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    private ArrayMap<String, Viewport> viewports = new ArrayMap<>();

    private int currentViewportIndex;
    private String currentViewportName;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg-small.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

        createViewports();
        selectNextViewport();

        Gdx.input.setInputProcessor(this);
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
        batch.draw(texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        font.draw(batch, currentViewportName, 50, 100);
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        selectNextViewport();
        return true;
    }

    private void createViewports(){
        viewports.put(StretchViewport.class.getSimpleName(),
            new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(FitViewport.class.getSimpleName(),
            new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(FillViewport.class.getSimpleName(),
            new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(ScreenViewport.class.getSimpleName(),
            new ScreenViewport(camera));
        viewports.put(ExtendViewport.class.getSimpleName(),
            new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        currentViewportIndex = -1;
    }

    private void selectNextViewport(){
        currentViewportIndex = (currentViewportIndex + 1) % viewports.size;
        currentViewport = viewports.getValueAt(currentViewportIndex);
        currentViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentViewportName = viewports.getKeyAt(currentViewportIndex);

        log.debug("ausgewählter Viewport = " + currentViewportName);
    }
}
