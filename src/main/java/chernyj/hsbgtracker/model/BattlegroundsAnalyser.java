package chernyj.hsbgtracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import chernyj.hsbgtracker.entity.Game;
import chernyj.hsbgtracker.entity.Hero;
import chernyj.hsbgtracker.entity.User;
import chernyj.hsbgtracker.service.GameService;
import chernyj.hsbgtracker.service.HeroService;
import chernyj.hsbgtracker.service.UserService;
import chernyj.hsbgtracker.swing.ResultsController;
import chernyj.hsbgtracker.utils.DateConverter;
import chernyj.hsbgtracker.utils.observers.LogFileObserver;

public class BattlegroundsAnalyser implements LogFileObserver {

	private ArrayList<Player> playersList = new ArrayList<>();

	private ResultsController resultsController;

	private Date timeStarted;
	private Date timeFinished;

	public void setResultsController(ResultsController resultsController) {
		this.resultsController = resultsController;
	}

	private void showResults() {
		Player mainPlayer = getMainPlayer();

		System.out.println(mainPlayer == null);
		System.out.println("GAME FINISHED at: " + timeFinished + " . YOR FINAL PLACE IS: " + mainPlayer.getPlace() + " YOUR HERO IS: "
				+ mainPlayer.getHero());

		// resultsController.addNewResult(mainPlayer.getHero().getHsId(),
		// mainPlayer.getPlace());

		saveResult();
		saveOtherDeadPlayersResultsToDB();
		resultsController.showResult(mainPlayer);
	}
	
	private void saveResult() {
		Player mainPlayer = getMainPlayer();
		Hero mainPlayerHero = mainPlayer.getHero();
		int mainPlayerPlace = mainPlayer.getPlace();
		
		GameService gameService = new GameService();
		
		Game game = null;
		
		if(game == null) {
			saveUserResultsToDB(mainPlayerHero, convertToDBUser(mainPlayer), mainPlayerPlace, timeStarted, timeFinished);
		}
	}
	
	
	private void saveUserResultsToDB(Hero hero, User user, int place, Date dateStarted, Date dateFinished) {
		GameService gameService = new GameService();
		Game game = new Game(user, hero, place, dateStarted, dateFinished);
		if(!gameService.exists(game))
			gameService.save(game);
	}
	
	private void saveOtherDeadPlayersResultsToDB() {
		UserService service = new UserService();
		User otherPlayer = service.getByNameAndBTag("Other Player", 0);
		
		int mainPlayerPlace = getMainPlayer().getPlace();
		
		for (Player player : playersList) {
			if((player.getPlace() < mainPlayerPlace && mainPlayerPlace != 2) || player.isMainPlayer())
				continue;
			saveUserResultsToDB(player.getHero(), otherPlayer, player.getPlace(), timeStarted, timeFinished);
		}
	}
	
	private User convertToDBUser(Player player) {
		String name = player.getName();
		int bTag = Integer.parseInt(player.getbTag());
		
		if(bTag == 0)
			name = "Other Player";

		UserService service = new UserService();
		service.getByNameAndBTag(name, bTag);

		if (name.isEmpty() && bTag == 0) 
			return null;
		else { 
			User user = service.getByNameAndBTag(name, bTag);
			if(user == null) {
				user = new User(name, bTag);
			}
			return user;
		}
	}
	


	private void setMainPlayer(Map<String, String> data) {
		Player player = new Player();
		player.setName(data.get("playerName"));
		player.setbTag(data.get("playerBTag"));
		player.setId(Integer.parseInt(data.get("playerId")));
		player.setMainPlayer(true);

		HeroService service = new HeroService();
		Hero hero = service.getByHsId(data.get("playerHeroId"));
		player.setHero(hero);

		playersList.add(player);
	}

	private Player getMainPlayer() {
		for (Player player : playersList) {
			if (player.isMainPlayer())
				return player;
		}

		return null;
	}

	private void setPlayerIdsAndHeroes(Map<String, String> data) {
		Player player = new Player();

		player.setId(Integer.parseInt(data.get("playerId")));

		HeroService service = new HeroService();
		Hero hero = service.getByHsId(data.get("playerHeroId"));
		player.setHero(hero);
		
		//System.out.println(data.get("playerHeroId"));
		//System.out.println(player);

		playersList.add(player);
	}

	private void updatePlayerPlace(Map<String, String> data) {
		String heroId = data.get("playerHeroId");
		Player player = getPlayerByHeroId(heroId);
		if (player != null)
			player.setPlace(Integer.parseInt(data.get("playerPlace")));
	}

	private Player getPlayerByHeroId(String heroId) {
		for (Player player : playersList)
			if (player.getHero().getHsId().equals(heroId))
				return player;
		return null;
	}

	private void setGameStartedTime(Map<String, String> data) {
		String dateStr = data.get("startedTime");
		this.timeStarted = DateConverter.convert(dateStr);
	}
	
	private void setGameFinishedTime(Map<String, String> data) {
		String dateStr = data.get("finishedTime");
		this.timeFinished = DateConverter.convert(dateStr);
	}

	@Override
	public void update(Map<String, String> data) {
//		for(Entry<String,String> dat: data.entrySet())
//			System.out.println(dat.getKey() + " " + dat.getValue());
//		System.out.println();

		if (data.get("infoType").equals("gameStarted"))
			setGameStartedTime(data);

		if (data.get("infoType").equals("mainPlayer"))
			setMainPlayer(data);

		if (data.get("infoType").equals("playerHero"))
			setPlayerIdsAndHeroes(data);

		if (data.get("infoType").equals("playerPlace"))
			updatePlayerPlace(data);

		if (data.get("infoType").equals("gameFinished"))
			finishGame(data);

	}

	private void finishGame(Map<String, String> data) {
		setGameFinishedTime(data);
		showResults();
		playersList = new ArrayList<>();
	}

}
