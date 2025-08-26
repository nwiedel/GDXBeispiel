package com.nicolas.gdxbeispiel;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;
import com.nicolas.gdxbeispiel.utils.Utilities;

public class BasicsAshleyEngine extends BeispielBase {

    private static final Logger log = new Logger(BasicsAshleyEngine.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsAshleyEngine.class);

    private static final float SPAWN_TIME = 1f;
    private static final float REMOVE_TIME = 3f;

    private Engine engine;

    private Array<Entity> bullets = new Array<>();

    private float spawnTimer;
    private float removeTimer;
    private Entity entity;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        engine = new Engine();
        engine.addEntityListener(new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                log.debug("Entity hinzugefuegt = " + entity);
                log.debug("Anzahl der Entities = " + engine.getEntities().size());
            }

            @Override
            public void entityRemoved(Entity entity) {
                log.debug("Entity entfernt = " + entity);
                log.debug("Anzahl der Entities = " + engine.getEntities().size());
            }
        });

        addBullet();
    }

    private void addBullet(){
        Entity bullet = new Entity();
        bullets.add(bullet);
        engine.addEntity(bullet);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Utilities.CORNFLOWER_BLUE);

        float delta = Gdx.graphics.getDeltaTime();
        engine.update(delta);

        spawnTimer += delta;

        if(spawnTimer > SPAWN_TIME){
            spawnTimer = 0;
            addBullet();
        }

        removeTimer += delta;

        if(removeTimer > REMOVE_TIME){
            removeTimer = 0;
            if (bullets.size > 0){
                Entity bullet = bullets.first();
                bullets.removeValue(bullet, true);
                engine.removeEntity(bullet);
            }
        }
    }
}
