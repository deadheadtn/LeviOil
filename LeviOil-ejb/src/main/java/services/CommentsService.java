package services;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;


import entity.Comments;
import entity.User;
import entity.news;
import interfaces.CommentsRemote;

@Stateless
@LocalBean
public class CommentsService implements CommentsRemote {
	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	public List<Comments> getAllcomments () {
		return em.createQuery("from Comments", Comments.class).getResultList(); 
	}
	
	public int addComment(Comments c) { 
		System.out.println(c.toString());
		em.persist(c);
		return 1; 
	}
	@Override
	public long getremovedComments(int month){
		return (long)em.createQuery("SELECT count(*) from Comments  c where c.removed=true and MONTH(c.createdAt)=:month").setParameter("month", month).getSingleResult();
	}
	public List<Comments> getAllcommentsfrompost (news n) {
		return em.createQuery("from Comments where post=:post", Comments.class).setParameter("post", n.getId()).getResultList(); 
	}
}