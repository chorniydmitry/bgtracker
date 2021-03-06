package chernyj.hsbgtracker.swing;

import java.util.ArrayList;
import java.util.List;

import chernyj.hsbgtracker.entity.User;
import chernyj.hsbgtracker.service.UserService;
import chernyj.hsbgtracker.utils.ApplicationConfiguration;

public class SettingsController {

	private SettingsDialog dialog;

	private List<User> userList = new ArrayList<>();

	public SettingsController(SettingsDialog dialog) {
		this.dialog = dialog;

		loadUsers();

		loadCheckBoxUsers();
		
		loadMMR();
		
		loadAppConfig();

		setListeners();
	}
	
	private void loadAppConfig() {
		String pathToHS = ApplicationConfiguration.getItem("powerlog.filepath");
		
		boolean showingPreviousResultsDialog = Boolean.parseBoolean(ApplicationConfiguration.getItem("show.prevresultdialog"));
		boolean showingUpdateMMRDialog = Boolean.parseBoolean(ApplicationConfiguration.getItem("show.updatemmrdialog"));
		boolean useGameResultHTML = Boolean.parseBoolean(ApplicationConfiguration.getItem("use.gameresultshtml"));
		
		dialog.getLblPathToHearthstone().setText(pathToHS);
		
		dialog.getCbShowResultPopup().setSelected(showingPreviousResultsDialog);
		dialog.getCbShowMMRUpdateDialog().setSelected(showingUpdateMMRDialog);
		dialog.getCbUpdateFileForStream().setSelected(useGameResultHTML);
		
		
	}
	private void loadUsers() {
		UserService userService = new UserService();

		userList = userService.getAll();
		userList.remove(0);

	}

	private void loadCheckBoxUsers() {
		userList.forEach(i -> dialog.getComboUsers().addItem(i.getName()));
	}
	
	private void loadMMR() {
		int index = dialog.getComboUsers().getSelectedIndex();
		
		dialog.getLblCurrentMMR().setText(String.valueOf(userList.get(index).getMmr()));
	}
	
	private void setListeners() {
		dialog.getComboUsers().addActionListener(l->doComboUsersValueChanged());
		dialog.getBtnChangeMMR().addActionListener(l->doChangeMMR());
		dialog.getBtnChangePathToHearthstone().addActionListener(l->doChangePathToHearthstone());
	}

	private void doChangePathToHearthstone() {
	}

	private void doChangeMMR() {
	}

	private void doComboUsersValueChanged() {
		loadMMR();
	}
	
	

}
