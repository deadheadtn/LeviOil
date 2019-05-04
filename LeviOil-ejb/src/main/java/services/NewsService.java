package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Contrat;
import entity.Type;
import entity.User;
import entity.news;
import interfaces.NewsRemote;
import interfaces.UserRemote;
@Stateless
@LocalBean
public class NewsService implements NewsRemote {

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	public List<news> getAllposts () {
		List<news> NEWS = em.createQuery("from news", news.class).getResultList(); 
		
		return NEWS;
	}
	@Override
	public int addNews(news News) { 
		em.persist(News);
		System.out.println("News ajouté");
		return 1; 
	}
	@Override
	public int modifynews(news News) {
		em.merge(News);
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
//	public User getNewsbyID(int id) {
//		
//		User u =em.find(User.class,id);
//		
//		return u;
//	}

}
