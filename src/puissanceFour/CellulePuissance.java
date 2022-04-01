package puissanceFour;

public class CellulePuissance {

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

    protected void removeDiscs(){
        this.team = null;
    }

    protected int MaxValueWin(){
        int[] cross = {1, 1, 1, 1};
        //int NEtoSW = 1;
        //int NWtoSE = 1;
        for(int i = 0; i < 4; i++){// UP, right, down, left
            if(nextCases[i] != null){
                cross[i%2] += nextCases[i].verifStraight(i, team);
            }
        }

        for(int i = 0;  i < 4 ; i++){
            if(nextCases[i] != null && nextCases[i].nextCases[(i+1)%4] != null){
                cross[(i%2)+2] += nextCases[i].nextCases[(i+1)%4].verifDiagonal(i, (i+1)%4,team);
            }
        }
        return max(cross);
    }

    private int max(int[] cross) {
        int mx = cross[0];
        for (int i : cross) {
            if(i > mx) mx = i;
        }
        return mx;
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

    protected int verifDiagonal(int side1, int side2, Team searchedTeam){
        int value = 0;
        if(this.team == searchedTeam){
            if(this.nextCases[side1] != null && this.nextCases[side1].nextCases[side2] != null){
                value = nextCases[side1].nextCases[side2].verifDiagonal(side1, side2, team);
            }
            value += 1;
        }
        return value;
    }

    public CellulePuissance getVoisin(SideEnum where){
        return nextCases[where.ordinal()];
    }

    public Team getTeam() {
        return team;
    }

}
