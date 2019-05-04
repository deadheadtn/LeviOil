package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.MaterielWashing;
import interfaces.MaterielWashingIservice;

@Stateless
public class MaterielWashingService implements MaterielWashingIservice{
	@PersistenceContext(unitName = "LeviOil-ejb") 
	EntityManager em; 
	
	@Override
	public int addMaterielWashing(MaterielWashing MaterielWashing) {
		em.persist(MaterielWashing);
		return MaterielWashing.getIdMaterielWashing();
	}
	@Override
	public void addMateriel1() {
		
		  em.createNativeQuery("INSERT INTO MaterielWashing (NameMateriel) VALUES (?)")
	      .setParameter(1, "shampoing auto")
	      .executeUpdate();

	}
	@Override
	public void addMateriel2() {
		
		  em.createNativeQuery("INSERT INTO MaterielWashing (NameMateriel) VALUES (?)")
	      .setParameter(1, "nettoyant mousse")
	      .executeUpdate();

	}
	@Override
	public void addMateriel3() {
		
		  em.createNativeQuery("INSERT INTO MaterielWashing (NameMateriel) VALUES (?)")
	      .setParameter(1, "nettoyant jante aluminium")
	      .executeUpdate();

	}
	@Override
	public void removeMaterielWashing(int idMaterielWashing) {
		System.out.println("In removeMaterielwashing : ");
		em.remove(em.find(MaterielWashing.class, idMaterielWashing));
		System.out.println("Out of removeMaterielWashing : "); 
	}

	@Override
	public void updateMaterielWashing(MaterielWashing MaterielWashing) {
		MaterielWashing wash = em.find(MaterielWashing.class, MaterielWashing.getIdMaterielWashing()); 
		wash.setNameMateriel(MaterielWashing.getNameMateriel());
		
		System.out.println("Out of updateMateriel : "); 
		
	}

	@Override
	public MaterielWashing findMaterielWashingById(int idMaterielWashing) {
		System.out.println("In findgasById : ");
		MaterielWashing wash = em.find(MaterielWashing.class, idMaterielWashing);
//		System.out.println("Out of findgasById : "+ wash.getIdMaterielWashing()); 
		return wash;
	}

	@Override
	public List<MaterielWashing> findAllMaterielWashing() {
		List<MaterielWashing> list = em.createQuery("from MaterielWashing", MaterielWashing.class).getResultList(); 
		return list; 
	}

}
