package chernyj.hsbgtracker.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import chernyj.hsbgtracker.utils.observers.LogFileObserver;

public class LogFileUtils implements LogFileObserver {

	private void createFile(String filePath) {
		File f = new File(filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update(Map<String, String> data) {
		if (data.containsKey("fileNotExists")) {
			createFile(data.get("fileNotExists"));
		}

	}

}
