package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tile extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int width=10;
	private static int height=10;
	private Color color;
	private boolean is_alive;
	private int x;
	private int y;
	public Tile(int x , int y) {
		this.color=Color.WHITE;
		this.x=x;
		this.y=y;
		this.is_alive=false;
	}
	
	public static int get_width() {
		return Tile.width;
	}
	public static int get_height() {
		return Tile.height;
	}
	public int get_x() {
		return this.x;
	}
	public int get_y() {
		return this.y;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(this.x, this.y, Tile.width, Tile.height);
		g.setColor(this.color);
		g.fillRect(this.x+1, this.y+1, width-1, height-1);
	}
	public Dimension getPreferredSize() {
	    return new Dimension(Tile.width, Tile.height);
	}
	public Boolean is_alive() {
		return this.is_alive;
	}
	public void alive() {
		
		this.color=Color.BLACK;
		this.is_alive=true;
		this.repaint();
	}
	public void dead() {
		this.color=Color.WHITE;
		this.is_alive=false;
		this.repaint();
	}
	public String toString() {
		return new String(this.x+" : "+this.y);
	}
	
}
