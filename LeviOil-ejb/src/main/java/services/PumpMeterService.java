package services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.GasStation;
import entity.PumpMeter;
import interfaces.PumpMeterIservice;

@Stateless
public class PumpMeterService implements PumpMeterIservice {
	@PersistenceContext(unitName = "LeviOil-ejb") 
	EntityManager em; 
	@Override
	public int addPumpMeter(PumpMeter PumpMeter) {
		em.persist(PumpMeter);
		return PumpMeter.getIdPumpMeter();	}

	@Override
	public void DeletePumpMeter(int idPumpMeter) {
		System.out.println("In removeMaterielwashing : ");
		em.remove(em.find(PumpMeter.class, idPumpMeter));
		System.out.println("Out of removeMaterielWashing : "); 
	}

	@Override
	public void updatePumpMeter(PumpMeter PumpMeter) {
		PumpMeter wash = em.find(PumpMeter.class, PumpMeter.getIdPumpMeter()); 
		wash.setQuantitéCarburant(PumpMeter.getQuantitéCarburant());
		wash.setTypePumpMeter(PumpMeter.getTypePumpMeter());
		wash.setUsers(PumpMeter.getUsers());
		wash.setPump(PumpMeter.getPump());
	}

	@Override
	public PumpMeter findPumpMeterById(int idPumpMeter) {
		System.out.println("In findgasById : ");
		PumpMeter wash = em.find(PumpMeter.class, idPumpMeter);
		System.out.println("Out of findgasById : "+ wash.getIdPumpMeter()); 
		return wash;
	}

	@Override
	public List<PumpMeter> findAllPumpMeter() {
		List<PumpMeter> list = em.createQuery("from PumpMeter", PumpMeter.class).getResultList(); 
		return list;
	}
	@Override
	public List<PumpMeter> findPumpByGasStation(GasStation GasStation) {
		Query query= em.createQuery("select p from PumpMeter p where  p.gasStation=:GasStation",PumpMeter.class);

		query.setParameter("GasStation", GasStation);
	return	query.getResultList();
	}
	@Override
	public int findbigid() {
		Query query= em.createQuery("select max(p.IdPumpMeter) from PumpMeter p",Integer.class);

	
	return	(int) query.getSingleResult();
}


}
