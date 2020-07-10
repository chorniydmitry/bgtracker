package chernyj.hsbgtracker.swing.statistics;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import chernyj.hsbgtracker.swing.ResultsFrame;

public class GameController {
	
	private ArrayList<String> images = new ArrayList<>();
	private ArrayList<Integer> places = new ArrayList<>();
	
	public GameController() {
		
	}
	
	
	public void addResult(String hero, int place) {
		String image = ResultsFrame.class.getResource("/images/heroes/" + hero + ".jpg").getPath();
		
		images.add(image);
		places.add(place);
		
		updateHtml();
	}
	
	private void updateHtml() {
		Map<String, Object> context = new HashMap<>();
		
		context.put("images", images);
		context.put("places", places);
		
		StringWriter writer = new StringWriter();
		MustacheFactory mustacheFactory = new DefaultMustacheFactory();
		Mustache template = mustacheFactory.compile("templates/game.mustache");
		try {
			template.execute(writer, context).flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------");
		System.out.println(writer.toString());
		
	}

}
