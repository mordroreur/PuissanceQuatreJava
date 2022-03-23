package graphiqueFour;

import java.awt.Dimension;
import javax.swing.JFrame;

public class FrameFour extends JFrame{
    
    public FrameFour(){
        
        // creating frame
		this.setTitle("F****** window");
		this.setVisible(true);
		this.setResizable(true);
        this.setPreferredSize(new Dimension(1300,780));
        this.setDefaultCloseOperation(FrameFour.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);


		//this.setContentPane(panel);
		// paint a temporary(splash) screen
		//stape = 42;
		//panel.repaint();
		// add the listener to the frame
		//this.addKeyListener(keyListener);
		//this.addMouseListener(mouseListener);
		//this.addMouseWheelListener(wheelListerne);
    }


    public void close(){
        this.dispose();
    }
}
