package com.shahmianhashim.game.States;

/**
 * Created by mianhashimshah on 7/17/17.
 */
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//This class has a stack of states which we can render and update according to which state user goes into while playing
public class GameStateManager {
    private Stack<State> StackOfStates;

    //Constructor
    public GameStateManager(){
        StackOfStates = new Stack<State>();
    }

    public void push(State state){
         StackOfStates.push(state); //our stack of states will push this STATE onto the stack
     }

    public void pop(){
        //.dispose() will dispose the elemnet just popped off
        StackOfStates.pop().dispose();// our stack of states will pop this STATE off the stack

    }

    //popPush method for whenever we want to pop and immediately push a state onto the stack
    public void popPush(State state){
        StackOfStates.pop().dispose();
        StackOfStates.push(state);
    }

    public void update(float deltaTime){
        //deltaTime argument passed into update() method from the State class
        StackOfStates.peek().update(deltaTime);    //peek() method is used to look at the top stack
    }

    public void render(SpriteBatch mySpriteBatch){
        StackOfStates.peek().render(mySpriteBatch);
    }
}
