package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import puissanceFour.Team;
import puissanceFour.TerrainPuissance;

public class Main {
    public static void main(String[] args) {
        TerrainPuissance ter = new TerrainPuissance();
        Team[] t = {new Team(0, 0, 0, 0), new Team(1, 0, 0, 0)};

        Scanner sc = new Scanner(System.in);
        boolean win = false;
        System.out.println(ter);
        while(!win)
        {
            for (Team team : t) {
                System.out.println("C'est au joueur " + (team.getId()+1) + " de jouer.\nOu veux-tu jouer ?[1, 7]");
                int column;
                try{
                    column = sc.nextInt();
                }catch (InputMismatchException e){column = -1;sc.nextLine();}
                while(!ter.addConditionalDiscs(column-1, team))
                {
                    System.out.println("Colonne invalide!\nOu veux-tu jouer ?[1, 7]");
                    try{
                        column = sc.nextInt();
                    }catch (InputMismatchException e){column = -1;sc.nextLine();}
                }
                win = ter.addDiscs(column-1, team);
                System.out.println(ter);
                if(win)
                {
                    System.out.println("Le joueur " + (team.getId()+1) + " a gagne!");
                    break;
                }
            }
        }
    }
}
