package Screens;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ProjectH;

public class RhythmGame extends ScreenAdapter {
    private final Texture noteTexture1;
    private final Texture characterImage1;
    private final Texture scoreIs300;
    private final Texture scoreIs100;
    private final Texture scoreIsMiss;
    private final Sound soundEffect;
    private Music music1;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Rectangle character;
    private final Array<Rectangle> notes;
    private long lastDropTime;
    private int counter = 1;

    private final RhythmGameHud hud;

    private final HashMap<Integer, Float> listOfNotes = new HashMap<>();

    public RhythmGame() {
        // load the images for the note and the character, 64x64 pixels each
        noteTexture1 = new Texture(Gdx.files.internal("buttons/note2.png"));
        characterImage1 = new Texture(Gdx.files.internal("playerImage/frij.png"));

        scoreIs300 = new Texture(Gdx.files.internal("score/300.png"));
        scoreIs100 = new Texture(Gdx.files.internal("score/100.png"));
        scoreIsMiss = new Texture(Gdx.files.internal("score/miss.png"));


        // load the note sound effect and the music
        soundEffect = Gdx.audio.newSound(Gdx.files.internal("sounds/drop.wav"));
        music1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/audio1.mp3"));


        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        //camera.setToOrtho(false, 1500, 642);
        batch = new SpriteBatch();
        hud = new RhythmGameHud(batch);

        // create a Rectangle to logically represent the character
        character = new Rectangle();
        character.x = 800 / 2 - 64 / 2; // center the character horizontally
        character.y = 20; // bottom left corner of the character is 20 pixels above the bottom screen edge
        character.width = 64;
        character.height = 64;

        // create the notes array
        notes = new Array<Rectangle>();
    }

    private void spawnNote(int a) throws NullPointerException{
        Rectangle individualNotes;
        createPattern();

        try {
            individualNotes = new Rectangle();
            individualNotes.x = listOfNotes.get(a);

            individualNotes.y = 480;
            individualNotes.width = 64;
            individualNotes.height = 64;
            notes.add(individualNotes);
            lastDropTime = TimeUtils.nanoTime();
        }
        catch(NullPointerException e)
        {
            if (hud.getPoints()>10000){
                ProjectH.INSTANCE.rhythmWinCheck = true;
                if(ProjectH.INSTANCE.platformWinCheck) {
                    this.dispose();
                    ProjectH.INSTANCE.setScreen(new WinScreen(ProjectH.INSTANCE));
                }
                else{
                    this.dispose();
                    ProjectH.INSTANCE.setScreen(new MenuScreen(ProjectH.INSTANCE));
                }

            }
            else{
                this.dispose();
                ProjectH.INSTANCE.setScreen(new RhythmGame());
            }
        }

    }

    public void createPattern(){

        // note is shown on screen (& meant to be hit) if the float value (second parameter) is within 800F
        // any note > 800F is to be treated as a break (or gap) and not for the user to hit
        listOfNotes.put(1, 400F);
        listOfNotes.put(2, 350F);
        listOfNotes.put(3, 400F);
        listOfNotes.put(4, 350F);
        listOfNotes.put(5, 5000F);
        listOfNotes.put(6, 600F);
        listOfNotes.put(7, 400F);
        listOfNotes.put(8, 550F);

        listOfNotes.put(9, 400F);
        listOfNotes.put(10, 450F);
        listOfNotes.put(11, 380F);
        listOfNotes.put(12, 500F);
        listOfNotes.put(13, 4000F);
        listOfNotes.put(14, 290F);
        listOfNotes.put(15, 350F);
        listOfNotes.put(16, 420F);

        listOfNotes.put(17, 500F);
        listOfNotes.put(18, 400F);
        listOfNotes.put(19, 300F);
        listOfNotes.put(20, 200F);
        listOfNotes.put(21, 350F);
        listOfNotes.put(22, 5000F);
        listOfNotes.put(23, 300F);
        listOfNotes.put(24, 450F);

        listOfNotes.put(25, 600F);
        listOfNotes.put(26, 450F);
        listOfNotes.put(27, 650F);
        listOfNotes.put(28, 550F);
        listOfNotes.put(29, 3500F);
        listOfNotes.put(30, 500F);
        listOfNotes.put(31, 400F);
        listOfNotes.put(32, 350F);

        listOfNotes.put(33, 400F);
        listOfNotes.put(34, 6000F);
        listOfNotes.put(35, 430F);
        listOfNotes.put(36, 550F);
        listOfNotes.put(37, 350F);
        listOfNotes.put(38, 5000F);
        listOfNotes.put(39, 400F);
        listOfNotes.put(40, 6500F);

        listOfNotes.put(41, 550F);
        listOfNotes.put(42, 3800F);
        listOfNotes.put(43, 450F);
        listOfNotes.put(44, 550F);
        listOfNotes.put(45, 390F);
        listOfNotes.put(46, 3000F);
        listOfNotes.put(47, 400F);

        listOfNotes.put(48, 500F);
        listOfNotes.put(49, 300F);
        listOfNotes.put(50, 370F);
        listOfNotes.put(51, 200F);
        listOfNotes.put(52, 350F);
        listOfNotes.put(53, 2200F);
        listOfNotes.put(54, 400F);
        listOfNotes.put(55, 550F);

        listOfNotes.put(56, 500F);
        listOfNotes.put(57, 600F);
        listOfNotes.put(58, 450F);
        listOfNotes.put(59, 600F);
        listOfNotes.put(60, 400F);
        listOfNotes.put(61, 2000F);
        listOfNotes.put(62, 350F);
        listOfNotes.put(63, 500F);

        listOfNotes.put(64, 300F);
        listOfNotes.put(65, 500F);
        listOfNotes.put(66, 350F);
        listOfNotes.put(67, 600F);
        listOfNotes.put(68, 450F);
        listOfNotes.put(69, 200F);
        listOfNotes.put(70, 4500F);
        listOfNotes.put(71, 200F);

        listOfNotes.put(72, 400F);
        listOfNotes.put(73, 600F);
        listOfNotes.put(74, 350F);
        listOfNotes.put(75, 600F);
        listOfNotes.put(76, 350F);
        listOfNotes.put(77, 2000F);
        listOfNotes.put(78, 400F);
        listOfNotes.put(79, 600F);

        listOfNotes.put(80, 400F);
        listOfNotes.put(81, 6000F);
        listOfNotes.put(82, 350F);
        listOfNotes.put(83, 600F);
        listOfNotes.put(84, 350F);
        listOfNotes.put(85, 2000F);
        listOfNotes.put(86, 400F);
        listOfNotes.put(87, 6000F);

        listOfNotes.put(88, 500F);
        listOfNotes.put(89, 3000F);
        listOfNotes.put(90, 370F);
        listOfNotes.put(91, 200F);
        listOfNotes.put(92, 350F);
        listOfNotes.put(93, 2200F);
        listOfNotes.put(94, 400F);
        listOfNotes.put(95, 550F);





    }
    @Override
    public void render(float delta) throws NullPointerException {
        // clear the screen with a dark blue color.
        // The arguments to clear are the red, green blue and alpha component in the range [0,1] of the color to be used to clear the screen.

        Gdx.gl.glClearColor(0,0,0,1.1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Texture img1 = new Texture("background/bg.jpg");
        batch.begin();
        batch.draw(img1 ,0, 0);
        batch.end();
        hud.draw(batch);

        // load the note sound effect and the music
        // start the playback of the music immediately
        music1.setVolume(0.2f);
        music1.play();



        // tell the camera to update its matrices.
        camera.update();


        // tell the SpriteBatch to render in the coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the character and all notes
        batch.begin();
        batch.draw(characterImage1, character.x, character.y);
        for(Rectangle note: notes) {
            batch.draw(noteTexture1, note.x, note.y);
        }
        batch.end();



        // move character to the left when LEFT key is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        // move character to the right when RIGHT key is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            character.x += 200 * Gdx.graphics.getDeltaTime();
        }

        // increase the speed of the character to -600 when LEFT is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            character.x -= 600 * Gdx.graphics.getDeltaTime();
        }

        // increase the speed of the character to 600 when RIGHT is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            character.x += 600 * Gdx.graphics.getDeltaTime();
        }

        // close the app when the user press ESCAPE key
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        // make sure the character stays within the screen bounds
        if(character.x < 0) character.x = 0;
        if(character.x > 800 - 64) character.x = 800 - 64;

        //sets the BPM to 151 to match the song -> 397350993 (current)
        //sets the BPM to 160 to match the song -> 395000000
        if(TimeUtils.nanoTime() - lastDropTime > 397350993) {
            spawnNote(counter);
            counter++;

        }

        // move the notes down, remove any that are beneath the bottom edge of
        // the screen or that hit the character. In the latter case we play back
        // a sound effect if the hit accuracy is 100 or 300.
            for (Iterator<Rectangle> iter = notes.iterator(); iter.hasNext(); ) {
                Rectangle raindrop = iter.next();
                raindrop.y -= 500 * Gdx.graphics.getDeltaTime();
                if (raindrop.y + 64 < 0) iter.remove();
                batch.begin();
                if (raindrop.y <= 64) {
                    if (raindrop.overlaps(character) && Math.abs(raindrop.x - character.x) < 20) {
                        soundEffect.play();
                        batch.draw(scoreIs300, raindrop.x, raindrop.y + 64);
                        hud.update(300);
                        iter.remove();

                    } else if (raindrop.overlaps(character) && Math.abs(raindrop.x - character.x) < 50) {
                        soundEffect.play();
                        batch.draw(scoreIs100, raindrop.x, raindrop.y + 64);
                        hud.update(100);
                        iter.remove();

                    } else {
                        batch.draw(scoreIsMiss, raindrop.x, raindrop.y + 64);
                        iter.remove();
                    }
                }
                batch.end();
            }

    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        noteTexture1.dispose();
        characterImage1.dispose();
        soundEffect.dispose();
        music1.dispose();
        batch.dispose();
    }
}
