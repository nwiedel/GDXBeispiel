package com.nicolas.gdxbeispiel.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;

public class BasicsBitmapFont extends BeispielBase {

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsBitmapFont.class);

    public static final float WIDTH = 1080f;
    public static final float HEIGHT = 720f; // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont effectFont;
    private BitmapFont uiFont;
    private GlyphLayout glyphLayout = new GlyphLayout();

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        batch = new SpriteBatch();
        effectFont = new BitmapFont(Gdx.files.internal("fonts/effect_font_32.fnt"));
        uiFont = new BitmapFont(Gdx.files.internal("fonts/ui_font_32.fnt"));
        uiFont.getData().markupEnabled = true;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        draw();

        batch.end();
    }

    private void draw(){
        String text1 = "ICH BENUTZE BITMAP FONT!";
        effectFont.draw(batch, text1, 20, HEIGHT, 150, 0, true);

        String text2 ="[#FF0000]BITMAP [GREEN]FONTS [YELLOW]SIND [BLUE]COOL!";
        glyphLayout.setText(uiFont, text2);
        uiFont.draw(batch,text2,
            (WIDTH - glyphLayout.width) / 2f,
            (HEIGHT - glyphLayout.height) / 2f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        uiFont.dispose();
        effectFont.dispose();
    }
}
