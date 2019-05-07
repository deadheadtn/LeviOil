package interfaces;

import javax.ejb.Remote;
import entity.Comments;
import entity.User;
import entity.news;
import java.util.List;

@Remote
public interface CommentsRemote {
	public List<Comments> getAllcomments();
}
