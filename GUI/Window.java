
package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JButton;

// 1 une cellule survie que si elle est entourée par 2 ou 3 voisine
// 2 une case vide enntouré par 3 voisines devient vivante

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	private JFrame MyWindow;
	private final int WIDTH;
	private final int HEIGHT;
	private Grid grid;
	private JPanel commands;
	public Window() {
		this.WIDTH=1700;
		this.HEIGHT=1000;
		
		this.MyWindow=new JFrame("my window");
		this.MyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.MyWindow.setSize(this.WIDTH, this.HEIGHT); 
		this.MyWindow.setLocationRelativeTo(null);
		this.MyWindow.setResizable(true);
		this.MyWindow.setVisible(true);
		this.MyWindow.setLayout(new BorderLayout());
        this.MyWindow.setLocationRelativeTo(null); // Center the frame on the screen
        init_commands();
		init_grid();
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
}