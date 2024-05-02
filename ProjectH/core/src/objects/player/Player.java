package objects.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static helper.Constants.PPM;


public class Player extends GameEntity {
    private int jumpCounter;

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 12f;
        this.jumpCounter = 0;
    }

    @Override
    public void update() {
        x = body.getPosition().x = PPM;
        y = body.getPosition().y = PPM;

        checkUserInput();
    }

    @Override
    public void render(SpriteBatch batch) {


    }

    private void checkUserInput() {
        velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velX = 1;

        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velX = -1;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumpCounter < 2){
            float force = body.getMass() * 100;
            body.setLinearVelocity(body.getLinearVelocity().x,0);
            body.applyLinearImpulse(new Vector2(0,force),body.getPosition(), true);
            jumpCounter++;
            Sound jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jumpSound.mp3"));
            jumpSound.play();
        }


        //resetJumpCounter
        if(body.getLinearVelocity().y == 0){
            jumpCounter = 0;
        }
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 25 ?
                body.getLinearVelocity().y : 25);
    }
}
