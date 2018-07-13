package com.shahmianhashim.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Animation {

    private Array<TextureRegion> frames; //store frames
    private float maxFrameTime; //max time frame stays until we switch
    private float currentFrameTime; //time animation has been in current frame
    private int frameCount; //# of frames in our animation
    private int frame; //current frame we are actually in

    public Animation (TextureRegion region, int frameCount , float cycleTime)
    {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth()/frameCount; //width of a singke frame

        for (int i =0 ; i <frameCount ; i++){
            frames.add(new TextureRegion(region,  i*frameWidth , 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){

        currentFrameTime += dt; //how long current frame in view

        if (currentFrameTime >maxFrameTime ){
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount)
            frame=0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
