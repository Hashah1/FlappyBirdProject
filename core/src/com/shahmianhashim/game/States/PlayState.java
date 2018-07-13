package com.shahmianhashim.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.shahmianhashim.game.FlappyDappy;
import com.shahmianhashim.game.sprites.Bird;
import com.shahmianhashim.game.sprites.Tube;


public class PlayState extends State {

    private static final int TUBE_SPACING = 125; //for space b/w tubes
    private static final int TUBE_COUNT = 4; //total number of tubes
    private static final int GROUND_Y_OFFSET = -50;

    private Bird bird; //to render the character in the play state
    private Texture background;
    private Array<Tube> tubeArray;
    private Texture ground;
    private Vector2 groundPos1 , groundPos2; //to hold position of ground

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDappy.WIDTH/2, FlappyDappy.HEIGHT/2); //zoom into figure

        tubeArray = new Array<Tube>();
        bird = new Bird(50,300 ); //set position of bird
        background = new Texture("background.png");//set background for playing
        ground = new Texture("ground.png");

        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        for (int i = 1; i <= TUBE_COUNT ; i++)
            tubeArray.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){//if touched
            bird.jump(); //jump bird: add onto the y axis of velocity
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        updateGround();
        bird.update(deltaTime);
        camera.position.x = bird.getBirdPosition().x + 80; //so we can follow bird as it moves

        for (int i=0; i< tubeArray.size; i++){
             Tube tube = tubeArray.get(i);

             if (camera.position.x - (camera.viewportWidth/2) > tube.getPositionTobTube().x + tube.getTopTube().getWidth())
                 tube.reposition(tube.getPositionTobTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

             ///since we already have a loop, we just check each loop whether collision has happened
             if (tube.collisionTheory(bird.getBounds())){ //if it collides
                 gsm.popPush(new PlayState(gsm));  //then restart
             }
        }

        //kill bird if touches ground
        if (bird.getBirdPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.popPush(new PlayState(gsm));
        camera.update();
    }

    @Override
    public void render(SpriteBatch mySpriteBatch) {
        //adjust mySpriteBatch so it knows what to render in relation to camera
        mySpriteBatch.setProjectionMatrix(camera.combined);
        mySpriteBatch.begin();
        mySpriteBatch.draw(background, camera.position.x - (camera.viewportWidth/2/*width of screen*/), 0 ); //draw background wrt bird
        mySpriteBatch.draw(bird.getBirdTexture(), bird.getBirdPosition().x, bird.getBirdPosition().y);

        for (Tube tube : tubeArray) {
            mySpriteBatch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y); //draw bottom tube
            mySpriteBatch.draw(tube.getTopTube(), tube.getPositionTobTube().x, tube.getPositionTobTube().y); //draw top tube
        }

        mySpriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        mySpriteBatch.draw(ground, groundPos2.x , groundPos2.y);
        mySpriteBatch.end();
    }

    private void updateGround(){
        //check if camera moves along with ground
        if (camera.position.x-(camera.viewportWidth/2) >groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth()*2,0);
        }
        if (camera.position.x-(camera.viewportWidth/2) >groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth()*2,0);
        }
    }

    @Override
    public void dispose() {
        background.dispose();
        ground.dispose();
        bird.dispose();
        for (Tube tube : tubeArray){
            tube.dispose();
            System.out.println("Play State Disposed");
        }

    }
}
