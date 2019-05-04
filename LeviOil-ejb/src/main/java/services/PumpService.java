package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Pump;
import interfaces.PumpIService;

@Stateless
public class PumpService implements PumpIService {
	@PersistenceContext(unitName = "LeviOil-ejb") 
	EntityManager em;
	@Override
	public int addPump(Pump Pump) {
		em.persist(Pump);
		return Pump.getIdPump();
	}

	@Override
	public void removePump(int idPump) {
		System.out.println("In rem : ");
		em.remove(em.find(Pump.class, idPump));
		System.out.println("Out of rem : "); 		
	}

	@Override
	public void updatePump(Pump Pump) {
		Pump wash = em.find(Pump.class, Pump.getIdPump()); 
		wash.setName(Pump.getName());
		
	}
	
	
	
	
	@Override
	public Pump findPumpById(int idPump) {
		System.out.println("In findgasById : ");
		Pump wash = em.find(Pump.class, idPump);
		System.out.println("Out of findgasById aaaaaa: "+ wash.getIdPump()); 
		return wash;
	}

	@Override
	public List<Pump> findAllPump() {
		List<Pump> list = em.createQuery("from Pump", Pump.class).getResultList(); 
		return list;
	}
	@Override
	public List<Pump> findUsedPump() {
		Query query= em.createQuery("select p from Pump p where  p.Used=:Used",Pump.class);

		query.setParameter("Used", true);
	return	query.getResultList();

	}
	@Override
	public List<Pump> findnotUsedPump() {
		Query query= em.createQuery("select p from Pump p where  p.Used=:Used",Pump.class);

		query.setParameter("Used", false);
	return	query.getResultList();

	}

	@Override
	public void usedpump(Pump Pump) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUsedPump(Pump Pump) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
