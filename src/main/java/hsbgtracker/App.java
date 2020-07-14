package hsbgtracker;

import java.util.ArrayList;

import chernyj.hsbgtracker.entity.Hero;
import chernyj.hsbgtracker.model.BattlegroundsAnalyser;
import chernyj.hsbgtracker.service.HeroService;
import chernyj.hsbgtracker.swing.MainController;
import chernyj.hsbgtracker.swing.ResultsController;
import chernyj.hsbgtracker.swing.ResultsFrame;
import chernyj.hsbgtracker.swing.Tray;
import chernyj.hsbgtracker.swing.statistics.GameController;
import chernyj.hsbgtracker.swing.statistics.StatisticsController;
import chernyj.hsbgtracker.utils.ApplicationConfiguration;
import chernyj.hsbgtracker.utils.C;
import chernyj.hsbgtracker.utils.LogFileReader;
import chernyj.hsbgtracker.utils.LogFileUtils;

public class App {

	public static void addHeroesToDB() {
		ArrayList<Hero> heroes = new ArrayList<>();

//		heroes.add(new Hero(57633, "TB_BaconShop_HERO_01", 40, "Эдвин ван Клиф"));
//		heroes.add(new Hero(57635, "TB_BaconShop_HERO_02", 40, "Галакронд"));
//		heroes.add(new Hero(57634, "TB_BaconShop_HERO_08", 40, "Иллидан"));
//		heroes.add(new Hero(57891, "TB_BaconShop_HERO_10", 40, "Принц Галливикс"));
//		heroes.add(new Hero(57892, "TB_BaconShop_HERO_11", 40, "Рагнарос"));
//		heroes.add(new Hero(57893, "TB_BaconShop_HERO_12", 40, "Крысиный король"));
//		heroes.add(new Hero(57924, "TB_BaconShop_HERO_14", 40, "Королева Пыхлевих"));
//		heroes.add(new Hero(57929, "TB_BaconShop_HERO_15", 40, "Джордж Падший"));
//		heroes.add(new Hero(57944, "TB_BaconShop_HERO_16", 40, "А. Ф. Ка"));
//		heroes.add(new Hero(57946, "TB_BaconShop_HERO_17", 40, "Миллифисент Манашторм"));
//		heroes.add(new Hero(57947, "TB_BaconShop_HERO_18", 40, "Пират Глазастик"));
//		heroes.add(new Hero(57956, "TB_BaconShop_HERO_19", 40, "Великий Плавник"));
//		heroes.add(new Hero(57961, "TB_BaconShop_HERO_20", 40, "Профессор Мерзоцид"));
//		heroes.add(new Hero(58021, "TB_BaconShop_HERO_21", 40, "Ахалаймахалай"));
//		heroes.add(new Hero(58024, "TB_BaconShop_HERO_22", 40, "Король-лич"));
//		heroes.add(new Hero(58027, "TB_BaconShop_HERO_23", 40, "Дрыжеглот"));
//		heroes.add(new Hero(58044, "TB_BaconShop_HERO_25", 40, "Лич Баз'хиал"));
//		heroes.add(new Hero(58435, "TB_BaconShop_HERO_27", 40, "Синдрагоса"));
//		heroes.add(new Hero(58534, "TB_BaconShop_HERO_28", 40, "Вечная Токи"));
//		heroes.add(new Hero(58536, "TB_BaconShop_HERO_30", 40, "Нефариан"));
//		heroes.add(new Hero(58546, "TB_BaconShop_HERO_31", 40, "Барменатор"));
//		heroes.add(new Hero(59203, "TB_BaconShop_HERO_33", 40, "Смотритель"));
//		heroes.add(new Hero(59397, "TB_BaconShop_HERO_34", 50, "Лоскутик"));
//		heroes.add(new Hero(59805, "TB_BaconShop_HERO_35", 40, "Йогг-Сарон"));
//		heroes.add(new Hero(59806, "TB_BaconShop_HERO_36", 40, "Танцор Дэрил"));
//		heroes.add(new Hero(59807, "TB_BaconShop_HERO_37", 40, "Лорд Джараксус"));
//		heroes.add(new Hero(59814, "TB_BaconShop_HERO_38", 40, "Король Мукла"));
//		heroes.add(new Hero(59831, "TB_BaconShop_HERO_39", 40, "Пирамидон"));
//		heroes.add(new Hero(60211, "TB_BaconShop_HERO_40", 40, "Сэр Финли Мрргглтон"));
//		heroes.add(new Hero(60212, "TB_BaconShop_HERO_41", 40, "Рено Джексон"));
//		heroes.add(new Hero(60213, "TB_BaconShop_HERO_42", 40, "Элиза Звездочет"));
//		heroes.add(new Hero(60214, "TB_BaconShop_HERO_43", 40, "Укротитель ящеров Бранн"));
//		heroes.add(new Hero(60361, "TB_BaconShop_HERO_44", 40, "Сильвана Ветрокрылая"));
//		heroes.add(new Hero(60362, "TB_BaconShop_HERO_45", 40, "Суперзлодей Рафаам"));
//		heroes.add(new Hero(60364, "TB_BaconShop_HERO_47", 40, "Тирион Фордринг"));
//		heroes.add(new Hero(60366, "TB_BaconShop_HERO_49", 40, "Миллхаус Манашторм"));
		heroes.add(new Hero(60367, "TB_BaconShop_HERO_50", 40, "Тесс Седогрив"));
//		heroes.add(new Hero(60369, "TB_BaconShop_HERO_52", 40, "Смертокрыл"));
//		heroes.add(new Hero(60370, "TB_BaconShop_HERO_53", 40, "Изера"));
//		heroes.add(new Hero(60372, "TB_BaconShop_HERO_55", 40, "Грибомант Флургл"));
//		heroes.add(new Hero(61488, "TB_BaconShop_HERO_56", 40, "Алекстраза"));
//		heroes.add(new Hero(61489, "TB_BaconShop_HERO_57", 40, "Ноздорму"));
//		heroes.add(new Hero(61490, "TB_BaconShop_HERO_58", 40, "Малигос"));
//		heroes.add(new Hero(61910, "TB_BaconShop_HERO_59", 40, "Аранна Звездочет"));
//		heroes.add(new Hero(61911, "TB_BaconShop_HERO_59t", 40, "Аранна Освобожденная"));
//		heroes.add(new Hero(61912, "TB_BaconShop_HERO_60", 40, "Принц Кель'тас"));
//		heroes.add(new Hero(61913, "TB_BaconShop_HERO_61", 40, "Леди Вайш"));
//		heroes.add(new Hero(61914, "TB_BaconShop_HERO_62", 40, "Майев Песнь Теней"));
//		heroes.add(new Hero(62242, "TB_BaconShop_HERO_64", 40, "Капитан Юдора"));
//		heroes.add(new Hero(62266, "TB_BaconShop_HERO_67", 40, "Капитан Кривоклык"));
//		heroes.add(new Hero(62268, "TB_BaconShop_HERO_68", 40, "Крагг Поднебесный"));

		HeroService service = new HeroService();

		service.addAll(heroes);
	}
	
	public static void loadStatictics() {
		new StatisticsController();
	}
	
	public static void appStart() {
		new MainController(new Tray());

		LogFileReader reader = new LogFileReader(ApplicationConfiguration.getItem("powerlog.filepath"));

		BattlegroundsAnalyser game = new BattlegroundsAnalyser();

		LogFileUtils utils = new LogFileUtils();

		ResultsController resController = new ResultsController(new ResultsFrame(1000, 110, C.APPLICATION_NAME));

		game.setResultsController(resController);

		reader.register(utils);
		reader.register(game);

		reader.run();
	}
	
	
	private static void testGameController() {
		GameController gc = new GameController();
		gc.addResult("TB_BaconShop_HERO_45", 5);
		gc.addResult("TB_BaconShop_HERO_43", 3);
		gc.addResult("TB_BaconShop_HERO_42", 1);
		gc.addResult("TB_BaconShop_HERO_41", 4);
		gc.addResult("TB_BaconShop_HERO_40", 8);
		gc.addResult("TB_BaconShop_HERO_45", 5);
		gc.addResult("TB_BaconShop_HERO_43", 3);
		gc.addResult("TB_BaconShop_HERO_42", 1);
		gc.addResult("TB_BaconShop_HERO_41", 4);
		gc.addResult("TB_BaconShop_HERO_40", 8);
		gc.addResult("TB_BaconShop_HERO_45", 5);
		gc.addResult("TB_BaconShop_HERO_43", 3);
		gc.addResult("TB_BaconShop_HERO_42", 1);
		gc.addResult("TB_BaconShop_HERO_41", 4);
		gc.addResult("TB_BaconShop_HERO_40", 8);
	}

	public static void main(String[] args) {
		//addHeroesToDB();
		appStart();
		//loadStatictics();
		//testGameController();
		


	}
}
