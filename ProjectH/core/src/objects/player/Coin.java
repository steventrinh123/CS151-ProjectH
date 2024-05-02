package objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Coin {
    public float x_;
    public float y_;
    public Sprite coin_;
    private boolean coinCollected_;
    private static Sound coinSound_;
    public Vector3 initialCords = new Vector3();

    public Coin(float x, float y) {
        x_ = x;
        y_ = y;
        Texture coinTexture_ = new Texture("score/coin.jpeg");
        coinCollected_ = false;
        //coin_ = new Sprite(coinTexture_, 0, 0, coinTexture_.getWidth(), coinTexture_.getHeight());
        coin_ = new Sprite(coinTexture_, 0, 0, 64, 64);
        initialCords.x = x;
        initialCords.y = y;
        if (coinSound_ == null) {
            coinSound_ = Gdx.audio.newSound(Gdx.files.internal("sounds/coinFound.mp3"));
        }
    }


    public void coinDisplay(SpriteBatch batch) {
        if (!coinCollected_) {
            coin_.draw(batch);
        }
    }

    public int inRegion(float x, float y) {
        int offset = 64;

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