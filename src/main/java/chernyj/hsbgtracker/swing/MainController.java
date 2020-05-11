package chernyj.hsbgtracker.swing;


public class MainController {
	private Tray tray;

	public MainController(Tray tray) {
		this.tray = tray;
		
		tray.showTray();
		
	}

}
