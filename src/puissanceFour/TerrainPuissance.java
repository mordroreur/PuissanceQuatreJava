package puissanceFour;

public class TerrainPuissance {

    public final int VALUE_FOR_WIN = 4;

    private final int sizeX;
    private final int sizeY;
    private CellulePuissance[] index;
    private Console console;

    public TerrainPuissance(){
        this(7, 6);
    }

    public TerrainPuissance(int x, int y){
        this.sizeX = x;
        this.sizeY = y;

        console = new Console();
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

    public boolean addDiscs(int column, Team team){
        CellulePuissance temporry = index[column];
        index[column] = index[column].addDiscs(team);
        if(temporry.MaxValueWin() < VALUE_FOR_WIN){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public String toString() {
        return console.draw(index[0]);
    }

    
}
