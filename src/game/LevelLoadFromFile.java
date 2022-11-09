package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import entities.Entity;
import entities.bombAndFlame.FlameSegment;
import entities.character.Bomber;
import entities.enermies.*;
import entities.tiles.Grass;
import entities.tiles.Item.BombItem;
import entities.tiles.Item.BombPassItem;
import entities.tiles.Item.FlameItem;
import entities.tiles.Item.FlamePassItem;
import entities.tiles.Item.SpeedItem;
import entities.tiles.Item.WallPassItem;
import entities.tiles.Wall;
import entities.tiles.destroyable.Brick;
import entities.tiles.destroyable.Portal;
import graphics.Sprite;

public class LevelLoadFromFile extends LevelLoader {
    public static char[][] map;

    /**
     * Constructor LevelLoadFromFile 2 parameters.
     *
     * @param board - board
     * @param level - level
     */
    public LevelLoadFromFile(Board board, int level) {
        super(board, level);
        loadLevel(level);
    }

    /**
     * Load level.
     *
     * @param level - level
     */
    @Override
    public void loadLevel(int level) {
        File mapFile = new File("res\\levels\\Level" + level + ".txt");
        try {
            InputStream inS = new FileInputStream(mapFile);
            InputStreamReader inSR = new InputStreamReader(inS);
            BufferedReader br = new BufferedReader(inSR);
            String s = br.readLine();
            String arr[] = s.split(" ");
            this.level = Integer.parseInt(arr[0]);
            this.levelWidth = Integer.parseInt(arr[2]);
            this.levelHeight = Integer.parseInt(arr[1]);
            map = new char[levelHeight][levelWidth];
            for (int i = 0; i < levelHeight; i++) {
                String line = br.readLine();
                for (int j = 0; j < levelWidth; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
        } catch (Exception e) {
            System.out.println("Win");
            this.board.winGame();
        }
    }

    /**
     * Create entity.
     */
    @Override
    public void createEntities() {
        for (int i = 0; i < levelHeight; i++) {
            for (int j = 0; j < levelWidth; j++) {
                System.out.print(map[i][j]);
                int position = j + i * this.getLevelWidth();
                if (map[i][j] == '#') {
                    Entity e = new Wall(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == '*') {
                    Entity e = new Brick(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == ' ') {
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == 'x') {
                    Entity e = new Portal(j, i, board);
                    board.addEntity(position, e);
                } else if (map[i][j] == 'p') {
                    board.addCharacter(new Bomber(j * Sprite.DEFAULT_SIZE,
                            i * Sprite.DEFAULT_SIZE, this.board));
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == '1') {
                    board.addCharacter(new Balloon(j * Sprite.DEFAULT_SIZE,
                            i * Sprite.DEFAULT_SIZE, this.board));
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == '2') {
                    board.addCharacter(new Oneal(j * Sprite.DEFAULT_SIZE,
                            i * Sprite.DEFAULT_SIZE, this.board));
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == '3') {
                    board.addCharacter(new Doll(j * Sprite.DEFAULT_SIZE,
                            i * Sprite.DEFAULT_SIZE, this.board));
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == '4') {
                    board.addCharacter(new Kondoria(j * Sprite.DEFAULT_SIZE,
                            i * Sprite.DEFAULT_SIZE, this.board));
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == '5') {
                    board.addCharacter(new Minvo(j * Sprite.DEFAULT_SIZE,
                            i * Sprite.DEFAULT_SIZE, this.board));
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == '6') {
                    board.addCharacter(new Ghost(j * Sprite.DEFAULT_SIZE,
                            i * Sprite.DEFAULT_SIZE, this.board));
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == 'f') {
                    Entity e = new FlameItem(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == 'b') {
                    Entity e = new BombItem(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == 's') {
                    Entity e = new SpeedItem(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == 'w') {
                    Entity e = new WallPassItem(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == 'q') {
                    Entity e = new FlamePassItem(j, i);
                    board.addEntity(position, e);
                } else if (map[i][j] == 'l') {
                    Entity e = new BombPassItem(j, i);
                    board.addEntity(position, e);
                } else {
                    Entity e = new Grass(j, i);
                    board.addEntity(position, e);
                }
            }
            System.out.println();
        }
    }
}
