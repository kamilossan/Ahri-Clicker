package ahri_clicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Main_frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832305425147746386L;

	public static long charmed = 0;
	protected static JFrame frame = new JFrame("Ahri Clicker v.1.0");
	// create buttonz
	private static Skill q = new Skill("AhriQ.png", 40, 1);
	private static Skill w = new Skill("AhriW.png", 110, 3);
	private static Skill r = new Skill("AhriR.png", 400, 9);
	private static Skill s = new Skill("AhriE.png", 0, 1);
	private static int refresh = 0;
	static Font skill = new Font("Comic Sans MS", Font.BOLD, 16);
	protected static long influx=0;


	public static JTextArea score = new JTextArea("Soul power:\n" + charmed);
	protected static Item torment = new Item(0, 100000);
	protected static Item blade = new Item(1, 1000000);
	protected static Item cap = new Item(2, 10000000);
	// skill level counters
	protected static JTextArea qscore = new JTextArea("Lvl: 0\nCost of upgrade: " + q.cost);
	protected static JTextArea wscore = new JTextArea("Lvl: 0\nCost of upgrade: " + w.cost);
	protected static JTextArea rscore = new JTextArea("Lvl: 0\nCost of upgrade: " + r.cost);

	// Yes, I like making 100+ line mains.
	public static void main(String[] args) throws Exception{
		 Clip bg = AudioSystem.getClip();
	        AudioInputStream src = AudioSystem.getAudioInputStream(Main_frame.class.getResource("SRloop.wav"));
	        bg.open(src);
	        bg.loop(Clip.LOOP_CONTINUOUSLY);
		score.setForeground(Color.MAGENTA);
		qscore.setEditable(false);
		wscore.setEditable(false);
		rscore.setEditable(false);
		score.setEditable(false);
		qscore.setFont(skill);
		wscore.setFont(skill);
		rscore.setFont(skill);
		score.setOpaque(false);
		score.setFont(new Font("Goudy Stout", Font.ITALIC, 30));

		
		frame.setBounds(0, 0, 1500, 900);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pane = new Background();

		JPanel qpanel = new JPanel();

		JPanel wpanel = new JPanel();

		JPanel epanel = new JPanel();

		JPanel rpanel = new JPanel();

		pane.setLayout(new GridBagLayout());
		pane.setPreferredSize(new Dimension(1500, 900));
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.weightx = 0.5;
		c.weighty = 0.01;
		c.insets = new Insets(70, 30, 70, 30);
		c.gridy = 0;

		// q button adjust

		q.button.setPreferredSize(new Dimension(64, 64));

		qscore.setBackground(Color.GRAY);
		qscore.setOpaque(true);
		qpanel.add(q.button);
		qpanel.add(qscore);
		qpanel.setOpaque(false);
		pane.add(qpanel, c);

		c.gridy = 1;
		c.weighty = 0.2;

		// w button adjust

		w.button.setPreferredSize(new Dimension(64, 64));

		wscore.setBackground(Color.GRAY);
		wscore.setOpaque(true);
		wpanel.setOpaque(false);
		wpanel.add(w.button);
		wpanel.add(wscore);
		pane.add(wpanel, c);

		c.weighty = 50;
		c.gridy = 2;

		// r button adjust

		r.button.setPreferredSize(new Dimension(64, 64));

		rscore.setBackground(Color.GRAY);
		rscore.setOpaque(true);
		rpanel.add(r.button);
		rpanel.add(rscore);
		rpanel.setOpaque(false);
		pane.add(rpanel, c);

		c.gridx = 1;
		c.weightx = 100;
		c.gridy = 2;
		c.insets = new Insets(0, 300, 150, 0);
		c.anchor = GridBagConstraints.WEST;
		// charm button adjust

		s.button.setPreferredSize(new Dimension(80, 80));
		s.button.setEnabled(true);
		s.charm = true;
		s.button.setToolTipText("Click to steal one soul with your charm!");
		epanel.add(s.button);
		pane.add(epanel, c);
		epanel.setOpaque(false);
		c.insets = null;
		c.gridx = 2;
		c.weightx = 0;
		pane.add(score);
		// items
		c = new GridBagConstraints();
		c.gridy = 3;
		c.weighty = 10;
		c.insets = new Insets(0, 10, 20, 10);
		skill = new Font("Comic Sans MS", Font.BOLD, 23);
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		JPanel panel1 = new JPanel();
		panel1.add(torment.button);
		panel1.add(torment.price);
		torment.price.setFont(skill);
		torment.price.setBorder(raisedetched);
		torment.price.setBackground(Color.LIGHT_GRAY);
		JPanel panel2 = new JPanel();
		panel2.add(blade.button);
		panel2.add(blade.price);
		blade.price.setFont(skill);
		blade.price.setBorder(raisedetched);
		blade.price.setBackground(Color.LIGHT_GRAY);
		JPanel panel3 = new JPanel();
		panel3.add(cap.button);
		panel3.add(cap.price);
		cap.price.setFont(skill);
		cap.price.setBorder(raisedetched);
		cap.price.setBackground(Color.LIGHT_GRAY);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);

		pane.add(panel1, c);

		pane.add(panel2, c);

		pane.add(panel3, c);

		frame.add(pane);
		frame.setVisible(true);
		Timer timer = new Timer();
		timer.schedule(new Refresh(), 0, 250);

	}
//determine whether to enable purchase for items, return boolean for the sake of liandry
	protected static boolean handle_item(Item x) {
		if (x.bought == true) {
			x.button.setEnabled(false);
			x.price.setText("Owned");
			return true;

		} else if (x.cost <= charmed) {
			x.button.setEnabled(true);
			return false;

		} else {
			x.button.setEnabled(false);
			return false;

		}
	}



	public static void update() {
		// Multi-threading for cavemen, dividing one-second frame into 4
		// subframes. Just to make clicking more responsive and score increase
		// more smooth.
		switch (refresh) {
		case 0: // refreshes all, increase score from Q stacks

			if (q.cost <= Main_frame.charmed) {
				q.button.setEnabled(true);
			} else {
				q.button.setEnabled(false);
			}
			if (w.cost <= Main_frame.charmed) {
				w.button.setEnabled(true);
			} else {
				w.button.setEnabled(false);
			}
			if (r.cost <= Main_frame.charmed) {
				r.button.setEnabled(true);
			} else {
				r.button.setEnabled(false);
			}
			Main_frame.charmed = Main_frame.charmed + ((q.profit) * q.count);
			score.setText("Soul power: \n" + Main_frame.charmed);
			qscore.setText("Lvl: " + q.count + "\nCost of upgrade: " + q.cost);
			wscore.setText("Lvl: " + w.count + "\nCost of upgrade: " + w.cost);
			rscore.setText("Lvl: " + r.count + "\nCost of upgrade: " + r.cost);
			refresh++;
			break;
		case 1:// refreshes all, score from w
			if (q.cost <= Main_frame.charmed) {
				q.button.setEnabled(true);
			} else {
				q.button.setEnabled(false);
			}
			if (w.cost <= Main_frame.charmed) {
				w.button.setEnabled(true);
			} else {
				w.button.setEnabled(false);
			}
			if (r.cost <= Main_frame.charmed) {
				r.button.setEnabled(true);
			} else {
				r.button.setEnabled(false);
			}
			Main_frame.charmed = Main_frame.charmed + ((w.profit) * w.count);
			score.setText("Soul power: \n" + Main_frame.charmed);
			qscore.setText("Lvl: " + q.count + "\nCost of upgrade: " + q.cost);
			wscore.setText("Lvl: " + w.count + "\nCost of upgrade: " + w.cost);
			rscore.setText("Lvl: " + r.count + "\nCost of upgrade: " + r.cost);
			refresh++;
			break;
		case 2:// refreshes all, score from r
			if (q.cost <= Main_frame.charmed) {
				q.button.setEnabled(true);
			} else {
				q.button.setEnabled(false);
			}
			if (w.cost <= Main_frame.charmed) {
				w.button.setEnabled(true);
			} else {
				w.button.setEnabled(false);
			}
			if (r.cost <= Main_frame.charmed) {
				r.button.setEnabled(true);
			} else {
				r.button.setEnabled(false);
			}
			Main_frame.charmed = Main_frame.charmed + ((r.profit) * r.count);
			score.setText("Soul power: \n" + Main_frame.charmed);
			qscore.setText("Lvl: " + q.count + "\nCost of upgrade: " + q.cost);
			wscore.setText("Lvl: " + w.count + "\nCost of upgrade: " + w.cost);
			rscore.setText("Lvl: " + r.count + "\nCost of upgrade: " + r.cost);
			refresh++;
			break;
		// refresh all, reload items+liandry passive
		case 3:
			if (q.cost <= Main_frame.charmed) {
				q.button.setEnabled(true);
			} else {
				q.button.setEnabled(false);
			}
			if (w.cost <= Main_frame.charmed) {
				w.button.setEnabled(true);
			} else {
				w.button.setEnabled(false);
			}
			if (r.cost <= Main_frame.charmed) {
				r.button.setEnabled(true);
			} else {
				r.button.setEnabled(false);
			}
			handle_item(cap);
			handle_item(blade);
			if(handle_item(torment)){
				long bonus = (long)(charmed/100*1.5);
				charmed = charmed+bonus;
			}
			influx = q.profit*q.count+w.profit*w.count+r.profit*r.count;
			
			
			refresh = 0;
			break;
		}
		
		//layout tends to mess up while resizing, so adjusts automatically within "acceptable" sizes
		if(frame.getWidth()<1400|frame.getHeight()<850){
		frame.setSize(1400, 850);
		}
		if(frame.getWidth()>1505|frame.getHeight()>900){
			frame.setSize(1505, 900);
			}
		
	}

}
//paint background image
class Background extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6607108043156903306L;
	Image bg = Toolkit.getDefaultToolkit().createImage(Main_frame.class.getResource("Ahri.png"));

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this);

	}
}