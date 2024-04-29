package objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Coin {
    private float x_;
    private float y_;
    private Sprite coin_;
    private Texture coinTexture_;
    private boolean coinCollected_;
    private Sound coinSound_;

    public Coin(float x, float y) {
        x_ = x;
        y_ = y;
        coinTexture_ = new Texture("buttons/coin.jpeg");
        coinCollected_ = false;
        coin_ = new Sprite(coinTexture_, 0, 0, coinTexture_.getWidth(), coinTexture_.getHeight());
        coinSound_ = Gdx.audio.newSound(Gdx.files.internal("sounds/coinFound.mp3"));
    }

    public float getX() {
        return x_;
    }

    public float getY() {
        return y_;
    }

    public boolean checkCollected() {
        return coinCollected_;
    }

    public void setCollected(boolean collected) {
        coinCollected_ = collected;
    }

    public void coinDisplay(SpriteBatch batch, float x, float y) {
        coinCollected_ = false;
        coin_.setPosition(x, y);
        coin_.draw(batch);
    }

    public boolean inRegion(Player player) {
        float x = player.getBody().getPosition().x;
        float y = player.getBody().getPosition().y;

        if(x>= x_ - 1 && x<= x_ + 1 && y>= y_ - 1 && y<= y_ + 1) {
            coin_.setPosition(0,0);
            if(!coinCollected_) {
                coinSound_.play();
                coinCollected_ = true;
            }
            return true;
        }
        return false;
    }
}
