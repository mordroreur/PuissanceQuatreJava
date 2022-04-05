package main;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import graphiqueFour.FrameFour;
import puissanceFour.MinMax;
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
    private int turn;

    private int actualPlayer;
    private boolean draw;

    private final String SAVE_FILE = "save.txt";

    public PrincipaleFour(char c){
        this.nbPlayer = 2;
        this.teams = new Team[this.nbPlayer];
        this.teams[0] = new Team(0, 200, 0, 0, false);
        this.teams[1] = new Team(1, 255, 215, 0, false);

        consoleMode = ('c' == c);
        actualPlayer = (Math.random() < 0.5)? 1 : 0;
        //actualPlayer = 0;
        this.ter = new TerrainPuissance();

        if(consoleMode){
            sc = new Scanner(System.in);
        }else{
            frame = new FrameFour(this);
            frame.setTer(this.ter);
            frame.setAll(this);
            frame.setnewState(42);
        }

        turn = 0;
        isWin = false;
        draw = false;
    }

    public void askLoad(){
        File file = new File("save" + System.getProperty("file.separator") + SAVE_FILE);
        if(file.exists())
        {
            System.out.println("Voulez vous recharger la dérnière parti ? (O/N)");
            String answer = sc.nextLine();
            if(answer.equals("O") || answer.equals("o") || answer.equals("0")){
                this.ter = new TerrainPuissance(SAVE_FILE, this.teams);
                actualPlayer = this.ter.turnOfWho();
            }
        }
    }

    public boolean isWin(){
        if(isWin){
            File file = new File("save" + System.getProperty("file.separator") + SAVE_FILE);
            file.delete();

            if(!consoleMode){
                consoleMode = true;
                frame.close();
            }

            System.out.println(ter);
            if(!draw)    
                System.out.println("Victoire du joueur " + (actualPlayer + 1) + "!!!");
            else
                System.out.println("Egalite !");
        }


        return isWin;
    }

    public void forceEnd(){
        isWin = true;
        draw = true;
        actualPlayer = -1;
    }

    public void nextPlay(){
        
        if(consoleMode){
            nextPlayConsole();
        }else{
            
            if(System.nanoTime() - lastRenderTime > renderTime) {
                // drawing a new frame
                int tmp = (actualPlayer+1)%this.nbPlayer;
                frame.DrawTer(ter);
                
                
                if(teams[tmp].isACpu()) {
                    actualPlayer = (actualPlayer+1)%this.nbPlayer;
                    int column = MinMax.whereToPlay(teams[1], teams[0], ter, turn)+1;
                    isWin = ter.addDiscs(column-1, teams[actualPlayer]);
                }
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
        turn++;
        if(!isWin && ter.isFull()) {draw = true; isWin = true;}
        if(!isWin && !draw) ter.save(SAVE_FILE);
        if(isWin && consoleMode) sc.nextLine();
    }

    public void nextPlayConsole(){
        actualPlayer = (actualPlayer+1)%this.nbPlayer;
        
        System.out.println(ter);
        if(!teams[actualPlayer].isACpu())System.out.println("C'est au joueur " + (actualPlayer+1) + " de jouer.\nOu veux-tu jouer ?[1, 7]");
        else System.out.println("L'ordinateur joue :");
        int column;
        
        if(!teams[actualPlayer].isACpu()) {column = ask_column();}
        else {column = MinMax.whereToPlay(teams[actualPlayer], teams[(actualPlayer+1)%this.nbPlayer], ter, turn)+1;};

        while(!ter.addConditionalDiscs(column-1))
        {
            System.out.println("Colonne invalide!\nOu veux-tu jouer ?[1, 7]");
            column = ask_column();
        }
        isWin = ter.addDiscs(column-1, teams[actualPlayer]);
    }


    public void PlaceGraphics(int column){
        //System.out.println(column);
        actualPlayer = (actualPlayer+1)%this.nbPlayer;
        if(ter.addConditionalDiscs(column-1)){
            isWin = ter.addDiscs(column-1, teams[actualPlayer]);
            //ter.save("test.txt");
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
        sc.close();
        
    }

    public Team getPlayingTeam() {
        return teams[(actualPlayer+1)%this.nbPlayer];
    }

    public TerrainPuissance getTer(){
        return ter;
    }

    public void askChangeMode() {
        System.out.println("Voulez vous ouvrir la version graphique ? (O/N)");
        String answer = sc.nextLine();
        if(answer.equals("O") || answer.equals("o") || answer.equals("0")){
            frame = new FrameFour(this);
            frame.setTer(this.ter);
            frame.setAll(this);
            frame.setnewState(42);
            consoleMode = !consoleMode;
        }
    }

    public boolean askNewParti() {
        System.out.println("Voulez vous jouer une nouvelle parti ? (O/N)");
        String answer = sc.nextLine();
        return (answer.equals("O") || answer.equals("o") || answer.equals("0"));
    }

    public void askSetComputer() {
        System.out.println("Voulez vous jouer contre une IA ? (O/N)");
        String answer = sc.nextLine();
        if(answer.equals("O") || answer.equals("o") || answer.equals("0")){
            this.teams[1] = new Team(1, this.teams[1].getColor(), true);
        }
        else{
            this.teams[1] = new Team(1, this.teams[1].getColor(), false);
        }
    }

    public void resetTerrain() {

        actualPlayer = (Math.random() < 0.5)? 1 : 0;
        this.ter = new TerrainPuissance();
        turn = 0;
        isWin = false;
        draw = false;

    }
}
