package graphiqueFour;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class PanelFour extends JPanel{

    protected int frameCount;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);




        if(true) {                //affichage de Frames et ticks a l'ecran
            g.setFont(new Font("Verdana", 0,this.getHeight()/30));
            g.setColor(Color.red);
            g.drawString(Integer.toString(frameCount), 0, this.getHeight()/30);
            //g.setColor(Color.yellow);
            //g.drawString(Integer.toString(Trame.TicksFin), this.getWidth()-this.getHeight()/20, this.getHeight()/30);
        }

    }

    public void setFrame(int nb){
        this.frameCount = nb;
    }
    
}
