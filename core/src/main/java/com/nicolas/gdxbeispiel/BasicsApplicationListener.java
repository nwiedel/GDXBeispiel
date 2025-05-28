package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class BasicsApplicationListener implements ApplicationListener {

    private static final Logger log = new Logger(BasicsApplicationListener.class.getSimpleName(), Logger.DEBUG);

    private boolean renderInterrupted = true;

    @Override
    public void create() {
        // wird genutzt, um Daten zu initialisieren und
        // Ressourcen zu laden
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        log.debug("create()");
    }

    @Override
    public void resize(int width, int height) {
        //wird genutzt, um die Screensize zu setzen
        log.debug("resize() width = " + width + " height = " + height);
    }

    @Override
    public void render() {
        // wird genutzt, um die Spiellogig upzudaten und neu zu
        // zeichnen, 60 mal pro Sekunde
        if (renderInterrupted){
            log.debug("render()");
            renderInterrupted = false;
        }
    }

    @Override
    public void pause() {
        // wird genutzt, um den Gamestate zu speichern, wenn
        // das Spiel den Fokus verliert.
        log.debug("pause()");
        renderInterrupted = true;
    }

    @Override
    public void resume() {
        // wird genutzt, nach Pausieren um den Gamestat neu
        // zu starten
        log.debug("resume()");
        renderInterrupted = true;
    }

    @Override
    public void dispose() {
        // wird genutzt, um Ressourcen frei zu geben
        log.debug("dispose();");
    }
}
