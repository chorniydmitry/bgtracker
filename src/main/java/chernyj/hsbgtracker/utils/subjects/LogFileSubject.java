package chernyj.hsbgtracker.utils.subjects;

import java.util.Map;

import chernyj.hsbgtracker.utils.observers.LogFileObserver;

public interface LogFileSubject {
	
	public void register(LogFileObserver observer);
	public void remove(LogFileObserver observer);
	public void notifyObservers(Map<String, String> data);

}
