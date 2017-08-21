import java.util.Collections;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.time.Instant;
import java.lang.IllegalArgumentException;
public class TextUnit implements Comparable<TextUnit> {
	String author;
	String body;
	SortedSet<TextUnit> comments;
	final Instant creation;

	public TextUnit(String author, String body) {
		this(author, body, null);
	}
	public TextUnit(String author, String body, SortedSet<TextUnit> comments) {
		try {
			this.set_author(author);
			this.set_body(body);
		} catch (IllegalArgumentException e) {
			throw e;
		}
		if (comments == null) {
			this.comments = Collections.synchronizedSortedSet(new TreeSet<TextUnit>());
		} else {
			this.comments = comments;
		}
		this.creation = Instant.now();
	}
	@Override
	public int compareTo(TextUnit b) {
		return this.get_creation().compareTo(b.get_creation());
	}
	@Override
	public String toString() {
		return "Author: " + this.get_author() + "\nBody: " + this.get_body();
	}
	public Instant get_creation() {
		return this.creation;
	}
	//Returns author string
	public String get_author() {
		return this.author;
	}
	//Returns body string
	public String get_body() {
		return this.body;
	}
	public boolean has_comments() {
		return (comments != null && !comments.isEmpty()); 
	}
	//Returns set of comments, or null if there are none 
	public SortedSet<TextUnit> get_comments() {
		if (this.has_comments()) {
			return this.comments;
		} else {
			return null;
		}
	}
	//Modify author
	public boolean set_author(String author) throws IllegalArgumentException {
		if (author != null && author != "") {
			this.author = author;
			return true;
		} else {
			throw new IllegalArgumentException("Invalid author field");
		}
	}
	//Modify body
	public boolean set_body(String body) throws IllegalArgumentException {
		if (body != null && body != "") {
			this.body = body;
			return true;
		} else {
			throw new IllegalArgumentException("Invalid body field");
		}
	}
	public boolean add_comment(TextUnit comment) {
		return this.comments.add(comment);
	}
	public boolean remove_comment(TextUnit comment) {
		return this.comments.remove(comment);
	}
	public ArrayList<TextUnit> find_text(String author, String body) {
		ArrayList<TextUnit> found = new ArrayList<>(); 
		if(author == null) {
			author = "";
		}
		if(body == null) {
			body = "";
		}
		for(TextUnit c: comments) {
			found.addAll(c.find_text(author, body));
			if (c.get_author().contains(author)
				&& c.get_body().contains(body)) 
			{
				found.add(c);
			}
		}
		return found;
	}
}
