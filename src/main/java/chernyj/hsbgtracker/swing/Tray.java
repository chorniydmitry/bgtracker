package chernyj.hsbgtracker.swing;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.LinkedList;
import java.util.List;

import antlr.debug.MessageAdapter;
import chernyj.hsbgtracker.utils.C;

/**
 * @author Chernyj Dmitry
 *
 */
public class Tray {

	private static final String MI_STATISTICS = "Статистика";
	private static final String MI_SETTINGS = "Настройки";
	private static final String MI_EXIT = "Завершить работу";

	private MenuItem miStatistics = new MenuItem(MI_STATISTICS);
	private MenuItem miSettings = new MenuItem(MI_SETTINGS);
	private MenuItem miExit = new MenuItem(MI_EXIT);

	private PopupMenu puTrayMenu = new PopupMenu();

	private TrayIcon trayIcon;

	private List<MenuItem> menuItems = new LinkedList<MenuItem>();
	
	public void showTray() {
		fillMenuItems();
		initTrayIcon();
		initTray();
	}

	private void fillMenuItems() {
		menuItems.add(miStatistics);
		menuItems.add(miSettings);
		menuItems.add(miExit);
	}
	
	public void displayMessage(String text) {
		trayIcon.displayMessage("8 МЕСТО!", null, MessageType.NONE);
	}

	private void initTrayIcon() {

		trayIcon = new TrayIcon(C.APP_ICON, null, puTrayMenu);
		trayIcon.setImageAutoSize(true);
	}

	private void initTray() {
		if (!SystemTray.isSupported())
			return;

		for (MenuItem mi : menuItems) {
			puTrayMenu.add(mi);
		}

		SystemTray tray = SystemTray.getSystemTray();
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void setInfoMessageInTray(String message) {
		trayIcon.displayMessage(C.APPLICATION_NAME, message, TrayIcon.MessageType.INFO);
	}

	public void setErrorMessageInTray(String message) {
		trayIcon.displayMessage(C.APPLICATION_NAME, message, TrayIcon.MessageType.ERROR);
	}

	public MenuItem getMiStatistics() {
		return miStatistics;
	}

	public void setMiStatistics(MenuItem miStatistics) {
		this.miStatistics = miStatistics;
	}

	public MenuItem getMiSettings() {
		return miSettings;
	}

	public void setMiSettings(MenuItem miSettings) {
		this.miSettings = miSettings;
	}

	public MenuItem getMiExit() {
		return miExit;
	}

	public void setMiExit(MenuItem miExit) {
		this.miExit = miExit;
	}
}
