package GUI;

import java.awt.event.MouseEvent;


public class MouseListener implements java.awt.event.MouseListener{
	private Grid grid;
	
	public MouseListener(Grid grid) {
		// TODO Auto-generated constructor stub
		this.grid=grid;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x =e.getX()/Tile.get_width();
		int y =e.getY()/Tile.get_height();
		this.grid.set_up_alive_cell(x, y);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
