package chernyj.hsbgtracker.swing.statistics;

import java.awt.Dimension;

import javax.swing.JDialog;

public class StatisticsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel pnlSide = new JPanel();
	private JPanel pnlMiddle = new JPanel();
	
	private JButton btn1StPlaces = new JButton("1 места");
	private JButton btn1To4Places = new JButton("В четверке");
	private JButton btnHeroStatistics = new JButton("По персонажам")
	
	
	public StatisticsDialog(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	
	
	
	

}
