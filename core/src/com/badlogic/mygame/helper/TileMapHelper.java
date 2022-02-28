package com.badlogic.mygame.helper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.mygame.GameScreen;
import com.badlogic.mygame.controller.LevelController;

// our model of Level
public class TileMapHelper extends ApplicationAdapter {
    String firstSceneRelativePath = "myTileMap.tmx";
    String secondSceneRelativePath = "myTileMap_part2.tmx";
    private GameScreen gameScreen;
    public TiledMap tiledMap;

    public int[] groundLayerIndices; // for ex. ground and water - Bottom Layer
    public int[] belowCharLayerIndices; // for ex. flowers - Top layer
    public int[] decorationLayersIndices; // for ex. houses, bushes, trees - EvenMoreTopLayer

    public TileMapHelper(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    public OrthogonalTiledMapRenderer setupMap(){
        tiledMap = new TmxMapLoader().load(firstSceneRelativePath);

        // Reading Map Layers
        MapLayers mapLayers = tiledMap.getLayers();

        groundLayerIndices = new int[]{
                mapLayers.getIndex("BottomLayer")
        };
        belowCharLayerIndices = new int[]{
                mapLayers.getIndex("TopLayer")
        };
        decorationLayersIndices = new int[]{
                mapLayers.getIndex("EvenMoreTopLayer")
        };

        return new OrthogonalTiledMapRenderer(tiledMap, LevelController.UNIT_SCALE);

    }
    public OrthogonalTiledMapRenderer setupMap2(){
        tiledMap = new TmxMapLoader().load(secondSceneRelativePath);

        // Reading Map Layers
        MapLayers mapLayers = tiledMap.getLayers();

        groundLayerIndices = new int[]{
                mapLayers.getIndex("BottomLayer")
        };
        belowCharLayerIndices = new int[]{
                mapLayers.getIndex("TopLayer")
        };
        decorationLayersIndices = new int[]{
                mapLayers.getIndex("EvenMoreTopLayer")
        };

        return new OrthogonalTiledMapRenderer(tiledMap, LevelController.UNIT_SCALE);

    }


}
