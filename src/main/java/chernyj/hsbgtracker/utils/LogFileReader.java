package chernyj.hsbgtracker.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import chernyj.hsbgtracker.utils.observers.LogFileObserver;
import chernyj.hsbgtracker.utils.subjects.LogFileSubject;

/**
 * @author Chernyj Dmitry
 *
 */
public class LogFileReader implements Runnable, LogFileSubject {

	// private static final int UPDATE_TIME = 100;

	private boolean debug = true;

	private boolean isRunning = true;
	private boolean isGameStarted = false;
	private boolean isGameIsBg = false;
	private boolean isGotMainPlayerInfo = false;

	private boolean isGotAllPlayersInfo = false;

	private File logFile = null;

	private ArrayList<LogFileObserver> observers = new ArrayList<LogFileObserver>();

	public enum InfoType {
		MAIN_PLAYER_INFO, PLAYER_PLACE, GAME_STATE, SPECTATING, PLAYER_HERO,
	}

	public LogFileReader(String myFile) {
		logFile = new File(myFile);
	}

	private void printLine(String message) {
		System.out.println(message);
	}

	private void checkGameStarted(String line) {
		if (line != null && line.contains("CREATE_GAME")) {
			Map<String, String> resultSet = new HashMap<>();

			resultSet.put("infoType", "gameStarted");
			resultSet.put("startedTime", getDate(line));

			notifyObservers(resultSet);

			isGameStarted = true;
		}
	}

	private String getDate(String line) {
		if (line != null)
			return line.split("D ")[1].split(" ")[0];

		return null;
	}

	private void checkGameBattleGrounds(String line) {
		if (line != null && line.contains("GameType=GT_BATTLEGROUNDS"))
			isGameIsBg = true;

	}

	private boolean updatedPlayersPlace(String message) {
		if (message != null && message.contains("tag=PLAYER_LEADERBOARD_PLACE value=") && message.contains("cardId=")
				&& message.contains("zone=")) {
			return true;
		}
		return false;
	}

	private Map<String, String> getPlayerPlace(String message) {
		String playerCardId = message.split("cardId=")[1].split(" ")[0];
		String playerPlace = message.split("tag=PLAYER_LEADERBOARD_PLACE value=")[1].trim();
		String playerZone = message.split("zone=")[1].split(" ")[0];

		Map<String, String> resultSet = new HashMap<>();
		resultSet.put("infoType", "playerPlace");
		resultSet.put("playerHeroId", playerCardId);
		resultSet.put("playerPlace", playerPlace);
		resultSet.put("playerZone", playerZone);

		return resultSet;
	}

	private Map<String, String> getFinishedState(String line) {
		Map<String, String> finished = new HashMap<>();
		finished.put("infoType", "gameFinished");
		finished.put("finishedTime", getDate(line));
		return finished;
	}

	private boolean checkGameFinished(String line) {
		return (line.contains("TAG_CHANGE Entity=GameEntity tag=STATE value=COMPLETE")) ? true : false;
	}

	private boolean gameIsReady() {
		return (isGameStarted && isGameIsBg) ? true : false;
	}

	private void waitForNewGame() {
		isGameStarted = false;
		isGameIsBg = false;
		isGotMainPlayerInfo = false;
		isGotAllPlayersInfo = false;
	}

	public void waitForSpectating(BufferedReader reader) throws IOException {
		String line = null;
		boolean spectating = false;
		line = reader.readLine();

		if (line == null)
			return;

		while (isRunning && spectating) {
			if (line.contains("================== End Spectator Game")) {
				spectating = false;
			}
		}

	}

	public void waitForOtherPlayers(BufferedReader reader) throws IOException, InterruptedException {
		String line = null;
		int otherPlayers = 0;

		Map<String, String> resultSet = new HashMap<String, String>();

		String playerHeroId = null;
		String playerId = null;

		while (isRunning && otherPlayers < C.PLAYERS_AMONUT) {

			line = reader.readLine();

			if (line == null) {
				continue;
			}

			if (line.contains("tag=PLAYER_ID value="))
				playerId = line.split("tag=PLAYER_ID value=")[1].trim();

			if (line.contains("GameState.DebugPrintPower() - FULL_ENTITY - Creating ID=")
					&& line.contains("CardID=TB_BaconShop_HERO_")) {
				String hero = line.split("CardID=")[1].trim();

				if (hero.matches("^.*\\d$"))
					playerHeroId = hero;
			}

			if (playerId != null && playerHeroId != null) {
				resultSet.put("infoType", "playerHero");
				resultSet.put("playerId", playerId);
				resultSet.put("playerHeroId", playerHeroId);

				notifyObservers(resultSet);
				otherPlayers++;
				playerHeroId = null;
				playerId = null;
			}
		}
		isGotAllPlayersInfo = true;
	}

	public boolean ifSpectating(String line) {
		return (line.contains("================== Begin Spectating")) ? true : false;
	}

	private void createLogFileIfNotExists() {
		if (!logFile.exists()) {
			Map<String, String> resultSet = new HashMap<>();
			resultSet.put("infoType", "logFileNotExists");
			resultSet.put("fileNotExists", logFile.getAbsolutePath());

			notifyObservers(resultSet);
		}
	}

	private boolean fileIsEmpty(File logFile) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
			return (reader.lines().count() > 0) ? false : true;
		}
	}

	private void showMessageAndGoToEOF(BufferedReader br) throws IOException {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Начать чтение файла сначала?", "", dialogButton);
		if (dialogResult == 0) {
			// SKIP TO EOF
			while (br.readLine() != null) {
				br.readLine();
			}
		}
	}

	private void getMainPlayerInfo(BufferedReader reader) throws IOException {
		String playerName = null;
		String playerId = null;
		String playerBTag = null;
		String playerHero = null;
		Map<String, String> resultSet = new HashMap<>();
		while (playerName == null || playerId == null || playerBTag == null || playerHero == null) {
			String line = reader.readLine();
			if(line == null)
				continue;
			
			if (line.contains("PlayerID=") && line.contains("PlayerName=") && line.contains("#")) {
				playerName = line.split("PlayerName=")[1].split("#")[0];
				playerId = line.split("PlayerID=")[1].split(",")[0];
				playerBTag = line.split("#")[1];
			}

			if (line.contains("m_chosenEntities") && line.contains("cardId=TB_BaconShop_HERO")) {
				playerHero = line.split("cardId=")[1].split(" ")[0];
			}
		}

		resultSet.put("infoType", "mainPlayer");
		resultSet.put("playerName", playerName);
		resultSet.put("playerId", playerId);
		resultSet.put("playerBTag", playerBTag);
		resultSet.put("playerHeroId", playerHero);
		resultSet.put("gameState", "PLAYING");

		isGotMainPlayerInfo = true;
		notifyObservers(resultSet);

	}

	public void run() {
		try {
			createLogFileIfNotExists();

			BufferedReader br = new BufferedReader(new FileReader(logFile));
			String line = null;

			if (!fileIsEmpty(logFile)) {
				showMessageAndGoToEOF(br);
			}

			while (isRunning) {
				line = br.readLine();

				if (line == null) {
					Thread.sleep(500);
					continue;
				}

//				if(line.contains("FULL_ENTITY - Updating [entityName=") && line.contains("cardId=TB_BaconShop_HERO_") && line.contains("zone=PLAY"))
//					System.out.println(line);

//				if(line.contains("BLOCK_START BlockType=ATTACK Entity=[entityName="))
//					readBlock(br);

				if (ifSpectating(line)) {
					waitForSpectating(br);
					continue;
				}

				// IF GAME NOT READY
				if (!gameIsReady()) {
					// System.out.println("GAME NOT READY...");
					checkGameStarted(line);
					checkGameBattleGrounds(line);
					continue;
				}
				// IF GAME IS READY, BUT NOT GOT MAIN PLAYER INFO
				if (!isGotMainPlayerInfo) {
					// System.out.println("GAME IS READY, BUT NOT GOT MAIN PLAYER INFO...");
					getMainPlayerInfo(br);

				}
				if (!isGotAllPlayersInfo) {
					// System.out.println("GAME IS READY, GOT MAIN PLAYER INFO, BUT NOT GOT OTHER
					// PLAYERS INFO...");
					waitForOtherPlayers(br);
				}

				// IF GAME FINISHED
				if (checkGameFinished(line)) {
					// System.out.println("GAME IS FINISHED...");
					notifyObservers(getFinishedState(line));
					waitForNewGame();
					continue;
				}
				// IF GAME IS READY, GOT PLAYER INFO, BUT NOT FINISHED
				if (updatedPlayersPlace(line)) {
					// System.out.println("UPDATING PLAYER PLACES...");
					notifyObservers(getPlayerPlace(line));
				}

				// Thread.sleep(UPDATE_TIME);

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			stopRunning();

		}
		if (debug)
			this.printLine("Exit the program...");

	}

	private void readBlock(BufferedReader reader) throws IOException {
		String line = null;
		do {
			line = reader.readLine();
			System.out.println(line);
		} while (!line.contains("BLOCK_END"));
	}

	public void stopRunning() {
		isRunning = false;
	}

	@Override
	public void register(LogFileObserver observer) {
		observers.add(observer);

	}

	@Override
	public void remove(LogFileObserver observer) {
		observers.remove(observer);

	}

	@Override
	public void notifyObservers(Map<String, String> resultSet) {
		for (LogFileObserver observer : observers) {
			observer.update(resultSet);
		}

	}

}
