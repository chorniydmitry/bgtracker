package chernyj.hsbgtracker.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chernyj.hsbgtracker.utils.C;


public class ResultsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private ArrayList<JLabel> lblsImagesList = new ArrayList<JLabel>();
	private ArrayList<JLabel> lblsPlacesList = new ArrayList<JLabel>();
	private JPanel mainPanel = new JPanel();

	public ResultsFrame(int width, int height, String title) {

		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		this.setLocation(10, 0);
		this.setIconImage(C.APP_ICON);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		layoutMainPanel();
		updateMainPanel();
		
		this.add(mainPanel);
		
		this.setVisible(true);
	}
	
	private void updateMainPanel() {
		mainPanel.setBackground(Color.BLACK);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	private void layoutMainPanel() {
		if (lblsImagesList.size() == 0)
			return;
		
		mainPanel.removeAll();

		mainPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
	
		for (int i = 0; i < lblsImagesList.size(); i++) {
			JPanel pnlPlace = new JPanel();
			pnlPlace.setLayout(new GridBagLayout());
			
			pnlPlace.add(lblsImagesList.get(i), new GridBagConstraints(i, 0, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			pnlPlace.add(lblsPlacesList.get(i), new GridBagConstraints(i, 1, 1, 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			pnlPlace.setBackground(Color.BLACK);
			
			mainPanel.add(pnlPlace);
		}
		
		mainPanel.setVisible(true);
		
		updateMainPanel();
		
		revalidate();
		repaint();
	}
	
	public void update(String imageName, int score) {
		ImageIcon icon = new ImageIcon(
				new ImageIcon(ResultsFrame.class.getResource("/images/heroes/" + imageName + ".jpg")).getImage()
						.getScaledInstance(C.HERO_IMAGE_WIDTH, C.HERO_IMAGE_HEIGHT, Image.SCALE_DEFAULT));

		JLabel imgLabel = new JLabel();
		imgLabel.setIcon(icon);

		JLabel lblScore = new JLabel();
		lblScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblScore.setText(String.valueOf(score));

		lblScore.setOpaque(false);
		lblScore.setForeground(Color.WHITE);
		
		lblsImagesList.add(imgLabel);
		lblsPlacesList.add(lblScore);
		
		layoutMainPanel();
	}

	public ArrayList<JLabel> getLblsImagesList() {
		return lblsImagesList;
	}


	public ArrayList<JLabel> getLblsPlacesList() {
		return lblsPlacesList;
	}

}
