import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collections;
import java.lang.IllegalArgumentException;
public class Post extends TextUnit {
	String title;
	SortedSet<String> labels;
	final int max_title = 80;
	final int max_label = 80;

	public Post(String title, String author, String body) {
		this(title, null, author, body, null);
	}
	public Post(String title, String author, String body, SortedSet<TextUnit> comments) {
		this(title, null, author, body, comments);
	}
	public Post(String title, SortedSet<String> labels, String author, String body) {
		this(title, labels, author, body, null);
	}
	public Post(String title, SortedSet<String> labels, String author, String body, SortedSet<TextUnit> comments) {
		super(author, body, comments);
		this.set_title(title);
		if (labels == null) {
			this.labels = Collections.synchronizedSortedSet(new TreeSet<String>());
		} else {
			this.labels = labels;
		}
	}
	public String get_title() {
		return this.title;
	}
	public SortedSet<String> get_labels() {
		return this.labels;
	}
	public boolean set_title(String title) throws IllegalArgumentException {
		if (title != null && title != "" && title.length() <= max_title) {
			this.title = title;
			return true;
		} else {
			throw new IllegalArgumentException(String.format(
						"Title must be between 1 and %d characters", max_title));
		}
	}
	public boolean add_label(String label) throws IllegalArgumentException {
		if (label != null && label != "" && label.length() <= max_label) {
			return this.labels.add(label);
		} else {
			throw new IllegalArgumentException(String.format(
						"Label must be between 1 and %d characters", max_label));
		}
	}
	public boolean remove_label(String r_label) {
		for (String l : labels) {
			if (l.equals(r_label)) {
				labels.remove(l);
			}
		}
		return true;
	}
}
