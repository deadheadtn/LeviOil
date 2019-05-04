
package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.news;

@Remote
public interface NewsRemote {

	public List<news> getAllposts();
	public int addNews(news News);
	int modifynews(news News);
	public int delete(news News);
	List<news> search(String searchable);
}
