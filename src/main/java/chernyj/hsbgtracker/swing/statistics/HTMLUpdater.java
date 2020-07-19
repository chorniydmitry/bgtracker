package chernyj.hsbgtracker.swing.statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import chernyj.hsbgtracker.swing.ResultsFrame;
import chernyj.hsbgtracker.utils.ApplicationConfiguration;
import chernyj.hsbgtracker.utils.C;

public class HTMLUpdater {

	private List<GamePlayed> gamesPlayedList = new ArrayList<>();
	private int currentMmr;
	private int startMmr;
	
	public HTMLUpdater() {
	}

	public void addResult(String hero, int place, int currentMmr) {
		String image = ResultsFrame.class.getResource("/images/heroes/" + hero + ".jpg").getPath();

		if(currentMmr == 0)
			currentMmr = startMmr;
		
		gamesPlayedList.add(new GamePlayed(image, place, currentMmr, startMmr));
		this.currentMmr = currentMmr;
		
		System.out.println(image + " " + place + " " + currentMmr + " " + startMmr);

		updateHtml();
	}

	public void updateHtml() {
		Map<String, Object> context = new HashMap<>();

		context.put("games", gamesPlayedList);
		context.put("startMmr", startMmr);
		context.put("currentMmr", currentMmr);
		
		StringWriter writer = new StringWriter();
		MustacheFactory mustacheFactory = new DefaultMustacheFactory();
		Mustache template = (Boolean.parseBoolean(ApplicationConfiguration.getItem("show.updatemmrdialog"))) ? mustacheFactory.compile("templates/game_with_mmr.mustache") : mustacheFactory.compile("templates/game.mustache");
		try {
			template.execute(writer, context).flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writeToHtml(writer);

	}

	private void writeToHtml(StringWriter writer) {
		File fileToSave = new File(C.GAMERESULTS_FILEPATH);
		if(!fileToSave.exists())
			try {
				fileToSave.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		PrintWriter prw;
		try {
			prw = new PrintWriter(C.GAMERESULTS_FILEPATH);
			prw.println(writer.toString());
			prw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void clear() {
		writeToHtml(new StringWriter());
	}

	public List<GamePlayed> getGamesPlayedList() {
		return gamesPlayedList;
	}

	public void setGamesPlayedList(List<GamePlayed> gamesPlayedList) {
		this.gamesPlayedList = gamesPlayedList;
	}

	class GamePlayed {
		private String imageLink;
		private int place;
		private int startMmr;
		private int currentMmr;

		public GamePlayed(String imageLink, int place) {
			this.imageLink = imageLink;
			this.place = place;
		}
		
		public GamePlayed(String imageLink, int place, int startMmr, int currentMmr) {
			super();
			this.imageLink = imageLink;
			this.place = place;
			this.startMmr = startMmr;
			this.currentMmr = currentMmr;
		}

		public String getImageLink() {
			return imageLink;
		}

		public void setImageLink(String imageLink) {
			this.imageLink = imageLink;
		}

		public int getPlace() {
			return place;
		}

		public void setPlace(int place) {
			this.place = place;
		}

		public int getStartMmr() {
			return startMmr;
		}

		public void setStartMmr(int startMmr) {
			this.startMmr = startMmr;
		}

		public int getCurrentMmr() {
			return currentMmr;
		}

		public void setCurrentMmr(int currentMmr) {
			this.currentMmr = currentMmr;
		}
		
	}

	public void setCurrentMmr(int mmr) {
		this.currentMmr = mmr;
		updateHtml();
	}

	public void setStartMmr(int startMmr) {
		this.startMmr = startMmr;
		updateHtml();
		
	}
}
