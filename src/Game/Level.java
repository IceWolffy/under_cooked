package Game;

import utils.LevelData;

public class Level {
    private int[][] terrain;
    private int[][] background;
    private int[][] foreground;

    public Level(LevelData data) {
        this.terrain = data.terrain;
        this.background = data.background;
        this.foreground = data.foreground;
    }

    public int getTerrainAndDecoSpriteIndex(int x, int y) {
        return terrain[y][x];
    }

    public int getBackgroundSpriteIndex(int x, int y) {
        return background[y][x];
    }

    public int getForegroundSpriteIndex(int x, int y) {
        return foreground[y][x];
    }
}
