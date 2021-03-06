package puissanceFour;



public class Console {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public Console(){}

    public CellulePuissance goToDirection(SideEnum dir, CellulePuissance cell)
    {
        while(cell.getVoisin(dir) != null)
        {
            cell = cell.getVoisin(dir);
        }
        return cell;
    }

    protected String drawColor(CellulePuissance cell){
        cell = goToDirection(SideEnum.UP, cell);
        cell = goToDirection(SideEnum.LEFT, cell);
        String s = "";
        CellulePuissance cellCopy = cell;
        CellulePuissance cellCopy2 = cell;
        while(cellCopy != null)
        {
            s += ANSI_RESET + "|";
            while(cell != null){
                if(cell.getTeam() != null)
                {
                    String color = (cell.getTeam().getId() == 1) ? ANSI_YELLOW : ANSI_RED;
                    s += color + " " + ((cell.getTeam().getId() == 1) ? "£" : "$") + " " + ANSI_RESET + "|";
                }
                else
                {
                    s += ANSI_RESET + "   |";
                }
                cell = cell.getVoisin(SideEnum.RIGHT);
            }
            s += "\n";
            cellCopy = cellCopy.getVoisin(SideEnum.DOWN);
            cell = cellCopy;
        }
        int i = 1;
        s += " ";
        while(cellCopy2 != null)
        {
            s += " " + i + "  ";
            i++;
            cellCopy2 = cellCopy2.getVoisin(SideEnum.RIGHT);
        }
        return s;
    }   

    protected String draw(CellulePuissance cell){
        cell = goToDirection(SideEnum.DOWN, cell);
        cell = goToDirection(SideEnum.LEFT, cell);
        String s = "";
        CellulePuissance cellCopy = cell;
        while(cellCopy != null)
        {
            while(cell != null){
                if(cell.getTeam() != null){
                    s += cell.getTeam().getId() + ":";
                }
                cell = cell.getVoisin(SideEnum.UP);
            }
            s += "!\n";
            cellCopy = cellCopy.getVoisin(SideEnum.RIGHT);
            cell = cellCopy;
        }
        return s;
    }
}
