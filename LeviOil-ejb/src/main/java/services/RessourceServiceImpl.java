package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Ressource;
import entity.RessourceTypeEnum;
import interfaces.RessourceService;

@Stateless
public class RessourceServiceImpl implements RessourceService{
	
	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	@Override
	public void addRessource(Ressource ressource) {
		em.persist(ressource);
	}
	
	@Override
	public List<Integer> getEfficiencyByType(RessourceTypeEnum ressourceTypeEnum)
	{
		TypedQuery<Integer> query = em.createQuery("select r.efficiency from Ressource r where r.type=:ressourceType", Integer.class);
		query.setParameter("ressourceType", ressourceTypeEnum);
		return query.getResultList();
	}
	
	@Override
	public void updateRessource(int id, Ressource ressource) {
		// TODO Auto-generated method stub
		Ressource ressource2= em.find(Ressource.class, id);
		ressource2.setQuantity(ressource.getQuantity());
		ressource2.setDescription(ressource.getDescription());
		ressource2.setEfficiency(ressource.getEfficiency());
		
	}
	
	@Override
	public Ressource getRessource(int id) {
		// TODO Auto-generated method stub
		return em.find(Ressource.class, id);
	}
	
	/*@Override
	public Ressource getRessourceByTypeAndEfficiency(RessourceTypeEnum ressourceTypeEnum, Integer n) {
		// TODO Auto-generated method stub
		System.out.println(em.toString());
		TypedQuery<Ressource> query = em.createQuery("select r from Ressource r where  r.type=:ressourceType ", Ressource.class);
		query.setParameter("ressourceType", ressourceTypeEnum);
		//query.setParameter("efficiency", 29);
		System.out.println("mba3ad");
		return query.getResultList().get(0);
	}*/
	
	
	@Override
	public void deleteRessource(int id) {
		Ressource ressource = em.find(Ressource.class, id);
		em.remove(ressource);
	}
	
	@Override
	public List<Ressource> getAllRessources() {
		// TODO Auto-generated method stub
		return em.createQuery("from Ressource",Ressource.class).getResultList();
	}
}
