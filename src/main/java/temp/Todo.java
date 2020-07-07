package temp;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class Todo {
	String title;
	String text;
	
	public Todo(String title, String text) {
		this.title = title;
		this.text = text;
	}
	
	public static void muctache() throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Mustache m = mf.compile("todo.mustache");
		
		Todo todo = new Todo("Todo 1", "Description");
		StringWriter writer = new StringWriter();
		m.execute(writer, todo).flush();
		String html = writer.toString();
		System.out.println(html);
	}
	
	public static void main(String[] args) throws IOException {
		muctache();
		
	}

}
