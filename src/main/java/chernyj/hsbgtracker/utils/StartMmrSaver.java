package chernyj.hsbgtracker.utils;

import chernyj.hsbgtracker.utils.observers.SetMmrObserver;

public class StartMmrSaver implements SetMmrObserver {

	@Override
	public void update(int mmr) {
		ApplicationConfiguration.saveItem("lastplayer.startmmr", String.valueOf(mmr));
	}

}
