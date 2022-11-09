package entities.enermies;

import entities.Entity;
import entities.bombAndFlame.Flame;
import entities.character.Bomber;
import entities.character.Character;
import entities.enermies.AI.AI;
import game.Board;
import game.Game;
import graphics.Screen;
import graphics.Sprite;

public abstract class Enemy extends Character {
    protected int points;

    protected double speed;

    protected final double MAX_STEPS;

    protected final double rest;

    protected double steps;

    protected int finalAnimation = 60;

    protected Sprite deadSprite;

    protected AI ai;

    /**
     * Constructor Enemy 6 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param board  - board
     * @param dead   - dead
     * @param speed  - speed
     * @param points - point
     */
    public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
        this._x = x;
        this._y = y;
        this.board = board;
        this.deadSprite = dead;
        this.points = points;
        this.speed = speed;
        MAX_STEPS = Game.TILES_SIZE / this.speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        steps = MAX_STEPS;
        timeAnimate = 20;
    }

    /**
     * Update.
     */
    @Override
    public void update() {
        if (!this.isAlive) {
            this.die();
            return;
        } else {
            calculateMove();
        }
    }

    /**
     * Calculate move.
     */
    public void calculateMove() {
        int yTmp = 0;
        int xTmp = 0;
        if (steps <= 0) {
            direction = ai.calculateDirection();
            steps = MAX_STEPS;
        }
        if (this.direction == 0) {
            yTmp++;
        }
        if (this.direction == 1) {
            xTmp++;
        }
        if (this.direction == 2) {
            xTmp--;
        }
        if (this.direction == 3) {
            yTmp--;
        }
        
        if (canMove(xTmp , yTmp )) {
            steps -= 1 + rest;
            move(xTmp * speed, yTmp * speed);
            isMoving = true;
        } else {
            steps = 0;
            isMoving = false;
        }
    }

    /**
     * Check can move.
     *
     * @param x - x
     * @param y - y
     * @return true if can move, false if not
     */
    public boolean canMove(int x, int y) {
        if (!isAlive) {
            return false;
        }
        double xx = this._x + 2;
        double yy = this._y + 2;
        if (this.direction == 0) {
            yy += this._sprite.getSize() - 4;
        }
        if (this.direction == 1) {
            xx += this._sprite.getSize() - 4;
        }
        
        if (!isAlive) {
            return false;
        }
        Entity e = board.getEntity((xx + x)
                / Sprite.DEFAULT_SIZE, (yy + y) / Sprite.DEFAULT_SIZE, this);
        return e.collide(this);
    }

    /**
     * Move.
     *
     * @param xa - xa
     * @param ya - ya
     */
    public void move(double xa, double ya) {
        if (!isAlive) {
            return;
        }
        _y += ya;
        _x += xa;
    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    @Override
    public void render(Screen screen) {
        if (!isAlive) {
            if (timeAnimate > 0) {
                this._sprite = deadSprite;
                _animate = 0;
            } else {
                this._sprite = Sprite.movingSprite(Sprite.mob_dead1,
                        Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }
        } else {
            choosingSprite();
        }
        screen.renderEntity((int) _x, (int) _y, this);
    }

    @Override
    public abstract void kill();

    /**
     * Die.
     */
    @Override
    public void die() {
        this.isAlive = false;
        if (this.timeAnimate >= 0) {
            timeAnimate--;
        } else if (this.finalAnimation >= 0) {
            this.finalAnimation--;
        } else {
            this.remove();
        }
    }

    @Override
    public abstract boolean canThrough(Entity e);

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            this.kill();
            return false;
        }
        if (e instanceof Bomber) {
            ((Bomber) e).kill();
            return false;
        }
        return true;
    }

    public abstract void choosingSprite();

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}