package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.PumpMeter;
import entity.PumpMeterPump;
import interfaces.PumpMeterPumpIservice;


@Stateless
public class PumpmeterPumpService implements PumpMeterPumpIservice {
	@PersistenceContext(unitName = "LeviOil-ejb") 
	EntityManager em;

	@Override
	public void addPumpMeterPump(PumpMeterPump PumpMeterPump) {
		// TODO Auto-generated method stub
		 em.createNativeQuery("INSERT INTO PumpMeterPump ( ConfirmPump, IdPump,IdPumpMeter) VALUES (?,?,?)")
	      .setParameter(1, false)
	      .setParameter(2, PumpMeterPump.getPump())
	      .setParameter(3, PumpMeterPump.getPumpMeter())
	      .executeUpdate();	} 
	@Override
	public List<PumpMeterPump> findAllAPumpconfirm(PumpMeter pumpMeter) {
		Query query= em.createQuery("select p from PumpMeterPump p where  p.pumpMeter=:pumpMeter and p.ConfirmPump=:ConfirmPump ",PumpMeterPump.class);
		query.setParameter("pumpMeter", pumpMeter);
		query.setParameter("ConfirmPump", true);

	return	query.getResultList();
	}
	
	
	
	
	
	@Override
	public List<PumpMeterPump> findAllAPumpnonconfirm() {
		Query query= em.createQuery("select p from PumpMeterPump p where  p.ConfirmPump=:ConfirmPump",PumpMeterPump.class);

		query.setParameter("ConfirmPump", false);
	return	query.getResultList();
	}

	@Override
	public void updateconfirmPump(PumpMeterPump PumpmeterPump) {

		PumpMeterPump wash = em.find(PumpMeterPump.class, PumpmeterPump.getIdPumpMeterPump()); 
		wash.setConfirmPump(false);

	}
	
	@Override
	public void updatenonconfirmPump(PumpMeterPump PumpmeterPump) {
		PumpMeterPump wash = em.find(PumpMeterPump.class, PumpmeterPump.getIdPumpMeterPump()); 
		wash.setConfirmPump(true);
	}
	
	
	@Override
	public List<PumpMeter> findMateriel(PumpMeter pump) {
		
		
		Query query= em.createQuery("select p.pumpMeter from PumpMeterPump p where  p.pumpMeter =:pumpMeter ",PumpMeter.class);
		query.setParameter("pumpMeter", pump);

		return	query.getResultList();
	}
	
}
