package chernyj.hsbgtracker.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SetMmrDialog extends JDialog {
	private static final long serialVersionUID = 9098297168701798141L;
	
	private JLabel lblCurrentMMR = new JLabel("Текущий ММР:");
	private JTextField tfMMR = new JTextField(20);
	private JButton btnOk = new JButton("OK");
	
	public SetMmrDialog(int width, int height, String title) {
		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
		layoutDialog();
		this.setVisible(true);
	}
	
	public SetMmrDialog(int width, int height, String title, int mmr) {
		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		tfMMR.setText(String.valueOf(mmr));
		layoutDialog();
		this.setVisible(true);
	}
	
	private void layoutDialog() {
		this.add(lblCurrentMMR, BorderLayout.WEST);
		this.add(tfMMR, BorderLayout.CENTER);
		this.add(btnOk, BorderLayout.EAST);
	}

	public JTextField getTfMMR() {
		return tfMMR;
	}

	public void setTfMMR(JTextField tfMMR) {
		this.tfMMR = tfMMR;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}
	
}
