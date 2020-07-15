package chernyj.hsbgtracker.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class SettingsDialog extends JDialog {
	private static final long serialVersionUID = 6816002482388563385L;
	
	private JLabel lblAvailibleUsers = new JLabel("Найдены пользователи:");
	private JLabel lblPathToHearthstoneCaption = new JLabel("Путь к папке Hearthstone:");
	private JLabel lblPathToHearthstone = new JLabel();
	private JLabel lblCurrentMMRCaption = new JLabel("Текущий MMR:");
	private JLabel lblCurrentMMR = new JLabel();
	
	private JCheckBox cbShowResultPopup = new JCheckBox("Показывать окно результатов прошлых игр");
	private JCheckBox cbUpdateFileForStream = new JCheckBox("Обновлять файл game_results.html(для отображения в OBS)");
	private JCheckBox cbShowMMRUpdateDialog = new JCheckBox("Показывать окно обновления MMR после каждой игры");
	
	private JButton btnChangeMMR = new JButton("Обновить");
	private JButton btnChangePathToHearthstone = new JButton("Сменить");

	private JComboBox<String> comboUsers = new JComboBox<>();
	
	public SettingsDialog(int width, int height, String title) {
		this.setSize(new Dimension(width, height));
		this.setTitle(title);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		layoutDialog();
		this.setVisible(true);
	}

	private void layoutDialog() {
		this.setLayout(new GridBagLayout());
		
		// 1st row
		this.add(lblAvailibleUsers, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		this.add(comboUsers, new GridBagConstraints(1, 0, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));

		// 2nd row
		
		this.add(lblPathToHearthstoneCaption, new GridBagConstraints(0, 1, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));

		// 3rd row
		
		this.add(lblPathToHearthstone, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		this.add(btnChangePathToHearthstone, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		// 4th row
		
		this.add(lblCurrentMMRCaption, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));

		this.add(lblCurrentMMR, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		this.add(btnChangeMMR, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		// 5th row
		
		this.add(cbShowResultPopup, new GridBagConstraints(0, 4, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		// 6th row
		
		this.add(cbUpdateFileForStream, new GridBagConstraints(0, 5, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
		// 7th row
		
		this.add(cbShowMMRUpdateDialog, new GridBagConstraints(0, 6, 3, 1, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 1, 1));
		
	}

	public JLabel getLblPathToHearthstone() {
		return lblPathToHearthstone;
	}

	public void setLblPathToHearthstone(JLabel lblPathToHearthstone) {
		this.lblPathToHearthstone = lblPathToHearthstone;
	}

	public JLabel getLblCurrentMMR() {
		return lblCurrentMMR;
	}

	public void setLblCurrentMMR(JLabel lblCurrentMMR) {
		this.lblCurrentMMR = lblCurrentMMR;
	}

	public JCheckBox getCbShowResultPopup() {
		return cbShowResultPopup;
	}

	public void setCbShowResultPopup(JCheckBox cbShowResultPopup) {
		this.cbShowResultPopup = cbShowResultPopup;
	}

	public JCheckBox getCbUpdateFileForStream() {
		return cbUpdateFileForStream;
	}

	public void setCbUpdateFileForStream(JCheckBox cbUpdateFileForStream) {
		this.cbUpdateFileForStream = cbUpdateFileForStream;
	}

	public JCheckBox getCbShowMMRUpdateDialog() {
		return cbShowMMRUpdateDialog;
	}

	public void setCbShowMMRUpdateDialog(JCheckBox cbShowMMRUpdateDialog) {
		this.cbShowMMRUpdateDialog = cbShowMMRUpdateDialog;
	}

	public JButton getBtnChangeMMR() {
		return btnChangeMMR;
	}

	public void setBtnChangeMMR(JButton btnChangeMMR) {
		this.btnChangeMMR = btnChangeMMR;
	}

	public JButton getBtnChangePathToHearthstone() {
		return btnChangePathToHearthstone;
	}

	public void setBtnChangePathToHearthstone(JButton btnChangePathToHearthstone) {
		this.btnChangePathToHearthstone = btnChangePathToHearthstone;
	}

	public JComboBox<String> getComboUsers() {
		return comboUsers;
	}

	public void setComboUsers(JComboBox<String> comboUsers) {
		this.comboUsers = comboUsers;
	}
	
	

}
