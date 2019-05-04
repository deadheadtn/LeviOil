package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.GasStation;
import entity.Washing;
import interfaces.WashingIservice;

@Stateless
public class WashingService implements WashingIservice {
	@PersistenceContext(unitName = "LeviOil-ejb") 
	EntityManager em; 
	
	@Override
	public int addWashing(Washing Washing) {
		em.persist(Washing);
		return Washing.getIdWashing();
	}

	@Override
	public void removeWashing(int idWashing) {
		System.out.println("In removewashing : ");
		em.remove(em.find(Washing.class, idWashing));
		System.out.println("Out of removeWashing : "); 
		
	}

	@Override
	public void updateWashing(Washing Washing) {
		System.out.println("In updatewashing : "); 
		Washing wash = em.find(Washing.class, Washing.getIdWashing()); 
		wash.setEtat(Washing.getEtat());
		wash.setGasStation(Washing.getGasStation());
		
		System.out.println("Out of updategas : "); 
		
	}

	@Override
	public Washing findWashingById(int idWashing) {
		System.out.println("In findgasById : ");
		Washing wash = em.find(Washing.class, idWashing);
		System.out.println("Out of findgasById : "+ wash.getIdWashing()); 
		return wash;
	}

	@Override
	public List<Washing> findAllWashings() {
		List<Washing> list = em.createQuery("from Washing", Washing.class).getResultList(); 
		return list; 
	}

	@Override
	public List<Washing> findWashingByGasStation(GasStation GasStation) {
		Query query= em.createQuery("select p from Washing p where  p.GasStation=:GasStation",Washing.class);

		query.setParameter("GasStation", GasStation);
	return	query.getResultList();
	}
	@Override
	public int findbigid() {
		Query query= em.createQuery("select max(p.IdWashing) from Washing p",Integer.class);

	
	return	(int) query.getSingleResult();
}
}