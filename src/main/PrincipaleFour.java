package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import puissanceFour.Team;
import puissanceFour.TerrainPuissance;

public class PrincipaleFour {

    private Scanner sc;

    private TerrainPuissance ter;
    private Team[] teams;
    private boolean consoleMode;
    private boolean isWin;
    private int nbPlayer;

    private int actualPlayer;

    public PrincipaleFour(char c){
        this.nbPlayer = 2;
        this.teams = new Team[this.nbPlayer];
        this.teams[0] = new Team(0, 0, 0, 0);
        this.teams[1] = new Team(1, 0, 0, 0);
        this.ter = new TerrainPuissance("test.txt", this.teams);
        
        

        consoleMode = ('c' == c);

        if(consoleMode){
            sc = new Scanner(System.in);
        }

        actualPlayer = (Math.random() < 0.5)? 1 : 0;
        isWin = false;
    }

    public boolean isWin(){
        return isWin;
    }

    public void nextPlay(){
        if(consoleMode){
            nextPlayConsole();
        }
    }

    public void nextPlayConsole(){
        actualPlayer = (actualPlayer+1)%this.nbPlayer;
        System.out.println(ter);
        System.out.println("C'est au joueur " + (actualPlayer+1) + " de jouer.\nOu veux-tu jouer ?[1, 7]");
        int column = ask_column();
        while(!ter.addConditionalDiscs(column-1, teams[actualPlayer]))
        {
            System.out.println("Colonne invalide!\nOu veux-tu jouer ?[1, 7]");
            column = ask_column();
        }
        isWin = ter.addDiscs(column-1, teams[actualPlayer]);
        ter.save("test.txt");
    }


    private int ask_column()
    {
        try{
            int column = sc.nextInt();
            return column;
        }catch (InputMismatchException e){sc.nextLine(); return -1;}
        
    }

    public void close(){
        if(consoleMode){
            System.out.println(ter);
            System.out.println("Victoire du joueur " + (actualPlayer + 1) + "!!!");
        }
        sc.close();
    }
}
