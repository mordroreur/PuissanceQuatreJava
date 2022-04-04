package graphiqueFour;

import javax.swing.JPanel;

import main.PrincipaleFour;
import puissanceFour.CellulePuissance;
import puissanceFour.SideEnum;
import puissanceFour.TerrainPuissance;
import puissanceFour.Console;

import java.awt.MouseInfo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class PanelFour extends JPanel{

    protected PrincipaleFour the_game;
    protected int frameCount;
    private int stape;
    private TerrainPuissance ter;
    private Console con;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch(stape){
            case 42 : drawTer(g);break;
            default : g.setColor(Color.black); g.fillRect(0, 0, this.getWidth(), this.getHeight());break;
        }



        if(true) {                //affichage de Frames et ticks a l'ecran
            g.setFont(new Font("Verdana", 0,this.getHeight()/30));
            g.setColor(Color.red);
            g.drawString(Integer.toString(frameCount), 0, this.getHeight()/30);
            //g.setColor(Color.yellow);
            //g.drawString(Integer.toString(Trame.TicksFin), this.getWidth()-this.getHeight()/20, this.getHeight()/30);
        }

    }

    private void drawTer(Graphics g){
        g.setColor(Color.black); 
        g.fillRect(0, this.getHeight()/(ter.getHeight()+1), this.getWidth(), this.getHeight());

        CellulePuissance cell = ter.getLeftLine();
        cell = con.goToDirection(SideEnum.UP, cell);
        cell = con.goToDirection(SideEnum.LEFT, cell);

        int whereX = 0;
        int whereY = 0;
        CellulePuissance cellCopy = cell;
        while(cellCopy != null) {
            while(cell != null){
                if(cell.getTeam() != null) {
                    g.setColor(cell.getTeam().getColor());
                    g.fillOval(this.getWidth()/ter.getWidth() * whereX, this.getHeight()/(ter.getHeight()+1) * (whereY+1), this.getWidth()/ter.getWidth(), this.getHeight()/(ter.getHeight()+1));
                }
                whereX++;
                cell = cell.getVoisin(SideEnum.RIGHT);
            }
            whereY++;
            whereX = 0;
            cellCopy = cellCopy.getVoisin(SideEnum.DOWN);
            cell = cellCopy;
        }
        int column = (int)(((float)(MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x) )/((float)(this.getWidth())/(float)(ter.getWidth())));
        g.setColor(the_game.getPlayingTeam().getColor());
        //g.fillOval(MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x - (this.getWidth()/(ter.getWidth()*2)), 0, this.getWidth()/ter.getWidth(), this.getHeight()/(ter.getHeight()+1));
        g.fillOval(this.getWidth()/ter.getWidth() * column, 0, this.getWidth()/ter.getWidth(), this.getHeight()/(ter.getHeight()+1));
    }


    public void setFrame(int nb){
        this.frameCount = nb;
    }
    public void setStape(int nb){
        stape = nb;
    }
    public void setTer(TerrainPuissance ter){
        this.ter = ter;
        this.con = new Console();
    }

    public void setAll(PrincipaleFour principaleFour) {
        the_game = principaleFour;
    }
    
}
