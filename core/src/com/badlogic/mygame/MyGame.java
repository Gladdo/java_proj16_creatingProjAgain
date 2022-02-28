package com.badlogic.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

// cameraController
public class MyGame extends Game {
	private int widthScreen, heightScreen;
	private OrthographicCamera orthographicCamera;

	float tilesWidth = 14f;
	float tilesHeight = 14f;

	public static MyGame instance;

	public MyGame(){
		instance = this;
	}
	
	@Override
	public void create () {
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();

		this.orthographicCamera = new OrthographicCamera(tilesWidth, tilesHeight * (heightScreen/widthScreen));
		this.orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
		this.orthographicCamera.position.set(this.orthographicCamera.viewportWidth/2f, this.orthographicCamera.viewportWidth/2f, 0);

		setScreen(new GameScreen(orthographicCamera));
	}
}
