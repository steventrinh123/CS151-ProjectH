package objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Coin {
    private float x;
    private float y;
    private Sprite coin;
    private Texture coinTexture;
    private boolean coinCollected;
    private static Sound coinSound;

    public Coin(float x, float y) {
        x = x;
        y = y;
        coinTexture = new Texture("buttons/coin.jpeg");
        coinCollected = false;
        coin = new Sprite(coinTexture, 0, 0, coinTexture.getWidth(), coinTexture.getHeight());
        if (coinSound == null) {
            coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coinFound.mp3"));
        }
        coin.setPosition(x, y);
    }


    public boolean checkCollected() {
        return coinCollected;
    }

    public void setCollected(boolean collected) {
        coinCollected = collected;
    }

    public void coinDisplay(SpriteBatch batch) {
        if (!coinCollected) {
            coin.draw(batch);
        }
    }

    public int inRegion(Player player) {
        int offset = 2;
        float x = player.getBody().getPosition().x;
        float y = player.getBody().getPosition().y;

        if (x >= x - offset && x <= x + offset && y >= y - offset && y <= y + offset) {
            if (!coinCollected) {
                coinSound.play();
                coinCollected = true;
                return 1;
            }
            return 0;
        }
        return 0;
    }
}