package graphiqueFour;
import java.awt.event.*; 

public class mouseListener implements MouseListener{

    private boolean isClickedLeft = false;


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println(e.getButton() + "   Okay");
        if(e.getButton() == 1){
            isClickedLeft = true;
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        
    }

    public boolean isLeftPressed(){
        return isClickedLeft;
    }
    public void setClickUsed(){
        isClickedLeft = false;
    }
}
