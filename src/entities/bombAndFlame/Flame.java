package entities.bombAndFlame;

import entities.Entity;
import entities.enermies.Balloon;
import entities.enermies.Enemy;
import entities.tiles.Wall;
import entities.tiles.destroyable.Brick;
import graphics.Screen;
import graphics.Sprite;
import game.Board;
import entities.bombAndFlame.FlameSegment;
import entities.character.Bomber;

public class Flame extends Entity {
    protected FlameSegment[] _flameSegments = new FlameSegment[0];

    protected int radius = 1;

    protected int direction;

    /**
     * Constructor Flame 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public Flame(int x, int y, Sprite sprite) {
        this._x = x;
        this._y = y;
        this._sprite = sprite;
    }

    /**
     * Constructor Flame 5 parameters.
     *
     * @param x         - x
     * @param y         - y
     * @param direction - direction
     * @param radius    - radius
     * @param board     - board
     */
    public Flame(int x, int y, int direction, int radius, Board board) {
        _x = x;
        _y = y;
        this.direction = direction;
        this.radius = radius;
        this.board = board;
        createFlameSegments();
    }

    /**
     * Create flame segments.
     */
    private void createFlameSegments() {
        _flameSegments = new FlameSegment[calculatePermitedDistance()];
        boolean last = false;
        int x = (int) _x;
        int y = (int) _y;
        for (int i = 0; i < _flameSegments.length; i++) {
            if (i == _flameSegments.length - 1) {
                last = true;
            } else {
                last = false;
            }
            switch (direction) {
                case 0:
                    y--;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    y++;
                    break;
                case 3:
                    x--;
                    break;
            }
            _flameSegments[i] = new FlameSegment(x, y, direction, last);
        }
    }

    /**
     * Calculate permited distance.
     *
     * @return permited distance
     */
    private int calculatePermitedDistance() {
        int r = 0;
        int x = (int) _x;
        int y = (int) _y;
        while (r < radius) {
            if (direction == 0) {
                y--;
            }
            if (direction == 1) {
                x++;
            }
            if (direction == 2) {
                y++;
            }
            if (direction == 3) {
                x--;
            }
            Entity a = board.getEntity(x, y, null);
            if (a instanceof Bomb) {
                ++radius;
            }

            if (a.collide(this) == false) {
                break;
            }
            ++r;
        }
        return r;
    }

    /**
     * Update.
     */
    @Override
    public void update() {

    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    @Override
    public void render(Screen screen) {
        for (int i = 0; i < _flameSegments.length; i++) {
            _flameSegments[i].render(screen);
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
        if (e instanceof Bomber && !((Bomber) e).isFlamePass) {
            ((Bomber) e).kill();
        }
        if (e instanceof Brick) {
            ((Brick) e).destroy();
        }
        if (e instanceof Enemy) {
            ((Enemy) e).kill();
        }
        return true;
    }

    /**
     * Flame segment at.
     *
     * @param x - x
     * @param y - y
     * @return flame segment at
     */
    public FlameSegment flameSegmentAt(int x, int y) {
        for (int i = 0; i < _flameSegments.length; i++) {
            if (_flameSegments[i].getX() == x && _flameSegments[i].getY() == y) {
                return _flameSegments[i];
            }
        }
        return null;
    }
}
