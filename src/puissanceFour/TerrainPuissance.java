package puissanceFour;
import java.io.FileWriter;
import java.io.IOException;


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

    public boolean addConditionalDiscs(int column, Team team){
        if(column > this.sizeY || column < 0 || index[column].getTeam() != null) return false;
        return true;
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

    public boolean isFull()
    {
        for (CellulePuissance cell : index) {
            if(cell.getTeam() == null) return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return console.drawColor(index[0]);
    }

    public void save(String nameFile) {
        try {
            FileWriter file = new FileWriter("save" + System.getProperty("file.separator") + nameFile);//new FileWriter("./../save/" + nameFile);
            file.write(this.sizeY + " " + this.sizeX + "\n" + console.draw(index[0]));
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}
