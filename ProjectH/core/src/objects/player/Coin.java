package objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Coin {
    private float x_;
    private float y_;
    private Sprite coin_;
    private Texture coinTexture_;
    private boolean coinCollected_;
    private static Sound coinSound_;

    public Coin(float x, float y) {
        x_ = x;
        y_ = y;
        coinTexture_ = new Texture("buttons/coin.jpeg");
        coinCollected_ = false;
        coin_ = new Sprite(coinTexture_, 0, 0, coinTexture_.getWidth(), coinTexture_.getHeight());
        if (coinSound_ == null) {
            coinSound_ = Gdx.audio.newSound(Gdx.files.internal("sounds/coinFound.mp3"));
        }
        coin_.setPosition(x_, y_);
    }

    public void setCollected(boolean collected) {
        coinCollected_ = collected;
    }

    public void coinDisplay(SpriteBatch batch) {
        if (!coinCollected_) {
            coin_.draw(batch);
        }
    }

    public int inRegion(Player player) {
        int offset = 2;
        float x = player.getBody().getPosition().x;
        float y = player.getBody().getPosition().y;

        if (x >= x_ - offset && x <= x_ + offset && y >= y_ - offset && y <= y_ + offset) {
            if (!coinCollected_) {
                coinSound_.play();
                coinCollected_ = true;
                return 1;
            }
            return 0;
        }
        return 0;
    }
}