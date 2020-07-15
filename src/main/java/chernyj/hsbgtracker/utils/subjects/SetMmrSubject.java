package chernyj.hsbgtracker.utils.subjects;

import chernyj.hsbgtracker.utils.observers.SetMmrObserver;

public interface SetMmrSubject {
	public void register(SetMmrObserver observer);
	public void remove(SetMmrObserver observer);
	public void notifyObservers(int mmr);

}
