package services;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;


import entity.news;
import interfaces.NewsRemote;

@Stateless
@LocalBean
public class NewsService implements NewsRemote {

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	public List<news> getAllposts () {
		List<news> NEWS = em.createQuery("from news", news.class).getResultList(); 
		
		return NEWS;
	}
	public news getone (int id) {
		return em.createQuery("from news where id=:id ", news.class).setParameter("id", id).getSingleResult(); 
	}
	@Override
	public int addNews(news News) { 
		em.persist(News);
		return 1; 
	}
	@Override
	public int modifynews(news News) {
		System.out.println(News.getId());
		System.out.println(News.getTitle());
		System.out.println(News.getText());
		news n=em.find(news.class, News.getId());
		n.setImage(News.getImage());
		n.setTitle(News.getTitle());
		n.setText(News.getText());
		n.setEditable(false);
		return 0;
	}
	@Override
	public int delete(news News) {
		News=em.find(news.class,News.getId());
		em.remove(News);
		return 0;
	}
	@Override
	public List<news> search(String searchable){
		return em.createQuery("select a from news a where  a.title like CONCAT('%', :title, '%') OR a.text like CONCAT('%', :title, '%')",news.class).setParameter("title", searchable).getResultList();
	}
	public void edit(news n){
		n.setEditable(!n.isEditable());
		em.merge(n);
	}
//	public User getNewsbyID(int id) {
//		
//		User u =em.find(User.class,id);
//		
//		return u;
//	}

}
