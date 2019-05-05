package services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.BarrelInventory;
import entity.Fuel;
import entity.FuelTypeEnum;
import interfaces.FuelService;


@Stateless
public class FuelServiceImpl extends GenericServiceImpl implements FuelService{

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	@Override
	public Fuel getFuelByType(FuelTypeEnum fuelTypeEnum) {
	
		TypedQuery<Fuel> query =
			      em.createQuery("SELECT f FROM Fuel f where f.type=:fuelType", Fuel.class)
			      .setParameter("fuelType", fuelTypeEnum);
		
			  return query.getSingleResult();
			  
	}
	
	@Override
	public List<Fuel> getFuels(){
		@SuppressWarnings("unchecked")
		List<Fuel> list = (List<Fuel>)(Object)this.findAll(Fuel.class);
		System.out.println(list.toString());
			  return list;
	}
	
	@Override
	public float calculateFuelCost(Fuel fuel,int quantity)
	{
		Fuel fuel2 = (Fuel)this.findById(Fuel.class, fuel.getId());
		return fuel2.getPrice()*quantity;
	}
	
	@Override
	public float getFuelQuantity(Fuel fuel)
	{
		TypedQuery<BarrelInventory> query = em.createQuery("Select b from BarrelInventory b where b.fuel.id=:id", BarrelInventory.class)
				.setParameter("id", fuel.getId());
		return query.getResultList().get(0).getBarrelNumber();
	}
	
	
}
