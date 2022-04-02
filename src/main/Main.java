package main;

public class Main {

    public static void main(String[] args) {

        PrincipaleFour Game = new PrincipaleFour('c');
        boolean wantToStop = false;
        
        Game.askLoad();

        while(!wantToStop){
            Game.askSetComputer();
            Game.askChangeMode();
            
            while(!Game.isWin()){
                Game.nextPlay();
            }


            wantToStop = !Game.askNewParti();
            if(!wantToStop){
                Game.resetTerrain();
            }

        }
        Game.close();
        
    }

}
