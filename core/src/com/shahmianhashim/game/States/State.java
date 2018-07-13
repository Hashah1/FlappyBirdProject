package com.shahmianhashim.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by mianhashimshah on 7/17/17.
 */

//State is abstract because we dont want to instantiate it at any time
public abstract class State {

    protected OrthographicCamera camera; //manage our camera i.e what part of the game is shown on screen
    protected Vector3 mouse; //xyz axis
    protected GameStateManager gsm; //class needed so we can manage our states throughout the game. (Game state, menu state etc)

    //constructor-- Takes in GameStateManagaer as a parameter
    protected State(GameStateManager gsm){

        //instantiate all objects
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();

    }

    //BASIC METHODS FOR STATE CLASS//
    protected abstract void handleInput();
    public abstract void update(float deltaTime); //deltaTime:  diff between two rendered frames
    public abstract void render(SpriteBatch mySpriteBatch); //SpriteBatch is whatever needs to be rendered to screen
    public abstract void dispose();

}
