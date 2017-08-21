import java.util.ArrayList;
public class Test {
	public static void main(String[] args) {
		System.out.print("Starting tests...");
		Blog testBlog0 = new Blog("Title0");

		ArrayList<Post> testPosts = new ArrayList<>();
		ArrayList<TextUnit> testComments = new ArrayList<>();
		for(int i=0; i<5; i++) {
			Post p = new Post("PostTitle"+i, "PostAuthor"+i, "PostBody"+i);
			testPosts.add(p);
			testBlog0.add_post(p);
			TextUnit c = new TextUnit("CommentTitle"+i, "CommentBody"+i);
			testComments.add(p);
		}

		//test post modification
		testPosts.get(0).set_title("newPostTitle");
		assert(testPosts.get(0).get_title().equals("newPostTitle"));
		testPosts.get(1).set_author("newPostAuthor");
		assert(testPosts.get(1).get_author().equals("newPostAuthor"));
		testPosts.get(2).set_body("newPostBody");
		assert(testPosts.get(2).get_body().equals("newPostBody"));
		testPosts.get(3).add_label("label");
		assert(testPosts.get(3).get_labels().contains("label"));
		testPosts.get(4).add_label("removeThis");
		testPosts.get(4).remove_label("removeThis");
		assert(!testPosts.get(4).get_labels().contains("removeThis"));

		//test comment replies
		testComments.get(0).add_comment(testComments.get(1));
		assert(testComments.get(0).get_comments().contains(testComments.get(1)));
		
		//test commenting
		testPosts.get(0).add_comment(testComments.get(0));
		assert(testPosts.get(0).get_comments().contains(testComments.get(0)));
		testPosts.get(0).add_comment(testComments.get(1));

		//test comment removal
		testPosts.get(0).remove_comment(testComments.get(1));
		assert(!testPosts.get(0).has_comments());

		//test find
		assert(testBlog0.find_posts("newPostTitle").contains(testPosts.get(0)));
		assert(testBlog0.find_posts("newPostAuthor").contains(testPosts.get(1)));
		assert(testBlog0.find_posts("newPostBody").contains(testPosts.get(2)));
		assert(testBlog0.find_posts("label").contains(testPosts.get(3)));
		assert(!testBlog0.find_posts("removeThis").contains(testPosts.get(4)));
		System.out.println("done!");
	}
}
