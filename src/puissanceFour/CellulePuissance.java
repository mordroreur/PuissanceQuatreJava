package puissanceFour;

class CellulePuissance {

    private CellulePuissance[] nextCases;
    private Team team;


    protected CellulePuissance(){
        nextCases = new CellulePuissance[4];
        for(int i = 0; i < 4; i++){
            nextCases[i] = null;
        }
    }

    protected void setVoisin(SideEnum where, CellulePuissance who){
        nextCases[where.ordinal()] = who;
    }
}
