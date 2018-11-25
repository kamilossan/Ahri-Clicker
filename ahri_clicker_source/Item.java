package ahri_clicker;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

//items to buy
public class Item implements ActionListener {
	int id, cost;
	JTextArea price = new JTextArea();;
	protected boolean bought = false;
	protected JButton button;

	public Item(int id, int cost) {
		this.id = id;
		this.cost = cost;
		switch (id) {
		case 0:
			button = new JButton(null, new ImageIcon(getClass().getResource("Liandry.png")));
			button.setToolTipText("Gain 1.5% of your current Spirit Power every second.");

			break;
		case 1:
			button = new JButton(null, new ImageIcon(getClass().getResource("Bork.png")));
			button.setToolTipText(
					"Nobody can judge the Fox Goddess. Charm gains additional 1% of your current Spirit Power.");

			break;
		case 2:
			button = new JButton(null, new ImageIcon(getClass().getResource("Deathcap.png")));
			button.setToolTipText(
					"Charm additionally gains 30% of other skills' cumulative per second production of Spirit Power.");

			break;
		}
		button.setActionCommand("bought");
		button.setEnabled(false);
		button.addActionListener(this);
		price.setText("Price: " + cost);
		price.setEditable(false);
		button.setPreferredSize(new Dimension(64, 64));
	}

	private void sound_play() throws Exception {

		Clip clip = AudioSystem.getClip();
		AudioInputStream e = AudioSystem.getAudioInputStream(Skill.class.getResource("Purchase.wav"));
		clip.open(e);
		clip.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "bought") {
			if (Main_frame.charmed >= cost&&this.bought!=true) {
				Main_frame.charmed -= cost;
				this.bought = true;
			}
			try {
				sound_play();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
