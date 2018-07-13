package com.shahmianhashim.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shahmianhashim.game.States.GameStateManager;
import com.shahmianhashim.game.States.MenuState;

public class FlappyDappy extends ApplicationAdapter {
	public static final int HEIGHT= 800;
    public static final int WIDTH= 480;
    public static final String TITLE = "Flappy Bird";

    private SpriteBatch batch; //only need one SpriteBatch object. For efficiency
	private GameStateManager myGameStateManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1); //moved here from render method, since it only needs to paint screen ones
		myGameStateManager = new GameStateManager();
		myGameStateManager.push(new MenuState(myGameStateManager)); //push the menu state onto the stack
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //wipes screen clean and sprite batch redraws everything fresh
		myGameStateManager.update(Gdx.graphics.getDeltaTime()); //to get difference between render times
		myGameStateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
