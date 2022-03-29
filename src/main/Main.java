package main;

public class Main {

    public static void main(String[] args) {

        PrincipaleFour Game = new PrincipaleFour('n');

        
        while(!Game.isWin()){
            Game.nextPlay();
        }

        Game.close();
        
    }

}
