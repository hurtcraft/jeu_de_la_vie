
package GUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;

// 1 une cellule survie que si elle est entourée par 2 ou 3 voisine
// 2 une case vide enntouré par 3 voisines devient vivante

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	private JFrame MyWindow;
	private final int WIDTH;
	private final int HEIGHT;
	private JPanel grid;
	public Window() {
		this.WIDTH=1400;
		this.HEIGHT=1000;
		
		this.MyWindow=new JFrame("my window");
		this.MyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.MyWindow.setSize(this.WIDTH, this.HEIGHT); 
		this.MyWindow.setLocationRelativeTo(null);
		this.MyWindow.setResizable(true);
		this.MyWindow.setVisible(true);
		
		init_grid();
	}

    public void init_grid() {
        this.grid = new Grid(WIDTH, HEIGHT);
        this.grid.setBackground(Color.blue);
        this.grid.setLayout(new GridLayout(10, 10));
        this.MyWindow.add(this.grid);
        this.MyWindow.revalidate();
    }
}