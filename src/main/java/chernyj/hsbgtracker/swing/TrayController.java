package chernyj.hsbgtracker.swing;

public class TrayController {
	
	private Tray tray;
	
	public TrayController(Tray tray) {
		this.tray = tray;
		
		setListeners();
		
		tray.showTray();
	}
	
	private void setListeners() {
		tray.getMiStatistics().addActionListener(l->doOpenStatistics());
		tray.getMiSettings().addActionListener(l->doOpenSettings());
		tray.getMiExit().addActionListener(l->doExit());
	}

	private void doExit() {
		System.exit(0);
	}

	private void doOpenSettings() {
		new SettingsController(new SettingsDialog(500, 200, "Натройки приложения"));
		
	}

	private void doOpenStatistics() {
		tray.displayMessage("STATISTICS");
	}

}
