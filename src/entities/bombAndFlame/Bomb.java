package entities.bombAndFlame;

import entities.Entity;
import entities.EntityUseAnimation;
import game.Music;
import graphics.Screen;
import graphics.Sprite;
import entities.character.Bomber;
import entities.character.Character;
import entities.enermies.Enemy;
import game.Board;
import game.Game;

public class Bomb extends EntityUseAnimation {
    private boolean isPass = true;

    protected int timeToExplode = 120;

    protected final int timeBomb = 20;

    protected Flame[] flamesList;

    protected boolean isExploded = false;

    protected int timeAfterBomb = 20;

    public int bombRadius = 1;

    /**
     * Constructor Bomb 3 parameters.
     *
     * @param x     - x
     * @param y     - y
     * @param board - board
     */
    public Bomb(int x, int y, Board board) {
        _x = x;
        _y = y;
        this.board = board;
        _sprite = Sprite.bomb;
    }

    /**
     * Explode.
     */
    public void explode() {
        this.isExploded = true;
        String fileName = "res/musics/soundBomb.wav";
        Music sound = new Music();
        sound.setFile(fileName);
        sound.play();
        Character x = board.getCharacterAtExcluding((int) _x, (int) _y, null);
        if (x != null) {
            x.kill();
        }
        flamesList = new Flame[4];
        for (int i = 0; i < flamesList.length; i++) {
            flamesList[i] = new Flame((int) _x, (int) _y, i, Game.getBombRadius(), board);
        }
    }

    /**
     * Update.
     */
    @Override
    public void update() {
        if (timeToExplode >= 0) {
            timeToExplode--;
        } else {
            if (!isExploded) {
                explode();
            } else {
                updateFlames();
            }
            if (timeAfterBomb >= 0) {
                timeAfterBomb--;
            } else {
                this.remove();
            }
        }
    }

    /**
     * Flame at.
     *
     * @param x - x
     * @param y - y
     * @return flame at
     */
    public FlameSegment flameAt(int x, int y) {
        if (!isExploded) {
            return null;
        }
        for (int i = 0; i < flamesList.length; i++) {
            if (flamesList[i] == null) {
                return null;
            }
            FlameSegment e = flamesList[i].flameSegmentAt(x, y);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    /**
     * Render.
     *
     * @param screen- screen
     */
    @Override
    public void render(Screen screen) {
        if (isExploded) {
            _sprite = Sprite.bomb_exploded2;
            renderFlames(screen);
        } else {
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1,
                    Sprite.bomb_2, _animate, 60);
        }
        int x = (int) _x * Game.TILES_SIZE;
        int y = (int) _y * Game.TILES_SIZE;
        if (!isRemoved()) {
            screen.renderEntity(x, y, this);
        }
    }

    /**
     * Update flame.
     */
    public void updateFlames() {
        for (int i = 0; i < flamesList.length; i++) {
            flamesList[i].update();
        }
    }

    /**
     * Render flame.
     *
     * @param screen - screen
     */
    public void renderFlames(Screen screen) {
        for (int i = 0; i < flamesList.length; i++) {
            flamesList[i].render(screen);
        }
    }

    /**
     * Check can through.
     *
     * @param e - e
     * @return true
     */
    @Override
    public boolean canThrough(Entity e) {
        return true;
    }

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            if (((Bomber) e).isBombPass) {
                return true;
            }
            double diffX = e.getX() - this._x * Game.TILES_SIZE;
            double diffY = e.getY() - this._y * Game.TILES_SIZE;
            if (!((diffX > -11 && diffX < 16) && (diffY > -15 && diffY < 12))) {
                isPass = false;
            }
            return isPass;
        }
        if (e instanceof Flame) {
            timeToExplode = 0;
        }
        if (e instanceof Enemy) {
            return false;
        }
        return false;
    }
}