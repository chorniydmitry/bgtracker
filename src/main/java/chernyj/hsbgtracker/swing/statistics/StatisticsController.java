package chernyj.hsbgtracker.swing.statistics;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import chernyj.hsbgtracker.entity.Game;
import chernyj.hsbgtracker.entity.Result;
import chernyj.hsbgtracker.service.GameService;



public class StatisticsController {


	public StatisticsController() {

		GameService gameService = new GameService();

		List<Game> games = gameService.getAll();

		Map<String, Object> context = new HashMap<>();
		List<Object> statsList = new ArrayList<>();
		
		for (Game game : games) {
			
			GameStatistics gameStat = new GameStatistics();
			
			gameStat.setTimeStarted(game.getTimeStarted());
			gameStat.setTimeFinished(game.getTimeFinished());

			Set<Result> resultList = game.getResults();

			for (Result res : resultList) {
				if (res.getUser().getId() != 0) {
					gameStat.setScore(res.getPlace());
					gameStat.setHeroImageLink(StatisticsController.class.getResource("/images/heroes/" + res.getHero().getHsId() + ".jpg").getPath());
					gameStat.setPlayer(res.getUser().getName() + "#" + res.getUser().getbTag());
				}
			}
			statsList.add(gameStat);
		}
		
		context.put("context", statsList);

		Writer writer = new OutputStreamWriter(System.out);
		MustacheFactory mustacheFactory = new DefaultMustacheFactory();
		Mustache template = mustacheFactory.compile("statistics.mustache");
		try {
			template.execute(writer, context).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(writer.toString());

//		MustacheFactory mf = new DefaultMustacheFactory();
//		
//		Mustache m = mf.compile("statistics.mustache");
//
//		Set<Entry<String, String>> entrySet = resultSet.entrySet();
//		
//		StringWriter writer = new StringWriter();
//		try {
//			m.execute(writer, entrySet).flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String html = writer.toString();
//		
//		System.out.println(html);
	}

	private void placeToMustache() {
	}

	private void setListeners() {

	}
	
	public class GameStatistics {
		private Date timeStarted;
		private Date timeFinished;
		private String player;
		private int score;
		private String heroImageLink;
		
		public GameStatistics() {}
		
		public GameStatistics(Date timeStarted, Date timeFinished, String player, int score, String heroImageLink) {
			super();
			this.timeStarted = timeStarted;
			this.timeFinished = timeFinished;
			this.player = player;
			this.score = score;
			this.heroImageLink = heroImageLink;
		}

		public Date getTimeStarted() {
			return timeStarted;
		}

		public void setTimeStarted(Date timeStarted) {
			this.timeStarted = timeStarted;
		}

		public Date getTimeFinished() {
			return timeFinished;
		}

		public void setTimeFinished(Date timeFinished) {
			this.timeFinished = timeFinished;
		}

		public String getPlayer() {
			return player;
		}

		public void setPlayer(String player) {
			this.player = player;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public String getHeroImageLink() {
			return heroImageLink;
		}

		public void setHeroImageLink(String heroImageLink) {
			this.heroImageLink = heroImageLink;
		}
		
	}

}


