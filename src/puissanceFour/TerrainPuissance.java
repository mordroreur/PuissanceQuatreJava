package puissanceFour;

public class TerrainPuissance {
    private int sizeX;
    private int sizeY;
    private CellulePuissance[][] tableau;

    public TerrainPuissance(){
        this(7, 6);
    }

    public TerrainPuissance(int x, int y){
        this.sizeX = x;
        this.sizeY = y;

        this.tableau = new CellulePuissance[x][y];
        
    }

    
}
