package Game;

public class Level {
    private int[][] levelData;

    public Level(int[][] levelData) {
        this.levelData = levelData;
    }

    public int getBackgroundSpriteIndex(int x, int y) {
        // Logic to fetch background sprite index from levelData
        // You can use a specific value to indicate background tiles
        return levelData[y][x]; // Adjust based on how you store the data
    }

    public int getTerrainAndDecoSpriteIndex(int x, int y) {
        // Logic to fetch terrain and decoration sprite index from levelData
        return levelData[y][x]; // Adjust based on how you store the data
    }

    public int getForegroundSpriteIndex(int x, int y) {
        // Logic to fetch foreground sprite index from levelData
        return levelData[y][x]; // Adjust based on how you store the data
    }
}
