package com.shahmianhashim.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mianhashimshah on 7/26/17.
 */

public class Tube {

    public static  final int TUBE_WIDTH = 52;

    private static  final int FLUCTUATION = 130;
    private static  final int TUBE_GAP = 100;
    private static  final int LOWEST_OPENING = 120;


    private Texture topTube, bottomTube;
    private Vector2 positionTobTube, positionBottomTube; //for position of tubes
    private Rectangle TopBounds, BottomBounds; //to create invisible rectangle around the tubes so we can collide
    private  Random rand; //to create random y axis tube positions

     //constructor
    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random(); //for random positions

        positionTobTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTobTube.y - TUBE_GAP - bottomTube.getHeight());

        //set the x,y and the width,height :: set invisible rectangles when creating the tubes
        TopBounds = new Rectangle(positionTobTube.x , positionTobTube.y, topTube.getWidth(), topTube.getHeight());
        BottomBounds = new Rectangle(positionBottomTube.x , positionBottomTube.y , bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Vector2 getPositionTobTube() {
        return positionTobTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }
    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    //to reposition the tubes
    public void reposition(float x)
    {
        //reposition tubes
        positionTobTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube.set(x, positionTobTube.y - TUBE_GAP - bottomTube.getHeight());

        //reposition invisible rectangles
        TopBounds.setPosition(positionTobTube.x, positionTobTube.y);
        BottomBounds.setPosition(positionBottomTube.x, positionBottomTube.y);
    }

    public boolean collisionTheory(Rectangle player){
        return player.overlaps(TopBounds) || player.overlaps(BottomBounds); //if we hit top or bottom bounds
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }

}
