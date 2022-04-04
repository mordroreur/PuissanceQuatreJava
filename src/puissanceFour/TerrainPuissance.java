package puissanceFour;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


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
        CellulePuissance[][] tableau = initCellules(x, y);

        for (int i = 0; i < this.sizeX; i++)
        {
            index[i] = tableau[i][this.sizeY-1];
        }
    }

    public TerrainPuissance(String loadFile, Team[] teams){
        try {
            File F = new File("save" + System.getProperty("file.separator") + loadFile);
            Scanner sc = new Scanner(F);
            sc.useDelimiter(":|;|\\n");
            this.sizeY = Integer.parseInt(sc.next());
            this.sizeX = Integer.parseInt(sc.next());
            sc.next();
            index = new CellulePuissance[this.sizeX];
            CellulePuissance[][] tableau = initCellules(this.sizeX , this.sizeY);

            for (int i = 0; i < this.sizeX; i++)
            {
                index[i] = tableau[i][this.sizeY-1];
            }
            console = new Console();

            int i = 0;
            while (sc.hasNext())
            {
                String next = sc.next();
                if(next.contains("!"))
                {
                    i++;
                }
                else{
                    this.addDiscs(i, teams[Integer.parseInt(next)]);
                }
            }
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("File not found!");
        }
    }


    public CellulePuissance[][] initCellules(int x, int y) {
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
        }
        return tableau;

    }

    public int turnOfWho()
    {
        int[] count = {0, 0};
        for (int i = 0 ; i < index.length ; i++)
        {   
            CellulePuissance cell = index[i];
            while(cell != null && cell.getTeam() != null)
            {
                count[cell.getTeam().getId()]++;
                cell = cell.getVoisin(SideEnum.DOWN);
            }
        }

        if (count[0] > count[1]){
            return 1;
        }else if (count[0] < count[1]){
            return 0;
        }else {
            return (Math.random() < 0.5)? 1 : 0;
        }
            


    }

    public boolean addConditionalDiscs(int column){
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

    public int nbrAlign(int column)
    {
        CellulePuissance temp = index[column];
        if (temp.getTeam() != null)
        {
            return temp.MaxValueWin();
        } 
        else{
            return temp.getVoisin(SideEnum.DOWN).MaxValueWin();
        }
    }

    public boolean addDiscs(int column, Team team, int nbrToWin){
        CellulePuissance temporry = index[column];
        index[column] = index[column].addDiscs(team);
        if(temporry.MaxValueWin() < nbrToWin){
            return false;
        }else{
            return true;
        }
    }

    public void removeDiscs(int column)
    {
        if (index[column].getTeam() != null){ index[column].removeDiscs(); }
        else {
            index[column] = index[column].getVoisin(SideEnum.DOWN);
            index[column].removeDiscs();
        }
    }

    public boolean testWin(int column, Team team, Team team2, int nbrToWin){
        if(!addConditionalDiscs(column)) return false;
        this.addDiscs(column, team);
        if(!addConditionalDiscs(column)) return false;
        boolean result = this.addDiscs(column, team2, nbrToWin);
        
        removeDiscs(column);
        removeDiscs(column);
        return result;
    }

    public boolean testWin(int column, Team team, int nbrToWin){
        boolean result = false;
        CellulePuissance temporry = index[column];
        index[column] = index[column].addDiscs(team);
        if(temporry.MaxValueWin() < nbrToWin){
            result = false;
        }else{
            result = true;
        }
        removeDiscs(column);
        return result;
    }

    public boolean testWin(int column, Team team){
        boolean result = this.addDiscs(column, team);
        removeDiscs(column);
        return result;
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
            file.write(this.sizeY + ":" + this.sizeX + ";\n" + console.draw(index[0]));
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public TerrainPuissance copy()
    {
        TerrainPuissance T = new TerrainPuissance(this.sizeX, this.sizeY);
        CellulePuissance cell = console.goToDirection(SideEnum.DOWN, index[0]);

        for (int i = 0; i < this.sizeX ; i++){
            CellulePuissance cellCopy = cell;
            while(cell.getVoisin(SideEnum.UP) != null && cell.getTeam() != null)
            {
                
                T.addDiscs(i, cell.getTeam());
                cell = cell.getVoisin(SideEnum.UP);
            }
            cell = cellCopy.getVoisin(SideEnum.RIGHT);
        }


        return T;
    }

    public CellulePuissance getLeftLine(){
        return this.index[0];
    }

    public int getWidth(){
        return this.sizeX;
    }
    public int getHeight(){
        return this.sizeY;
    }
    
}
