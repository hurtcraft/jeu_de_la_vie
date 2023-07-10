package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;




// une cellule survie au tour suivant si elle est entouré par 2 ou 3 voisine
// si une case vide est entouré par exactement 3 voisine, elle devient vivante
public class Grid extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<Tile>> grid;
	private int col;
	private int raw;
	private int width;
	private int height;
	public Grid(int w_width, int w_height){
		this.col=(int)(w_width*0.9/Tile.get_width());
		this.raw=(int)(w_height/Tile.get_height());
		this.grid=new ArrayList<>();
		this.init_grid();
		this.width=this.col*Tile.get_width();
		this.height=this.raw*Tile.get_height();
		test();
	}
	public int get_width() {
		return this.width;
	}
	public int get_height() {
		return this.height;
	}
	public Dimension getPreferredSize() {
		return new Dimension(this.width+1,this.height+1);
	}
	private void init_grid() {
		ArrayList<Tile> tmp=new ArrayList<>();
		for(int i = 0; i<this.raw;i++) {
			for(int j = 0 ; j<this.col;j++) {
				tmp.add(new Tile(j*Tile.get_height(),i*Tile.get_width()));
			}
			this.grid.add(tmp);
			tmp=new ArrayList<>();
		}
	}
	@Override
	public void paintComponent(Graphics g ) {
		super.paintComponent(g);
		Tile tile;
		for (int i = 0 ; i<raw ;i++) {
			for( int j = 0; j < col; j++) {
				tile=this.grid.get(i).get(j);
				tile.paintComponent(g);;
			}
		}
		
	}
	
	
	public ArrayList<Tile> get_neighboors(Tile tile){
		ArrayList<Tile> neighboors= new ArrayList<>();
		
		Tile right_Tile= get_right_neighboor(tile);
		Tile left_Tile=get_left_neighboor(tile);
		Tile bot_Tile=get_bot_neighboor(tile);
		Tile top_Tile=get_top_neighboor(tile);
		
		Tile top_left_Tile=get_top_left_neighboor(tile);
		Tile top_right_Tile=get_top_right_neighboor(tile);
		Tile bot_left_Tile=get_bot_left_neighboor(tile);
		Tile bot_right_Tile=get_bot_right_neighboor(tile);
		// un peu bourrin je l'admet
	    if (right_Tile != null) neighboors.add(right_Tile);
	    if (left_Tile != null) neighboors.add(left_Tile);
	    if (bot_Tile != null) neighboors.add(bot_Tile);
	    if (top_Tile != null) neighboors.add(top_Tile);
	    if (top_left_Tile != null) neighboors.add(top_left_Tile);
	    if (top_right_Tile != null) neighboors.add(top_right_Tile);
	    if (bot_left_Tile != null) neighboors.add(bot_left_Tile);
	    if (bot_right_Tile != null) neighboors.add(bot_right_Tile);

		
		
		return neighboors;
	}
	
	
	public Tile get_right_neighboor(Tile tile) {
		int x=(int)(tile.get_x()/Tile.get_width());
		int y=(int)(tile.get_y()/Tile.get_height());
		
		try {
			return this.grid.get(y).get(x+1);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public Tile get_left_neighboor(Tile tile) {
		int x=(int)(tile.get_x()/Tile.get_width());
		int y=(int)(tile.get_y()/Tile.get_height());
		
		try {
			return this.grid.get(y).get(x-1);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public Tile get_top_neighboor(Tile tile) {
		int x=(int)(tile.get_x()/Tile.get_width());
		int y=(int)(tile.get_y()/Tile.get_height());
		
		try {
			return this.grid.get(y-1).get(x);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public Tile get_bot_neighboor(Tile tile) {
		int x=(int)(tile.get_x()/Tile.get_width());
		int y=(int)(tile.get_y()/Tile.get_height());
		
		try {
			return this.grid.get(y+1).get(x);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public Tile get_top_left_neighboor(Tile tile) {
		try {
			Tile t= this.get_left_neighboor(tile);		
			return this.get_top_neighboor(t);
		} catch (Exception e) {
			return null;
		}

	}
	
	public Tile get_top_right_neighboor(Tile tile) {
		try {
			Tile t= this.get_right_neighboor(tile);		
			return this.get_top_neighboor(t);
		} catch (Exception e) {
			return null;
		}

	}
	
	public Tile get_bot_right_neighboor(Tile tile) {
		try {
			Tile t= this.get_right_neighboor(tile);		
			return this.get_bot_neighboor(t);
		} catch (Exception e) {
			return null;
		}

	}
	public Tile get_bot_left_neighboor(Tile tile) {
		try {
			Tile t= this.get_left_neighboor(tile);		
			return this.get_bot_neighboor(t);
		} catch (Exception e) {
			return null;
		}

	}
	@Override
	public String toString() {
		
		return this.grid.toString();
	}
	public void test() {
		Tile tile = this.grid.get(10).get(10);
		Tile t =get_right_neighboor(tile);
		Tile t2=get_left_neighboor(tile);
		Tile t3=get_bot_neighboor(tile);
		Tile t4=get_top_neighboor(tile);
		
		Tile t5=get_top_left_neighboor(tile);
		Tile t6=get_top_right_neighboor(tile);
		Tile t7=get_bot_left_neighboor(tile);
		Tile t8=get_bot_right_neighboor(tile);
		tile.alive();
		t.alive();
		t2.alive();
		t3.alive();
		t4.alive();
		t5.alive();
		t6.alive();
		t7.alive();
		t8.alive();
		
		grid.get(0).get(col-1).alive();;
		System.out.println("aeikgapeiogahigia");
		
		
	}
	
}
