package chernyj.hsbgtracker.swing;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class GetMmrValue {

	public static int showInputDialog(Component parent, Icon icon, int place, int lastMmrValue) {
		JOptionPane optionPane = new JOptionPane("Введите значение ММР:", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.DEFAULT_OPTION, icon, null, lastMmrValue);
		optionPane.setWantsInput(true);
		
		String title = place == 0 ? "Введите ММР!" : "Вы заняли " + place + " место!";

		JDialog dialog = optionPane.createDialog(parent, title);
		dialog.setLocation(10, 200);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		
		int returnValue = 0;
		try {
			returnValue = Integer.parseInt((String)optionPane.getInputValue());
		} catch(NumberFormatException e) { 
			e.printStackTrace();
		}
		
		return returnValue;
	}
}
