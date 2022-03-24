package graphiqueFour;

import java.awt.Dimension;
import javax.swing.JFrame;

import puissanceFour.TerrainPuissance;

public class FrameFour extends JFrame{

    private int framesnb;
    
    public FrameFour(){
        
        // creating frame
		this.setTitle("F****** window");
		this.setVisible(true);
		this.setResizable(true);
        this.setPreferredSize(new Dimension(1300,780));
        this.setDefaultCloseOperation(FrameFour.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);


        this.framesnb = 0;

		//this.setContentPane(panel);
		// paint a temporary(splash) screen
		//stape = 42;
		//panel.repaint();
		// add the listener to the frame
		//this.addKeyListener(keyListener);
		//this.addMouseListener(mouseListener);
		//this.addMouseWheelListener(wheelListerne);
    }

    public void DrawTer(TerrainPuissance ter){
        //System.out.println("pas fait!!!!");
    }

    public void setFrameNumber(int frames){
        this.framesnb = frames;
    }

    public void close(){
        System.out.println(framesnb); // inutile juste pour que se soit plus un warning unused
        this.dispose();
    }
}
