package chernyj.hsbgtracker.swing;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import chernyj.hsbgtracker.model.Player;

public class ResultsController {

	public ResultsFrame dialog;

	public ResultsController(ResultsFrame dialog) {
		this.dialog = dialog;
	}

	public void showResult(Player mainPlayer) {
		dialog.update(mainPlayer.getHero().getHsId(), mainPlayer.getPlace());
		dialog.setExtendedState(JFrame.NORMAL);

	}

}
