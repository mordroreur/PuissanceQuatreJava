package main;

import java.util.ArrayList;
import java.util.Scanner;

import puissanceFour.Team;
import puissanceFour.TerrainPuissance;

public class Main {
    public static void main(String[] args) {
        TerrainPuissance ter = new TerrainPuissance();
        Team[] t = {new Team(0, 0, 0, 0), new Team(1, 0, 0, 0)};

        Scanner sc = new Scanner(System.in);
        boolean win = false;
        while(!win)
        {
            for (Team team : t) {
                System.out.println("C'est au joueur " + (team.getId()+1) + " de jouer.\nOu veux-tu jouer ?[1, 7]");
                int column = sc.nextInt();
                while(!ter.addConditionalDiscs(column-1, team))
                {
                    System.out.println("Colonne invalide!\nOu veux-tu jouer ?[1, 7]");
                    column = sc.nextInt();
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
