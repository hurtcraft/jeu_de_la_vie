
package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Iterator;

import javax.swing.JButton;

// 1 une cellule survie que si elle est entourée par 2 ou 3 voisine
// 2 une case vide enntouré par 3 voisines devient vivante

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument.LeafElement;

public class Window implements Runnable{

	private JFrame MyWindow;
	private final int WIDTH;
	private final int HEIGHT;
	private Grid grid;
	private JPanel commands;
	private Thread repaintThread;
	public Window() {
		this.WIDTH=1700;
		this.HEIGHT=1000;
		
		this.MyWindow=new JFrame("my window");
		this.MyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.MyWindow.setSize(this.WIDTH, this.HEIGHT); 
		this.MyWindow.setLocationRelativeTo(null);
		this.MyWindow.setResizable(false);
		this.MyWindow.setVisible(true);
		this.MyWindow.setLayout(new BorderLayout());
		init_grid();
		init_commands();
		start_paint_loop();
        

		this.MyWindow.pack();
		
	}

    public void init_grid() {
        this.grid = new Grid(WIDTH, HEIGHT);
        this.grid.setBackground(Color.blue);
        this.MyWindow.add(this.grid,BorderLayout.CENTER);
        this.MyWindow.revalidate();
    }
    
    public void init_commands(){
    	this.commands=new Commands(this.grid);
    	this.MyWindow.add(this.commands,BorderLayout.EAST);
    	this.MyWindow.revalidate();
    }
    private void start_paint_loop() {
    	repaintThread=new Thread(this);
    	repaintThread.start();
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double time_per_frame=1000000000/120;
		long last_frame=System.nanoTime();
		long now=System.nanoTime();
		while (true) {
			now = System.nanoTime();
			if(now-last_frame>=time_per_frame) {
				last_frame=now;
				this.grid.repaint();
			}
			
		}
		
	}
}