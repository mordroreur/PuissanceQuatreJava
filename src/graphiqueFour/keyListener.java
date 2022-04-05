package graphiqueFour;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.PrincipaleFour;

public class keyListener implements KeyListener{

   PrincipaleFour game;

   

    @Override
    public void keyPressed(KeyEvent key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent key) {
        //System.out.println(key.getKeyChar());
        if(key.getKeyCode() == 27){
            game.forceEnd();
        }else{

            /*
            switch(key.getKeyChar()){
                case('&') : game.PlaceGraphics(1); break;
                case('1') : game.PlaceGraphics(1); break;
                case('é') : game.PlaceGraphics(2); break;
                case('2') : game.PlaceGraphics(2); break; 
                case('"') : game.PlaceGraphics(3); break;
                case('3') : game.PlaceGraphics(3); break; 
                case('\''): game.PlaceGraphics(4); break;
                case('4') : game.PlaceGraphics(4); break; 
                case('(') : game.PlaceGraphics(5); break;
                case('5') : game.PlaceGraphics(5); break; 
                case('-') : game.PlaceGraphics(6); break;
                case('6') : game.PlaceGraphics(6); break; 
                case('è') : game.PlaceGraphics(7); break;
                case('7') : game.PlaceGraphics(7); break; 
            }
            */
        }
        
    }

    @Override
    public void keyTyped(KeyEvent key) {
        // TODO Auto-generated method stub
        
    }

    public void setGame(PrincipaleFour g){
        this.game = g;
    }
}
