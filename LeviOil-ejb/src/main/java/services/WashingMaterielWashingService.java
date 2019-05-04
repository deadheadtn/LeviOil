package services;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Washing;
import entity.WashingMaterielWashing;
import interfaces.WashingmaterielwashingIservice;
@Stateless
public class WashingMaterielWashingService implements WashingmaterielwashingIservice {
	@PersistenceContext(unitName = "LeviOil-ejb") 
	EntityManager em; 
	@Override
	public void addWashingmaterielwashing(WashingMaterielWashing Washingmaterielwashing) {
		
		  em.createNativeQuery("INSERT INTO WashingMaterielWashing (ConfirmMateriel, QuantiteMateriel, IdMaterielWashing,IdWashing) VALUES (?,?,?,?)")
	      .setParameter(1, false)
	      .setParameter(2, 0)
	      .setParameter(3, Washingmaterielwashing.getMaterielWashing())
	      .setParameter(4, Washingmaterielwashing.getWashing())
	      .executeUpdate();

	}

	@Override
	public void DeleteWashingmaterielwashing(int idWashingmaterielwashing) {
		System.out.println("In removeMaterielwashing : ");
		em.remove(em.find(WashingMaterielWashing.class, idWashingmaterielwashing));
		System.out.println("Out of removeMaterielWashing : "); 
		
	}

	@Override
	public void updateWashingmaterielwashing100(WashingMaterielWashing Washingmaterielwashing) {
		WashingMaterielWashing wash = em.find(WashingMaterielWashing.class, Washingmaterielwashing.getIdwashingmaterielwashing()); 
		wash.setQuantiteMateriel(100);
	}

	@Override
	public void updateWashingmaterielwashingapointment(Washing washing) {

		Query query = em.createQuery(
				 "UPDATE WashingMaterielWashing e SET e.QuantiteMateriel = e.QuantiteMateriel -10 WHERE e.washing=:washing");

		  int rowsUpdated = query.setParameter("washing", washing).executeUpdate();
	      System.out.println("entities Updated: " + rowsUpdated);

	}
	@Override
	public WashingMaterielWashing findWashingmaterielwashingById(int idWashingmaterielwashing) {
		System.out.println("In findgasById : ");
		WashingMaterielWashing wash = em.find(WashingMaterielWashing.class, idWashingmaterielwashing);
		System.out.println("Out of findgasById : "+ wash.getIdwashingmaterielwashing()); 
		return wash;
	}

	@Override
	public List<WashingMaterielWashing> findAllWashingmaterielwashingvide() {
		Query query= em.createQuery("select p from WashingMaterielWashing p where p.QuantiteMateriel=:QuantiteMateriel",WashingMaterielWashing.class);
		query.setParameter("QuantiteMateriel", 0);

	return	query.getResultList();
	}
	@Override
	public List<WashingMaterielWashing> findAllWashingmaterielwashingplein() {
		Query query= em.createQuery("select p from WashingMaterielWashing p where  p.QuantiteMateriel=:QuantiteMateriel",WashingMaterielWashing.class);
		query.setParameter("QuantiteMateriel", 100);

	return	query.getResultList();
	}
	
	
	@Override
	public List<WashingMaterielWashing> findAllWashingmaterielwashingbylavage(Washing wa) {
		Query query= em.createQuery("select p from WashingMaterielWashing p where  p.washing=:Washing",WashingMaterielWashing.class);
		query.setParameter("Washing", wa);

	return	query.getResultList();
	}
	
	@Override
	public List<WashingMaterielWashing> findAllWashingmaterielwashing() {
		List<WashingMaterielWashing> list = em.createQuery("from WashingMaterielWashing", WashingMaterielWashing.class).getResultList(); 
		return list;
	}
	@Override
	public List<Washing> findwashingbyquantity(){
		Query query= em.createQuery("select p.washing from WashingMaterielWashing p where  p.QuantiteMateriel BETWEEN 1 and 100 ",Washing.class);
		//List<Integer> names = Arrays.asList(25,50,75,100);
		//query.setParameter("Washing",names );
		
		
		return	query.getResultList();
	}
	@Override
	public List<Washing> findMateriel(Washing wash) {
		
		
		Query query= em.createQuery("select p.washing from WashingMaterielWashing p where  p.washing =:washing ",Washing.class);
		query.setParameter("washing", wash);

		return	query.getResultList();
	}
	
}
