package ahri_clicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.JButton;

//basically buttons with few additional properties
public class Skill implements ActionListener {
	private ImageIcon image;
	protected long cost, profit;
	protected int count = 0;
	protected JButton button;
	protected boolean charm = false;

	protected Skill(String image, int cost, int profit) {
		this.image = new ImageIcon(getClass().getResource(image));
		this.cost = cost;
		this.profit = profit;
		button = new JButton(null, this.image);
		button.setToolTipText(
				"Additional " + profit + " souls stolen by the charm of Ahri. Currently gaining 0 per second.");
		button.addActionListener(this);
		button.setActionCommand("click");

	}

	private void sound_play(String path) throws Exception {

		Clip clip = AudioSystem.getClip();
		AudioInputStream e = AudioSystem.getAudioInputStream(Skill.class.getResource(path));
		clip.open(e);
		clip.start();

	}

	public void actionPerformed(ActionEvent e) {

		if (charm) {
	
				try {
					sound_play("charm.wav");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				long bonus = 0;
				if(Main_frame.blade.bought){
					bonus = (long) Main_frame.charmed/100;
					Main_frame.charmed+=bonus;					
				}
				if(Main_frame.cap.bought){
					bonus = (long) Main_frame.influx/100*30;
					Main_frame.charmed+=bonus;
				}
			Main_frame.charmed++;
			Main_frame.score.setText("Soul power: \n" + Main_frame.charmed);
		} else
		// ikr, screw optimalisation. Everything will be of the same type.
		if (Main_frame.charmed >= cost) {
			try {
				sound_play("lvl.wav");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			count++;
			Main_frame.charmed = Main_frame.charmed - cost;
			button.setToolTipText("Additional " + profit + " souls stolen by the charm of Ahri. Currently gaining "
					+ profit * count + " per second.");
			this.cost = (long) (this.cost * 1.5);
		}
	}

}
