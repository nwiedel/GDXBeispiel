package com.nicolas.gdxbeispiel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nicolas.gdxbeispiel.common.BeispielBase;
import com.nicolas.gdxbeispiel.common.BeispielInfo;

public class BasicsShapeRenderer extends BeispielBase {

    public static final BeispielInfo BEISPIEL_INFO = new BeispielInfo(BasicsShapeRenderer.class);

    private static final float WORLD_WIDTH = 40f;
    private static final float WORLD_HEIGHT = 20f;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        renderer.setProjectionMatrix(camera.combined);

        if (drawGrid){
            drawGrid();
        }

        if (drawCircles){
            drawCircles();
        }

        if(drawRectangles){
            drawRectangles();
        }

        if (drawPoints){
            drawPoints();
        }
    }

    @Override
    public void resize(int width, int height) {
        // die Kamera wird in diesem Fall nicht zentriert.
        viewport.update(width, height);
    }

    private void drawGrid(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        for (int x = -worldWidth ; x < worldWidth; x++){
            renderer.line(x, -worldHeight, x,  worldHeight);

        }
        for(int y = -worldHeight; y < worldHeight; y++){
            renderer.line(-worldWidth, y, worldWidth, y);
        }
        renderer.setColor(Color.RED);
        renderer.line(-worldWidth, 0.0f, worldWidth, 0.0f );
        renderer.line(0.0f, -worldHeight, 0.0f, worldHeight);

        renderer.end();
    }

    private void drawCircles(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GREEN);
        renderer.circle(2, 2, 2, 30);
        renderer.circle(-5, -5, 1, 30);
        renderer.end();
    }

    private void drawRectangles(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GREEN);
        renderer.rect(-8, 4, 4, 2);
        renderer.rect(-11, 3, 1, 5);
        renderer.end();
    }

    private void drawPoints(){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.MAGENTA);
        renderer.point(5, 0, 0);
        renderer.point(5, -3, 0);
        renderer.point(8, 6, 1);
        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.x(-10, 0, 0.25f);
        renderer.end();
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.G){
            drawGrid = !drawGrid;
        } else if (keyCode == Input.Keys.C) {
            drawCircles = !drawCircles;
        } else if (keyCode == Input.Keys.R) {
            drawRectangles = !drawRectangles;
        } else if (keyCode == Input.Keys.P) {
            drawPoints = !drawPoints;
        }

        return true;
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
