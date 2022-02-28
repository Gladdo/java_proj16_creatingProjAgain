package com.badlogic.mygame.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.mygame.GameScreen;
import com.badlogic.mygame.helper.TileMapHelper;

import com.badlogic.gdx.utils.Array;

public class LevelController {
    // display in 2d plane
    private static OrthogonalTiledMapRenderer renderer;
    // load map
    private static TileMapHelper tileMapHelper;
    // draw sprites
    public static Batch spriteBatch;
    public static World world;
    //private static Array<Body> bodiesInWorld;
    private static Box2DDebugRenderer box2DDebugRenderer;

    public static final float UNIT_SCALE = 1/32f;

    public static void initializeController(GameScreen gameScreen){
        // call the levels
        tileMapHelper = new TileMapHelper(gameScreen);

        // rendering the first tileset - first scene:
        renderer = tileMapHelper.setupMap();
        //renderer = tileMapHelper.setupMap2(); // testing second part of a small map, to work with change scenes
        // this game doesn't need gravity
        world = new World(new Vector2(0,0), true);

        box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.setDrawBodies(true);

        spriteBatch = renderer.getBatch();
    }

    public static void draw(OrthographicCamera camera){
        renderer.render(tileMapHelper.groundLayerIndices);
        renderer.render(tileMapHelper.belowCharLayerIndices);
        spriteBatch.begin();
        // here comes the player
        //PlayerController.player.draw(spriteBatch);
        PlayerController.draw(spriteBatch);
        spriteBatch.end();
        renderer.render(tileMapHelper.decorationLayersIndices);

        box2DDebugRenderer.render(world, camera.combined);
    }

    public static void update(float deltaTime, OrthographicCamera camera){
        world.step(1/60f, 6, 2);
        spriteBatch.setProjectionMatrix(camera.combined);
        renderer.setView(camera);

        //PlayerController.update(deltaTime);

    }
    public World getWorld(){
        return this.world;
    }


}
