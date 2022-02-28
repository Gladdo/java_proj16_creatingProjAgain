package com.badlogic.mygame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.mygame.model.Player;

public class PlayerController {
    public static Player player;
    static String player2SpritesRelativePath = "player2AnimationSheet.png";
    static float playerVelocity = 0.02f;

    public static void initializeController(){
        player = new Player(new Vector2(5,5), 48, 48, player2SpritesRelativePath, playerVelocity);
    }
    public static void update(float deltaTime){
        player.update(deltaTime);
        handleInput();
    }

    public static void draw(Batch spriteBatch) {
        player.draw(spriteBatch);
    }

    public static void handleInput(){
        Vector2 movDir = new Vector2(0f,0f);
        float SPEED;
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            SPEED = 0.08f; // running speed
        }
        else{
            SPEED = 0.05f; // walking speed
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            movDir.add(new Vector2(-playerVelocity, 0f));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            movDir.add(new Vector2(playerVelocity, 0f));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            movDir.add(new Vector2(0f, playerVelocity));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            //camera.position.y -=3f;
            movDir.add(new Vector2(0f, -playerVelocity));
        }
        player.setMovDir(movDir.nor().scl(SPEED));
    }
}
