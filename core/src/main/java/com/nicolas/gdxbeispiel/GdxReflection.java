package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;

import java.util.Arrays;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class GdxReflection extends BeispielBase {

    private static final Logger log = new Logger(GdxReflection.class.getSimpleName(), Logger.DEBUG);

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(GdxReflection.class);

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

        debugReflection(GdxReflection.class);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
    }

    @Override
    public void render() {

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
            leftPressed ? "linke Maustaste gedrückt" : "linke Maustaste nicht gedrückt",
            20f,
            720 - 50f);

        font.draw(batch,
            rightPressed ? "rechte Maustaste gedrückt" : "rechte Maustaste nicht gedrückt",
            20f,
            720 - 80f);

        // Tasten
        boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        font.draw(batch,
            wPressed ? "w-Taste gedrückt" : "w-Taste nicht gedrückt",
            20f,
            720 - 110f);

        font.draw(batch,
            sPressed ? "s-Taste gedrückt" : "rechte nicht gedrückt",
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

    private static void debugReflection(Class<?> clazz){
        Field[] fields = ClassReflection.getDeclaredFields(clazz);
        Method[] methods = ClassReflection.getMethods(clazz);

        log.debug("== debug reflection class = " + clazz.getSimpleName());

        log.debug("fields-count = " + fields.length);

        for(Field field : fields){
            log.debug("name = " + field + " type = " + field.getType());
        }

        log.debug("methods-count = " + methods.length);
        for (Method method : methods){
            log.debug("name = " + method.getName() + " parameterType = " + Arrays.asList(method.getParameterTypes()));
        }
        log.debug("=== ENDE ===");
    }
}
