package puissanceFour;

class Console {

    protected Console(){}

    private CellulePuissance goToDirection(SideEnum dir, CellulePuissance cell)
    {
        while(cell.getVoisin(dir) != null)
        {
            cell = cell.getVoisin(dir);
        }
        return cell;
    }

    protected String draw(CellulePuissance cell){
        cell = goToDirection(SideEnum.UP, cell);
        cell = goToDirection(SideEnum.LEFT, cell);
        String s = "";
        CellulePuissance cellCopy = cell;
        while(cellCopy != null)
        {
            s += "|";
            while(cell != null){
                if(cell.getTeam() != null)
                {
                    s += " O |";
                }
                else
                {
                    s += "   |";
                }
                cell = cell.getVoisin(SideEnum.RIGHT);
            }
            s += "\n";
            cellCopy = cellCopy.getVoisin(SideEnum.DOWN);
            cell = cellCopy;
        }
        return s;
    }   
}
