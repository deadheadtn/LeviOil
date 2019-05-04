package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.GasStation;
import interfaces.GasStationIservice;

@Stateless 
public class GasStationService implements GasStationIservice {
	@PersistenceContext(unitName = "LeviOil-ejb") 
	EntityManager em; 
	@Override
	public int addGasStation(GasStation gasStation) {
		em.persist(gasStation);
		return gasStation.getIdGas();
	}

	@Override
	public void removeGasStation(int idGasStation) {
		System.out.println("In removegas : ");
		em.remove(em.find(GasStation.class, idGasStation));
		System.out.println("Out of removegas : "); 
		
	}

	@Override
	public void updateGasStation(GasStation gasGasStation) {
		System.out.println("In updateUser : "); 
		GasStation gas = em.find(GasStation.class, gasGasStation.getIdGas()); 
		gas.setName(gasGasStation.getName());
		gas.setStreet(gasGasStation.getStreet());
		gas.setImage(gasGasStation.getImage());
		gas.setLongitude(gas.getLongitude());
		gas.setLatitude(gas.getLatitude());
		System.out.println("Out of updategas : "); 
		
	}

	@Override
	public GasStation findGasStationById(int idGasStation) {
		System.out.println("In findgasById : ");
		GasStation gas = em.find(GasStation.class, idGasStation);
		System.out.println("Out of findgasById : "+ gas.getName()); 
		return gas;
	}


	
	public List<GasStation> findAllGasStations() {
		System.out.println("+++++: ");
		List<GasStation> list = em.createQuery("from GasStation", GasStation.class).getResultList(); 
		System.out.println("11111111111111111111111111"+list.size()); 
		return list; 

    }
	@Override
	public List<GasStation> findGasStationbynameuser(String Name) {
		Query query= em.createQuery("select p from GasStation p where  p.User.firstName=:Name",GasStation.class);

		query.setParameter("Name", Name);
	return	query.getResultList();
	}
	
	@Override
	public GasStation findGasbyname(String Name) {
		Query query= em.createQuery("select p from GasStation p where  p.name=:Name",GasStation.class);

		query.setParameter("Name", Name);
	return	(GasStation) query.getSingleResult();
	}
	
	}

	

	

	

