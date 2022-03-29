package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import graphiqueFour.FrameFour;
import puissanceFour.Team;
import puissanceFour.TerrainPuissance;



public class PrincipaleFour {

    public static int FRAME_CAP = 60;

    private FrameFour frame;
    private long timer = System.currentTimeMillis();
    private long lastRenderTime = System.nanoTime();
    private double renderTime = 1000000000.0/FRAME_CAP;
    private int framesnb = 0;

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
        this.teams[0] = new Team(0, 255, 0, 0);
        this.teams[1] = new Team(1, 0, 255, 0);

        consoleMode = ('c' == c);
        actualPlayer = (Math.random() < 0.5)? 1 : 0;
        this.ter = new TerrainPuissance();

        if(consoleMode){
            sc = new Scanner(System.in);
            System.out.println("Voulez vous recharger la dérnière parti ? (O/N)");
            String answer = sc.nextLine();
            if(answer.equals("O") || answer.equals("o") || answer.equals("0")){
                this.ter = new TerrainPuissance("save.txt", this.teams);
                actualPlayer = this.ter.turnOfWho();
            }
        }else{
            frame = new FrameFour(this);
            frame.setTer(this.ter);
        }

        
        isWin = false;
    }

    public boolean isWin(){
        return isWin;
    }

    public void forceEnd(){
        isWin = true;
        actualPlayer = -1;
    }

    public void nextPlay(){
        if(consoleMode){
            nextPlayConsole();
        }else{
            
            if(System.nanoTime() - lastRenderTime > renderTime) {
                // drawing a new frame
                frame.DrawTer(ter);
                
                framesnb++;
                lastRenderTime += renderTime;
            }else{
                try {
                    Thread.sleep((long) ((System.nanoTime() - lastRenderTime)/100000.0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            //every seconde
            if(System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    //System.out.println(framesnb );
                    frame.setFrameNumber(framesnb);
                    framesnb = 0;
            } 
            
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
        ter.save("save.txt");
    }


    public void PlaceGraphics(int column){
        //System.out.println(column);
        actualPlayer = (actualPlayer+1)%this.nbPlayer;
        if(ter.addConditionalDiscs(column-1, teams[actualPlayer])){
            isWin = ter.addDiscs(column-1, teams[actualPlayer]);
            ter.save("test.txt");
        }
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
            sc.close();
        }else{
            frame.close();
        }
        
    }
}
