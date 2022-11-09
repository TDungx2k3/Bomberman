package entities.character;

//import java.lang.System.Logger.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entities.Entity;
import entities.bombAndFlame.Bomb;
import entities.bombAndFlame.Flame;
import entities.enermies.Enemy;
import entities.tiles.Grass;
import entities.tiles.Tile;
import entities.tiles.Wall;
import entities.tiles.Item.WallPassItem;
import entities.tiles.destroyable.Brick;
import entities.tiles.destroyable.DestroyableTile;
import game.Board;
import game.Music;
import graphics.Screen;
import graphics.Sprite;
import input.Input;
import game.Game;
import game.LevelLoadFromFile;
import game.LevelLoader;

public class Bomber extends Character {
    public static boolean isFlamePass = false;

    public static boolean isBombPass = false;

    List<Bomb> bombsList = new ArrayList<>();

    public int timePutsBomb = 20;

    protected Input input;

    /**
     * Constructor Bomber 3 parameters.
     *
     * @param x     - x
     * @param y     - y
     * @param board - board
     */
    public Bomber(int x, int y, Board board) {
        this._x = x;
        this._y = y;
        this.board = board;
        bombsList = this.board.getBombs();
        this.input = this.board.getInput();
        this._sprite = Sprite.player_right;
    }

    /**
     * Update.
     */
    @Override
    public void update() {
        if (!isAlive) {
            this.die();
            return;
        }
        if (timePutsBomb >= 0) {
            timePutsBomb--;
        }
        animate();
        this.move();
        clearBombs();
        detectPlaceBomb();
    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    @Override
    public void render(Screen screen) {
        Screen.setStartPosition(Screen.calculateXStartPosition(board, this), 0);
        if (isAlive) {
            chooseSprite();
        } else {
            _sprite = Sprite.player_dead1;
        }
        screen.renderEntity((int) _x, (int) _y, this);
    }

    /**
     * Kill.
     */
    @Override
    public void kill() {
        this.isAlive = false;
        String fileName = "res/musics/bomberhit.wav";
        Music sound = new Music();
        sound.setFile(fileName);
        sound.play();
    }

    /**
     * Die.
     */
    @Override
    public void die() {
        if (timeAnimate > 0) {
            timeAnimate--;
        } else {
            board.endGame();
        }
    }

    /**
     * Check can move right.
     *
     * @return true if can move right, false if not
     */
    @Override
    public boolean canMoveRight() {
        int xt = ((int) this._x + 1 + 2 * Game.TILES_SIZE / 3) / Game.TILES_SIZE;
        if (xt >= this.board.getLevelLoader().getLevelWidth() - 1) {
            return false;
        }
        Entity a = board.getEntity(xt, (this._y + 4) / Game.TILES_SIZE, this);
        Entity b = board.getEntity(xt, (this._y + Game.TILES_SIZE - 1)
                / Game.TILES_SIZE, this);
        if (!a.collide(this) || !b.collide(this)) {
            return false;
        }
        return true;
    }

    /**
     * Check can move left.
     *
     * @return true if can move left, false if not
     */
    @Override
    public boolean canMoveLeft() {
        int xt = ((int) this._x - 1) / Game.TILES_SIZE;
        if (xt <= 0) {
            return false;
        }
        Entity a = board.getEntity(xt, (this._y + 4) / Game.TILES_SIZE, this);
        Entity b = board.getEntity(xt, (this._y + Game.TILES_SIZE - 1)
                / Game.TILES_SIZE, this);
        if (!a.collide(this) || !b.collide(this)) {
            return false;
        }
        return true;
    }

    /**
     * Check can move up.
     *
     * @return true if can move up, false if not
     */
    @Override
    public boolean canMoveUp() {
        int yt = ((int) this._y + 3) / Game.TILES_SIZE;
        if (yt <= 0) {
            return false;
        }
        Entity a = board.getEntity(this._x / Game.TILES_SIZE, yt, this);
        Entity b = board.getEntity((this._x + 2 * Game.TILES_SIZE / 3)
                / Game.TILES_SIZE, yt, this);
        if (!a.collide(this) || !b.collide(this)) {
            return false;
        }
        return true;
    }

    /**
     * Check can move down.
     *
     * @return true if can move down, false if not
     */
    @Override
    public boolean canMoveDown() {
        int yt = ((int) this._y + Game.TILES_SIZE) / Game.TILES_SIZE;
        if (yt >= this.board.getLevelLoader().getLevelHeight() - 1) {
            return false;
        }
        Entity a = board.getEntity((this._x) / Game.TILES_SIZE, yt, this);
        Entity b = board.getEntity((this._x + 2 * Game.TILES_SIZE / 3)
                / Game.TILES_SIZE, yt, this);
        if (!a.collide(this) || !b.collide(this)) {
            return false;
        }
        return true;
    }

    /**
     * Move.
     */
    @Override
    public void move() {
        int xa = 0;
        int ya = 0;
        if (input.up) {
            ya--;
        }
        if (input.down) {
            ya++;
        }
        if (input.left) {
            xa--;
        }
        if (input.right) {
            xa++;
        }
        if (xa == 0 && ya == 0) {
            isMoving = false;
        } else {
            isMoving = true;
        }
        if (xa > 0 && this.canMoveRight()) {
            direction = 1;
            moveRight(xa * Game.getBomberSpeed(), ya * Game.getBomberSpeed());
        }
        if (xa < 0 && this.canMoveLeft()) {
            direction = 3;
            moveLeft(xa * Game.getBomberSpeed(), ya * Game.getBomberSpeed());
        }
        if (ya > 0 && this.canMoveDown()) {
            direction = 2;
            moveDown(xa * Game.getBomberSpeed(), ya * Game.getBomberSpeed());
        }
        if (ya < 0 && this.canMoveUp()) {
            direction = 0;
            moveUp(xa * Game.getBomberSpeed(), ya * Game.getBomberSpeed());
        }
    }

    /**
     * Choose sprite.
     */
    private void chooseSprite() {
        switch (direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1,
                            Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player_right;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1,
                            Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1,
                            Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1,
                            Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1,
                            Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }

    /**
     * Detect place bomb.
     */
    private void detectPlaceBomb() {
        if (input.space && Game.getBombRate() > 0 && timePutsBomb < 0) {
            int x = (int) (this._x + _sprite.getSize() / 3) / Game.TILES_SIZE;
            int y = (int) (_y + _sprite.getSize() / 3) / Game.TILES_SIZE;
            Entity e = this.board.getEntityAt(x, y);
            if ((e instanceof DestroyableTile && !Objects.equals(e.getSprite(),
                    Sprite.grass)) || e instanceof Wall) {
                return;
            }
            placeBomb(x, y);
            Game.setBombRate(Game.getBombRate() - 1);
            timePutsBomb = 50;
        }
    }

    /**
     * Place bomb.
     *
     * @param x - x
     * @param y - y
     */
    protected void placeBomb(int x, int y) {
        Bomb bom = new Bomb(x, y, board);
        board.addBomb(bom);
    }

    /**
     * Clear bomb.
     */
    private void clearBombs() {
        for (int i = 0; i < bombsList.size(); i++) {
            Bomb b = bombsList.get(i);
            if (b.isRemoved()) {
                bombsList.remove(i);
                Game.setBombRate(Game.getBombRate() + 1);
            }
        }

    }

    /**
     * Check can through.
     *
     * @param e - e
     * @return true if can through, false if not
     */
    @Override
    public boolean canThrough(Entity e) {
        if (e instanceof Wall) {
            return false;
        }
        if (e instanceof Brick) {
            return false;
        }
        if (e instanceof Bomb) {
            return false;
        }
        if (e instanceof Flame) {
            this.kill();
        }
        return true;
    }

    /**
     * Pass Flame.
     */
    public static void passFlame() {
        isFlamePass = true;
    }

    /**
     * Pass bomb.
     */
    public static void passBomb() {
        isBombPass = true;
    }

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame && !isFlamePass) {
            this.kill();
        }
        if (e instanceof Enemy) {
            kill();
        }
        if (e instanceof Brick) {
            return true;
        }
        return false;
    }
    
    public boolean getIsAlive() {
    	return this.isAlive;
    }
}
