import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
public class Blog {
	String title;
	//SortedSet<Post> posts;
	ArrayList<Post> posts;
	final int max_title = 80;

	public Blog(String title) {
		this(title, null);
	}
	public Blog(String title, ArrayList<Post> posts) {
		this.set_title(title);
		if (posts == null) {
			//this.posts = Collections.synchronizedSortedSet(new TreeSet<Post>());
			this.posts = new ArrayList<>(); 
		} else {
			this.posts = posts;
		}
	}
	public boolean set_title(String title) {
		if (title != null && title != "" && title.length() <= max_title) {
			this.title = title;
			return true;
		} else {
			throw new IllegalArgumentException(String.format(
						"Title must have between 1 and %d characters", max_title));
		}
	}
	//public SortedSet<Post> get_posts() {
	public ArrayList<Post> get_posts() {
		return this.posts;
	}
	public boolean add_post(Post post) {
		return this.posts.add(post);
	}
	public boolean remove_post (Post post) {
		return posts.remove(post);
	}
	//This method will find based on matching substrings, not just exact matches.
	public ArrayList<Post> find_posts(String s) { 
		//SortedSet<Post> found = Collections.synchronizedSortedSet(new TreeSet<Post>());
		ArrayList<Post> found = new ArrayList<>();

		for(Post p: posts) {
			if(p.get_title().contains(s)
				|| p.get_author().contains(s)
				|| p.get_body().contains(s)
				|| p.get_labels().contains(s))
			{
				found.add(p);
			}
		}
		return found;
	}

}
