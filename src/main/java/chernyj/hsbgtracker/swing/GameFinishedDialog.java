package chernyj.hsbgtracker.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameFinishedDialog extends JDialog {
	private static final long serialVersionUID = 9098297168701798141L;
	
	private JLabel lblCurrentMMR = new JLabel("Текущий ММР:");
	private JTextField tfMMR = new JTextField(20);
	private JButton btnOk = new JButton("OK");
	
	public GameFinishedDialog(int width, int height, String title) {
		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	
		layoutDialog();
		this.setVisible(true);
	}
	
	private void layoutDialog() {
		this.add(lblCurrentMMR, BorderLayout.WEST);
		this.add(tfMMR, BorderLayout.CENTER);
		this.add(btnOk, BorderLayout.EAST);
	}
	
	
	

}
