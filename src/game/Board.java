package game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.Entity;
import entities.bombAndFlame.Bomb;
import entities.bombAndFlame.FlameSegment;
import entities.tiles.Message;
import graphics.Screen;
import input.Input;
import entities.character.Bomber;
import entities.character.Character;

public class Board {
    protected LevelLoader _levelLoader;

    protected Game game;

    protected Input input;

    protected Screen screen;

    public Entity[] entitiesList;

    public static List<Character> charactersList = new ArrayList<>();

    protected List<Bomb> bombsList = new ArrayList<>();

    private List<Message> messages = new ArrayList<>();

    private int _screenToShow = -1;

    private int _time = Game.TIME;

    private int _points = Game.POINTS;

    /**
     * Constructor Board 3 parameters.
     *
     * @param game   - game
     * @param input  - input
     * @param screen - screen
     */
    public Board(Game game, Input input, Screen screen) {
        this.game = game;
        this.input = input;
        this.screen = screen;
        loadLevel(1);
    }

    /**
     * Update.
     */
    public void update() {
        if (game.isPaused()) {
            return;
        }
        updateEntities();
        updateCharacters();
        updateBombs();
        updateMessage();
        detectEndGame();
        for (int i = 0; i < charactersList.size(); i++) {
            Character a = charactersList.get(i);
            if (a.isRemoved()) {
                charactersList.remove(i);
                i--;
            }
        }
    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    public void render(Screen screen) {
        if (game.isPaused()) {
            return;
        }
        int x0 = Screen.startX / Game.TILES_SIZE;

        int x1 = (Screen.startX + screen.getScreenWidth())
                / Game.TILES_SIZE + 1;
        int y0 = Screen.startY / Game.TILES_SIZE;
        int y1 = (Screen.startY + screen.getScreenHeight())
                / Game.TILES_SIZE;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                entitiesList[x + y * _levelLoader.getLevelWidth()].render(screen);
            }
        }
        renderBombs(screen);
        renderCharacter(screen);
    }

    /**
     * Load level.
     *
     * @param level - level
     */
    public void loadLevel(int level) {
        String soundTrack = "res/musics/gamestart.wav";
        Music sound = new Music();
        sound.setFile(soundTrack);
        sound.play();
        
        Screen.startX = 0;
        Screen.startY = 0;
        _time = Game.TIME;
        _screenToShow = 2;
        game.resetScreenDelay();
        game.pause();
        charactersList.clear();
        bombsList.clear();
        try {
            _levelLoader = new LevelLoadFromFile(this, level);
            entitiesList = new Entity[_levelLoader.getLevelHeight()
                    * _levelLoader.getLevelWidth()];
            _levelLoader.createEntities();
        } catch (Exception e) {
            endGame();
        }
    }

    /**
     * Next level.
     */
    public void nextLevel() {
        Game.setBombRadius(1);
        Game.setBombRate(1);
        Game.isPlayBackGroundMusic = 0;
        System.out.println(this._levelLoader.getLevel() + 1);
        loadLevel(this._levelLoader.getLevel() + 1);
    }

    /**
     * Detect end game.
     */
    protected void detectEndGame() {
        if (_time <= 0) {
            endGame();
        }
    }

    /**
     * Pause game.
     */
    public void pauseGame() {
        game.resetScreenDelay();
        if (_screenToShow <= 0) {
            setShow(3);
        }
        game.pause();
    }

    /**
     * Resume game.
     */
    public void resumeGame() {
        game.resetScreenDelay();
        setShow(-1);
        game.run();
    }

    /**
     * End game.
     */
    public void endGame() {
        _screenToShow = 1;
        String soundTrack = "res/musics/gameover.wav";
        Music sound = new Music();
        sound.setFile(soundTrack);
        sound.play();
        game.resetScreenDelay();
        this.game.isPaused = true;
        Game.isPlayBackGroundMusic = -1;
    }

    /**
     * Win game.
     */
    public void winGame() {
        _screenToShow = 4;
    }

    /**
     * Detect no enemy.
     *
     * @return total enemy = 0
     */
    public static boolean detectNoEnemies() {
        int total = 0;
        for (int i = 0; i < charactersList.size(); i++) {
            if (charactersList.get(i) instanceof Bomber == false) {
                ++total;
            }
        }
        return total == 0;
    }

    /**
     * Draw screen.
     *
     * @param g - g
     */
    public void drawScreen(Graphics g) {
        switch (_screenToShow) {
            case 1:
                screen.drawEndGame(g, _points);
                break;
            case 2:
                screen.drawChangeLevel(g, _levelLoader.getLevel());
                break;
            case 3:
                screen.drawPaused(g);
                break;
            case 4:
                screen.drawWinGame(g, _points);
        }
    }

    /**
     * Get Entity.
     *
     * @param x - x
     * @param y - y
     * @param m - m
     * @return entity
     */
    public Entity getEntity(double x, double y, Character m) {
        Entity res = null;
        res = getFlameSegmentAt((int) x, (int) y);
        if (res != null) {
            return res;
        }
        res = getBombAt(x, y);
        if (res != null) {
            return res;
        }
        res = getCharacterAtExcluding((int) x, (int) y, m);
        if (res != null) {
            return res;
        }
        res = getEntityAt((int) x, (int) y);
        return res;
    }

    /**
     * Get bombs.
     *
     * @return bombs list
     */
    public List<Bomb> getBombs() {
        return bombsList;
    }

    /**
     * Get bomb at.
     *
     * @param x - x
     * @param y - y
     * @return bomb at
     */
    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = bombsList.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.getX() == (int) x && b.getY() == (int) y) {
                return b;
            }
        }
        return null;
    }

    /**
     * Get bomber.
     *
     * @return bomber.
     */
    public Bomber getBomber() {
        Iterator<Character> itr = charactersList.iterator();
        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur instanceof Bomber) {
                return (Bomber) cur;
            }
        }
        return null;
    }

    /**
     * Get character at excluding.
     *
     * @param x - x
     * @param y - y
     * @param a - a
     * @return character at excluding
     */
    public Character getCharacterAtExcluding(int x, int y, Character a) {
        Iterator<Character> itr = charactersList.iterator();
        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur == a) {
                continue;
            }
            if (cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }
        }
        return null;
    }

    /**
     * Get flame segment at.
     *
     * @param x - x
     * @param y - y
     * @return flame segment at
     */
    public FlameSegment getFlameSegmentAt(int x, int y) {
        Iterator<Bomb> bs = bombsList.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            FlameSegment e = b.flameAt(x, y);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    /**
     * Get entity at.
     *
     * @param x - x
     * @param y - y
     * @return entity at
     */
    public Entity getEntityAt(double x, double y) {
        return entitiesList[(int) x + (int) y * _levelLoader.getLevelWidth()];
    }

    /**
     * Add entity.
     *
     * @param pos - pos
     * @param e   - e
     */
    public void addEntity(int pos, Entity e) {
        entitiesList[pos] = e;
    }

    /**
     * Add character.
     *
     * @param e - e
     */
    public void addCharacter(Character e) {
        charactersList.add(e);
    }

    /**
     * Add bomb.
     *
     * @param e - e
     */
    public void addBomb(Bomb e) {
        bombsList.add(e);
    }

    /**
     * Render character.
     *
     * @param screen - screen
     */
    protected void renderCharacter(Screen screen) {
        Iterator<Character> itr = charactersList.iterator();
        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    /**
     * Render bomb.
     *
     * @param screen - screen
     */
    protected void renderBombs(Screen screen) {
        Iterator<Bomb> itr = bombsList.iterator();
        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    /**
     * Update entity.
     */
    protected void updateEntities() {
        if (game.isPaused()) {
            return;
        }
        for (int i = 0; i < entitiesList.length; i++) {
            entitiesList[i].update();
        }
    }

    /**
     * Update character.
     */
    protected void updateCharacters() {
        if (game.isPaused()) {
            return;
        }
        Iterator<Character> itr = charactersList.iterator();
        while (itr.hasNext() && !game.isPaused()) {
            itr.next().update();
        }
    }

    /**
     * Update bomb.
     */
    protected void updateBombs() {
        if (game.isPaused()) {
            return;
        }
        Iterator<Bomb> itr = bombsList.iterator();
        while (itr.hasNext()) {
            itr.next().update();
        }
    }

    /**
     * Subtract time.
     *
     * @return time
     */
    public int subtractTime() {
        if (game.isPaused()) {
            return this._time;
        } else {
            return this._time--;
        }
    }

    /**
     * Start a new game.
     */
    public void startANewGame() {
        resetGame();
        Game.isPlayBackGroundMusic = 0;
        loadLevel(1);
    }

    /**
     * Reset game.
     */
    private void resetGame() {
        _points = 0;
        Game.setBombRate(1);
        Game.setBombRadius(1);
        Game.setBomberSpeed(1);
    }

    /**
     * Add message.
     *
     * @param message - message
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Render message.
     *
     * @param g - g
     */
    public void renderMessage(Graphics g) {
        Message message;
        for (int i = 0; i < messages.size(); ++i) {
            message = messages.get(i);
            g.setFont(new Font("Arial", Font.PLAIN, message.getSize()));
            g.setColor(message.getColor());
            g.drawString(message.getMessage(), (int) message.getX()
                    - Screen.startX * Game.scale, (int) message.getY());
        }
    }

    /**
     * Update message.
     */
    public void updateMessage() {
        if (game.isPaused) {
            return;
        }
        Message message;
        int left = 0;
        for (int i = 0; i < messages.size(); ++i) {
            message = messages.get(i);
            left = message.getDuration();
            if (left > 0) {
                message.setDuration(--left);
            } else {
                messages.remove(i);
            }
        }
    }

    public Input getInput() {
        return input;
    }

    public int getLevel() {
        return _levelLoader.level;
    }

    public Game getGame() {
        return game;
    }

    public int getShow() {
        return _screenToShow;
    }

    /**
     * Set show.
     *
     * @param i - i
     */
    public void setShow(int i) {
        _screenToShow = i;
    }

    public int getTime() {
        return _time;
    }

    public int getPoints() {
        return _points;
    }

    /**
     * Add point.
     *
     * @param points - point
     */
    public void addPoints(int points) {
        this._points += points;
    }

    public int getWidth() {
        return _levelLoader.getLevelWidth();
    }

    public int getHeight() {
        return _levelLoader.getLevelHeight();
    }

    public LevelLoader getLevelLoader() {
        return _levelLoader;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
