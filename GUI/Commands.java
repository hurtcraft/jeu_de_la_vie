package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.event.PrintJobAttributeEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Commands extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_next;
	private JButton btn_go;
	private JCheckBox chk_auto;
	private JComboBox<String> cb_box_vitesse;
	private JTextArea mode_emploi;
	private JLabel lb_generation;
	private int current_generation;
	private JButton btn_population_aleatoire;
	private JButton btn_clear;
	private JButton btn_stop;
	private Grid grid;

	public Commands(Grid grid){
		this.grid=grid;
		this.current_generation=this.grid.get_generation();
		init_btn_next();
		init_check_auto();
		init_cb_box_vitesse();
		init_btn_go();
		init_btn_stop();
		init_lb_mode_emploi();
		init_lb_generation();
		init_btn_aleatoire();
		init_btn_clear();
		generation_listener();
		
	}
	public Dimension getPreferredSize() {
	    return new Dimension(300, 500);
	}
	private void init_btn_next() {
		this.btn_next=new JButton("next");
		this.btn_next.addActionListener(this);
		this.add(this.btn_next);
	}
	private void init_btn_go() {
		this.btn_go=new JButton("go");
		this.btn_go.addActionListener(this);
		this.btn_go.setVisible(false);
		this.add(btn_go);
	}
 	private void init_check_auto() {
		this.chk_auto=new JCheckBox("mode auto");
		this.chk_auto.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cb_box_vitesse.setVisible(true);
					btn_go.setVisible(true);
					btn_stop.setVisible(true);
					btn_next.setVisible(false);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					cb_box_vitesse.setVisible(false);
					btn_go.setVisible(false);
					btn_stop.setVisible(false);
					btn_next.setVisible(true);
					
				}
			}
		});
		this.add(chk_auto);
		
	}
	private void init_cb_box_vitesse() {
		this.cb_box_vitesse=new JComboBox<>();
		this.cb_box_vitesse.setVisible(false);
		String placeholder = "selection vitesse";
		this.cb_box_vitesse.addItem(placeholder);
		for(int i = 1 ; i<10;i++) {
			this.cb_box_vitesse.addItem(String.valueOf(i));
		}
		this.add(this.cb_box_vitesse);
		
	}
	private void init_lb_mode_emploi() {
		String txt = "[next] --> pour une simulation pas à pas \n[mode auto] --> pour une simulation auto";

		this.mode_emploi = new JTextArea(txt);
		mode_emploi.setPreferredSize(new Dimension(250,50));
		mode_emploi.setEditable(false);
		mode_emploi.setLineWrap(true);
		mode_emploi.setWrapStyleWord(true);
		this.add(mode_emploi);
	}
	private void init_lb_generation() {
		this.lb_generation=new JLabel();
		this.add(this.lb_generation);
		
	}
	private void generation_listener(){
		TimerTask listen_generation= new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(grid.get_generation()!=current_generation) {
					current_generation=grid.get_generation();
					set_generation(current_generation);
				}
			}
		};
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(listen_generation, 0, 10);
		
	}
	
	public void set_generation(int generation) {
		this.lb_generation.setText("generation : "+String.valueOf(generation));
	}
	
	private void init_btn_aleatoire() {
		this.btn_population_aleatoire=new JButton();
		this.btn_population_aleatoire.setText("generer des cellules aléatoires");
		this.btn_population_aleatoire.addActionListener(this);
		this.add(this.btn_population_aleatoire);
	}
	private void init_btn_clear() {
		this.btn_clear=new JButton();
		this.btn_clear.setText("clear");
		this.btn_clear.addActionListener(this);
		this.add(this.btn_clear);
	}
	private void init_btn_stop() {
		// TODO Auto-generated method stub
		
		this.btn_stop=new JButton();
		this.btn_stop.setText("stop");
		this.btn_stop.setVisible(false);
		this.btn_stop.addActionListener(this);
		this.add(this.btn_stop);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_next) {
			grid.next();
			this.grid.mode_auto(false, 1);
		}
		
		if(e.getSource()==btn_go) {
			try {
				
				int vitesse=Integer.parseInt(this.cb_box_vitesse.getSelectedItem().toString());
				this.grid.mode_auto(true, vitesse);
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2);
			}
		}
		if(e.getSource()==btn_stop) {
			this.grid.mode_auto(false, 1);
		}
		
		if(e.getSource()==this.btn_population_aleatoire) {
			this.grid.random_tiles();
		}
		if(e.getSource()==this.btn_clear) {
			this.grid.clear();
		}

	}
}
