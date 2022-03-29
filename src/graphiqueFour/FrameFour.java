package graphiqueFour;

import java.awt.Dimension;
import javax.swing.JFrame;

import main.PrincipaleFour;
import puissanceFour.TerrainPuissance;

public class FrameFour extends JFrame{

    private PanelFour panel = new PanelFour();
    
    public FrameFour(PrincipaleFour game){
        
        // creating frame
		this.setTitle("Connect four");
		this.setVisible(true);
		this.setResizable(true);
        this.setPreferredSize(new Dimension(1300,780));
        //this.setDefaultCloseOperation(FrameFour.EXIT_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                game.forceEnd();
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);

        panel.setFrame(0);
		
		// paint a temporary(splash) screen
		//stape = 42;
		//panel.repaint();
		// add the listener to the frame
		//this.addKeyListener(keyListener);
		//this.addMouseListener(mouseListener);
		//this.addMouseWheelListener(wheelListerne);
    }

    public void DrawTer(TerrainPuissance ter){
        panel.revalidate();
        panel.repaint();
    }

    public void setFrameNumber(int frames){
        panel.setFrame(frames);
    }

    public void close(){
        this.dispose();
    }
}
