package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;




// une cellule survie au tour suivant si elle est entouré par 2 ou 3 voisine sinn elle meurt
// si une case vide est entouré par exactement 3 voisine, elle devient vivante
public class Grid extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<Tile>> grid;
	private ArrayList<Tile> alives_Tiles;
	//private Map<Tile, ArrayList<Tile>> alives_cells_map;
	private int col;
	private int raw;
	private int width;
	private int height;
	private MouseListener my_mouse_listener;
	
	private Timer timer;
	private TimerTask tmTask;

	public Grid(int w_width, int w_height){

		this.alives_Tiles=new ArrayList<>();
		this.my_mouse_listener=new MouseListener(this);
		this.col=(int)(w_width*0.9/Tile.get_width());
		this.raw=(int)(w_height/Tile.get_height());
		this.grid=new ArrayList<>();
		this.init_grid();
		this.width=this.col*Tile.get_width();
		this.height=this.raw*Tile.get_height();
		this.addMouseListener(my_mouse_listener);
		this.tmTask=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				next();
			}
		};
		
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
				tile.paintComponent(g);
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
			Tile neighboor=this.grid.get(y).get(x+1);
			return neighboor;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public Tile get_left_neighboor(Tile tile) {
		int x=(int)(tile.get_x()/Tile.get_width());
		int y=(int)(tile.get_y()/Tile.get_height());
		
		try {
			Tile neighboor=this.grid.get(y).get(x-1);
			return neighboor;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public Tile get_top_neighboor(Tile tile) {
		int x=(int)(tile.get_x()/Tile.get_width());
		int y=(int)(tile.get_y()/Tile.get_height());
		
		try {
			Tile neighboor=this.grid.get(y-1).get(x);
			
			return neighboor;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public Tile get_bot_neighboor(Tile tile) {

		int x=(int)(tile.get_x()/Tile.get_width());
		int y=(int)(tile.get_y()/Tile.get_height());
		
		try {
			Tile neighboor=this.grid.get(y+1).get(x);
			return neighboor;
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
	public void set_up_alive_cell(int y ,int x) {
		
		Tile tile=this.grid.get(x).get(y);
		
		if(tile.is_alive()) {
			tile.dead();
			this.alives_Tiles.remove(tile);
		}
		else {
			tile.alive();
			this.alives_Tiles.add(tile);
		}
		
		//repaint();

	}
	public void next(){
		ArrayList<Tile>neighboors;
		ArrayList<Tile>alive_neighboors;
		ArrayList<Tile>dead_neighboors;
		ArrayList<Tile> next_alives=new ArrayList<>();
		ArrayList<Tile> next_deaths=new ArrayList<>();
		for (Tile tile : this.alives_Tiles){
			
			neighboors=this.get_neighboors(tile);
			alive_neighboors=this.get_alives_neighboors(neighboors);
			dead_neighboors=this.get_deads_neighboors(neighboors);
			dead_or_alive(tile, alive_neighboors,next_alives,next_deaths);
			
			for (Tile neighboor:dead_neighboors) {
				neighboors=this.get_neighboors(neighboor);
				alive_neighboors=this.get_alives_neighboors(neighboors);
				dead_or_alive(neighboor, alive_neighboors, next_alives, next_deaths);
			}
		}
		
		this.repaint_deads_tiles(next_deaths);
		this.repaint_alives_tiles(next_alives);
		//repaint();
		
	}
	private void repaint_deads_tiles(ArrayList<Tile> next_deaths) {
		for(Tile t:next_deaths) {
			t.dead();
			this.alives_Tiles.remove(t);
		}
	}
	private void repaint_alives_tiles(ArrayList<Tile> next_alives) {
		for(Tile t:next_alives) {
			t.alive();
			if(!alives_Tiles.contains(t)) {
				this.alives_Tiles.add(t);
			}
			
		}
	}
	
	private void dead_or_alive(Tile tile , ArrayList<Tile> neighboors,ArrayList<Tile> next_alive,ArrayList<Tile> next_deaths){
		// cas cellule vivante
		if(tile.is_alive()) {
			if(neighboors.size()<2 || neighboors.size()>3){
				//this.alives_Tiles.remove(tile);
				next_deaths.add(tile);
			}
			return;
		}
		
		//cas cellule morte
		if(neighboors.size()==3) {
			//this.alives_Tiles.add(tile);
			next_alive.add(tile);
		}
	}
	private ArrayList<Tile> get_alives_neighboors(ArrayList<Tile>neighboors) {
		ArrayList<Tile> alives_neighboors= new ArrayList<>();
		for(Tile neighboor: neighboors) {
			if(neighboor.is_alive()) {

				alives_neighboors.add(neighboor);
			}
		}
		return alives_neighboors;
	}
	
	private ArrayList<Tile> get_deads_neighboors(ArrayList<Tile>neighboors) {
		ArrayList<Tile> deads_neighboors= new ArrayList<>();
		for(Tile neighboor: neighboors) {
			if(!neighboor.is_alive()) {

				deads_neighboors.add(neighboor);
			}
		}
		return deads_neighboors;
	}
	
	public void test() {
		Tile tile = this.grid.get(10).get(10);
		tile.alive();
		this.grid.get(0).get(0).alive();
		//repaint();
	}
	public void mode_auto(Boolean actif,int coeff_vitesse) {
		long delay=1000/coeff_vitesse;

		
		if(actif) 
		{
			timer.scheduleAtFixedRate(tmTask, 0, delay);
		}
		else {
			timer.cancel();
			timer.purge();
			timer=new Timer();

		}

	}
	
}
