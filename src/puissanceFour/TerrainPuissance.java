package puissanceFour;

public class TerrainPuissance {
    private final int sizeX;
    private final int sizeY;
    private CellulePuissance[] index;

    public TerrainPuissance(){
        this(7, 6);
    }

    public TerrainPuissance(int x, int y){
        this.sizeX = x;
        this.sizeY = y;

        index = new CellulePuissance[this.sizeX];

        CellulePuissance[][] tableau = new CellulePuissance[x][y];
        for(int i = 0; i < this.sizeX; i++){
            for(int j = 0; j < this.sizeY; j++){
                tableau[i][j] = new CellulePuissance();
            }
        }

        for(int i = 0; i < this.sizeX; i++){
            for(int j = 0; j < this.sizeY; j++){
                if(i < x-1){
                     tableau[i][j].setVoisin(SideEnum.RIGHT, tableau[i+1][j]);
                }
                if(i > 0){
                     tableau[i][j].setVoisin(SideEnum.LEFT, tableau[i-1][j]);
                }
                if(j < y-1){
                     tableau[i][j].setVoisin(SideEnum.DOWN, tableau[i][j+1]);
                }
                if(j > 0){
                     tableau[i][j].setVoisin(SideEnum.UP, tableau[i][j-1]);
                }
            }
            index[i] = tableau[i][this.sizeY-1];
        }
    }

    
}
