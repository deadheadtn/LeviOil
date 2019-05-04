
package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import entity.Cv;
import interfaces.CvRemote;
@Stateless
@LocalBean
public class CVService implements CvRemote {

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	public List<Cv> getAllposts () {
		List<Cv> CV = em.createQuery("from Cv", Cv.class).getResultList(); 
		return CV;
	}
	@Override
	public int addCv(Cv CV) { 
		em.persist(CV);
		System.out.println("CV ajouté");
		return 1; 
	}
	
	@Override
	public int modifyCv(Cv CV) {
		em.merge(CV);
		return 0;
	}
	@Override
	public int delete(Cv CV) {
		CV=em.find(Cv.class,CV.getId());
		em.remove(CV);
		return 0;
	}

}
