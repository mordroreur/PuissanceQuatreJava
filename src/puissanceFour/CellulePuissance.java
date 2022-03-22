package puissanceFour;

class CellulePuissance {

    private CellulePuissance[] nextCases;
    private Team team;


    protected CellulePuissance(){
        team = null;
        nextCases = new CellulePuissance[4];
        for(int i = 0; i < 4; i++){
            nextCases[i] = null;
        }
    }

    protected void setVoisin(SideEnum where, CellulePuissance who){
        nextCases[where.ordinal()] = who;
    }

    protected CellulePuissance addDiscs(Team newTeam){
        if(team != null){
            throw new IllegalStateException("This place is already taken by a team!");
        }
        this.team = newTeam;
        if(nextCases[SideEnum.UP.ordinal()] == null){
            return this;
        }
        return nextCases[SideEnum.UP.ordinal()];
    }

    protected int MaxValueWin(){
        int[] cross = {1, 1};
        //int NEtoSW = 1;
        //int NWtoSE = 1;
        for(int i = 0; i < 4; i++){// UP, right, down, left
            if(nextCases[i] != null){
                cross[i%2] += nextCases[i].verifStraight(i, team);
            }
        }
        return max(cross);
    }

    private int max(int[] cross) {
        if(cross[0] < cross[1]){
            return cross[1];
        }else{
            return cross[0];
        }
    }

    protected int verifStraight(int side, Team searchedTeam){
        int value = 0;
        if(this.team == searchedTeam){
            if(this.nextCases[side] != null){
                value = nextCases[side].verifStraight(side, team);
            }
            value += 1;
        }
        return value;
    }

    protected CellulePuissance getVoisin(SideEnum where){
        return nextCases[where.ordinal()];
    }

    public Team getTeam() {
        return team;
    }

}
