package com.shahmianhashim.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * This class will work with the physics corresponding with the bird i.e the velocity, position and texture
 */

public class Bird {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15; //add gravity to bird

    private Vector3 birdPosition, birdVelocity; //Vector3 holds x,y,z axes. We can use vector2 also
    private Texture birdie;
    private Rectangle boundsBird; //to create rectangle around bird too

    private Texture texture;
    private Animation birdAnimation;

    public Bird(int xAxisPosition, int yAxisPosition) //constructor takes in the start positions as the x and y axes
    {

        birdPosition = new Vector3(xAxisPosition, yAxisPosition , 0); //0 for the z axis
        birdVelocity = new Vector3(0,0,0); //cuz bird has 0 velocity

        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture),3,0.5f); //3 frames, 0.5f cycle time
        boundsBird = new Rectangle(xAxisPosition, yAxisPosition, texture.getWidth()/3, texture.getHeight());

    }

    //update methodd created so that it can send the delta time to the bird class to reset position and make it live.
    public void update (float deltaTime)
    {
        birdAnimation.update(deltaTime); //update animation

        if (birdPosition.y>0) //only if its within screen
            birdVelocity.add(0,GRAVITY,0); //everytime bird is updated, add gravity to its velocity on the y axis

        birdVelocity.scl(deltaTime); //since its in relation to time , we have to scale by delta time
        birdPosition.add(MOVEMENT*deltaTime,birdVelocity.y, 0); //update position and move bird in relation to deltatime

        if (birdPosition.y < 0 )
            birdPosition.y = 0; //so bird doesnt go below screen

        birdVelocity.scl(1/deltaTime); //reverses the scaling
        boundsBird.setPosition(birdPosition.x, birdPosition.y);//whenever our bird moves also update its bounds
    }

    //mehtod dealing with returnung the rectangle
    public Rectangle getBounds() {
    return boundsBird;
    }


    public Vector3 getBirdPosition() {
        return birdPosition;
    }

    public TextureRegion getBirdTexture() {
        return birdAnimation.getFrame();
    }
    public void jump(){
        birdVelocity.y = 250;
    }

    public void dispose(){
        texture.dispose();
    }
}
