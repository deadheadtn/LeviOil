package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.FuelOrder;
import interfaces.FuelOrderService;



@Stateless
public class FuelOrderServiceImpl extends GenericServiceImpl implements FuelOrderService{

	@PersistenceContext(unitName = "GestionTruck-ejb")
	EntityManager em;
	
	@Override
	public void updateFuelOrder(FuelOrder fuelOrder) {
		em.merge(fuelOrder);
	}
}
