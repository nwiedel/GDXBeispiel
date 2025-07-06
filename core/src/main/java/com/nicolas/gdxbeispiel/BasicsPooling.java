package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;

public class BasicsPooling extends BeispielBase {

    private static final Logger log = new Logger(BasicsPooling.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsPooling.class);

    private static final float BULLET_ALIVE_TIME = 3f;
    private static final float BULLET_SPAWN_TIME =1f;

    private Array<Bullet> bullets = new Array<Bullet>();

    private float timer;

    //public final Pool<Bullet> bulletPool = Pools.get(Bullet.class, 15);
    private final Pool<Bullet> bulletPool = new Pool<Bullet>() {
        @Override
        protected Bullet newObject() {
            log.debug("neues Objekt");
            return new Bullet();
        }

        @Override
        public void free(Bullet object) {
            log.debug("vor dem Freigeben, Objekt: " + object + " frei: " + getFree());
            super.free(object);
            log.debug("nach dem Freigeben, Objekt: " + object + " frei: " + getFree());
        }

        @Override
        public Bullet obtain() {
            log.debug("vor obtain: " + getFree());
            Bullet ret = super.obtain();
            log.debug("nach obtain: " + getFree());
            return ret;
        }

        @Override
        protected void reset(Bullet object) {
            log.debug("resetting object: " + object);
            super.reset(object);
        }
    };

    @Override
    public void create() {
        Gdx.app.setLogLevel(Logger.DEBUG);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        timer += delta;

        if(timer > BULLET_SPAWN_TIME){
            timer = 0;
            Bullet bullet = bulletPool.obtain();
            bullets.add(bullet);

            log.debug("erzeugte lebende Kugeln = " + bullets.size);
        }

        for (int i = 0; i < bullets.size; i++){
            Bullet bullet = bullets.get(i);
            bullet.update(delta);

            if (!bullet.alive){
                bullets.removeIndex(i);
                bulletPool.free(bullet);
                log.debug("nach Freigabe, lebende Kugeln =" + bullets.size);
            }
        }
    }

    @Override
    public void dispose() {
        bulletPool.freeAll(bullets);
        bulletPool.clear();
        bullets.clear();
    }

    // -- Bullet Class --
    public static class Bullet implements Pool.Poolable {
        boolean alive = true;
        float timer;

        public Bullet(){

        }

        public void update(float delta){
            timer += delta;

            if (alive && timer > BULLET_ALIVE_TIME){
                alive = false;
            }
        }

        @Override
        public void reset() {
            alive = true;
            timer = 0;
        }
    }
}
