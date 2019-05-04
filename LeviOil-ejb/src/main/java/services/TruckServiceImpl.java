package services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Part;
import entity.Truck;
import entity.TruckStateEnum;
import interfaces.TruckService;

@Stateless
public class TruckServiceImpl implements TruckService{

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	@Override
	public void addTruck(Truck truck) {
		// TODO Auto-generated method stub
		em.persist(truck);

	}

	@Override
	public Truck getTruck(int id) {
		// TODO Auto-generated method stub
		Truck truck = em.find(Truck.class, id);
		return truck;
	}

	@Override
	public Truck getTruckByRegistration(String registration) {
		// TODO Auto-generated method stub
		TypedQuery<Truck> query = em.createQuery("select t from Truck t where t.registration=:registration", Truck.class);
		query.setParameter("registration", registration);
		return query.getSingleResult();
	}

	@Override
	public List<Truck> getAllTruck() {
		// TODO Auto-generated method stub
		return em.createQuery("from Truck",Truck.class).getResultList();
	}

	@Override
	public void deleteTruck(int id) {
		// TODO Auto-generated method stub
		Truck truck = em.find(Truck.class, id);
		em.remove(truck);
	}

	@Override
	public int updateTruck(int id, Truck newTruck) {
		// TODO Auto-generated method stub
		Truck truck= em.find(Truck.class, id);
		truck.setMarque(newTruck.getMarque());
		truck.setRegistration(newTruck.getRegistration());
		truck.setState(newTruck.getState());
		truck.setUnitPrice(newTruck.getUnitPrice());
		
		return truck.getId();
	}

	@Override
	public Truck addPartToTruck(int id, Part part) {
		Truck truck = em.find(Truck.class, id);
		Part part2 = em.find(Part.class, part.getId());
		truck.getPartsRequested().remove(part2);
		part2.setQuantity(part2.getQuantity()-1);
		if (truck.getPartsRequested().size()==0)
			truck.setState(TruckStateEnum.Ready);
		return truck;
	}
	
	@Override
	public Truck reportPart(int id, Part part) {
		Truck truck = em.find(Truck.class, id);
		Part part2 = em.find(Part.class, part.getId());
		//getPartById()
		truck.getPartsRequested().add(part2);
		//part2.setQuantity(part2.getQuantity()-1);
		if (truck.getState()!=TruckStateEnum.UnderMaintenance)
			truck.setState(TruckStateEnum.UnderMaintenance);
		return truck;
	}

	@Override
	public Truck reportParts(int id, Set<Part> listParts) {
		// TODO Auto-generated method stub
		Truck truck = em.find(Truck.class, id);
		Set<Part> listParts2 = new HashSet<>();
		for (Part part : listParts) {
			listParts2.add(em.find(Part.class, part.getId()));
		}
		truck.getPartsRequested().addAll(listParts2);
		if (truck.getState()!=TruckStateEnum.UnderMaintenance)
			truck.setState(TruckStateEnum.UnderMaintenance);
		return truck;
		
	}

	@Override
	public List<Truck> getTruckBy()
	{
		Query  listTruck= em.createQuery("select count(t.state) as test from Truck t group by t.unitPrice",Long.class);
		return listTruck.getResultList();
	}
}
