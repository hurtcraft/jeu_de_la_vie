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
	private Grid grid;

	public Commands(Grid grid){
		this.grid=grid;
		init_btn_next();
		init_check_auto();
		init_cb_box_vitesse();
		init_btn_go();
		init_lb_mode_emploi();
		
		
		
		//this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
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
					btn_next.setVisible(false);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					cb_box_vitesse.setVisible(false);
					btn_go.setVisible(false);
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

		JTextArea mode_emploi = new JTextArea(txt);
		mode_emploi.setPreferredSize(new Dimension(250,50));
		mode_emploi.setEditable(false);
		mode_emploi.setLineWrap(true);
		mode_emploi.setWrapStyleWord(true);
		this.add(mode_emploi);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_next) {
			System.out.println("btn next");
			grid.next();
		}
		if(e.getSource()==btn_go) {
			System.out.println("btn prev");
		}

	}
}
