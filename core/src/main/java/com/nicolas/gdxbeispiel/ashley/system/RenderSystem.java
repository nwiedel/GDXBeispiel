package com.nicolas.gdxbeispiel.ashley.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.ashley.component.PositionComponent;
import com.nicolas.gdxbeispiel.ashley.component.SizeComponent;
import com.nicolas.gdxbeispiel.ashley.component.TextureComponent;

public class RenderSystem extends EntitySystem {

    public static final Logger log = new Logger(RenderSystem.class.getSimpleName(), Logger.DEBUG);

    private static final Family FAMILY = Family.all(
        PositionComponent.class,
        SizeComponent.class,
        TextureComponent.class
    ).get();

    private static final ComponentMapper<PositionComponent> POSITION =
        ComponentMapper.getFor(PositionComponent.class);
    private static final ComponentMapper<SizeComponent> SIZE =
        ComponentMapper.getFor(SizeComponent.class);
    private static final ComponentMapper<TextureComponent> TEXTURE =
        ComponentMapper.getFor(TextureComponent.class);

    private Viewport viewport;
    private SpriteBatch batch;

    public RenderSystem(Viewport viewport, SpriteBatch batch) {
        this.viewport = viewport;
        this.batch = batch;
    }

    private ImmutableArray<Entity> entities;

    public RenderSystem(int priority, Viewport viewport, SpriteBatch batch) {
        super(priority);
        this.viewport = viewport;
        this.batch = batch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        log.debug("added to engine!");
        entities = engine.getEntitiesFor(FAMILY);
        log.debug("family entities = " + entities.size());
        log.debug("all entities = " + engine.getEntities().size());
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        draw();

        batch.end();
    }

    private void draw(){
        for (Entity entity : entities){
            PositionComponent position = POSITION.get(entity);
            SizeComponent size = SIZE.get(entity);
            TextureComponent texture = TEXTURE.get(entity);

            batch.draw(texture.texture,
                position.x, position.y,
                size.width, size.height);
        }
    }
}
