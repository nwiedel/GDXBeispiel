package com.nicolas.gdxbeispiel;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.ashley.component.PositionComponent;
import com.nicolas.gdxbeispiel.ashley.component.SizeComponent;
import com.nicolas.gdxbeispiel.ashley.component.TextureComponent;
import com.nicolas.gdxbeispiel.ashley.system.RenderSystem;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;
import com.nicolas.gdxbeispiel.utils.Utilities;

public class BasicsAshleySystem extends BeispielBase {

    private static final Logger log = new Logger(BasicsAshleySystem.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsAshleySystem.class);

    private static final float WORLD_WIDTH = 10.0f;
    private static final float WORLD_HEIGHT = 7.0f;

    private static final String LEVEL_BG = "raw/level-bg.png";
    private static final String CHARACTER = "raw/character.png";

    private AssetManager assetManager;
    private Viewport viewport;
    private SpriteBatch batch;
    private Engine engine;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Logger.DEBUG);
        assetManager = new AssetManager();

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        batch = new SpriteBatch();
        engine = new Engine();

        assetManager.load(LEVEL_BG, Texture.class);
        assetManager.load(CHARACTER, Texture.class);
        assetManager.finishLoading();

        addBackground();
        addCharacter();

        engine.addSystem(new RenderSystem(viewport, batch));
    }

    private void addBackground(){
        PositionComponent position = new PositionComponent();
        position.x = 0;
        position.y = 0;

        SizeComponent size = new SizeComponent();
        size.width = WORLD_WIDTH;
        size.height = WORLD_HEIGHT;

        TextureComponent texture = new TextureComponent();
        texture.texture = assetManager.get(LEVEL_BG);

        Entity entity = new Entity();
        entity.add(position);
        entity.add(size);
        entity.add(texture);

        engine.addEntity(entity);
    }

    private void addCharacter() {
        PositionComponent position = new PositionComponent();
        position.x = 0;
        position.y = 0;

        SizeComponent size = new SizeComponent();
        size.width = 2;
        size.height = 2;

        TextureComponent texture = new TextureComponent();
        texture.texture = assetManager.get(CHARACTER);

        Entity entity = new Entity();
        entity.add(position);
        entity.add(size);
        entity.add(texture);

        engine.addEntity(entity);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Utilities.CORNFLOWER_BLUE);

        float delta = Gdx.graphics.getDeltaTime();
        engine.update(delta);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
    }
}
