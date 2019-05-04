package services;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Fuel;
import entity.FuelTypeEnum;
import interfaces.FuelService;

@Stateless
public class FuelServiceImpl implements FuelService{

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	@Override
	public Fuel getFuelByType(FuelTypeEnum fuelTypeEnum) {
	
		TypedQuery<Fuel> query =
			      em.createQuery("SELECT f FROM Fuel f where f.type=:fuelType", Fuel.class)
			      .setParameter("fuelType", fuelTypeEnum);
		
			  return query.getSingleResult();
			  
	}
}
