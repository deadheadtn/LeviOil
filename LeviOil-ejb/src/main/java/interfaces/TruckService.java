package interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entity.Part;
import entity.Truck;

@Remote
public interface TruckService {

	public void addTruck(Truck truck);
	public Truck getTruck(int id);
	public Truck getTruckByRegistration(String registration);
	public List<Truck> getAllTruck();
	public void deleteTruck(int id);
	public int updateTruck(int id, Truck newTruck);
	public Truck addPartToTruck(int id, Part part);
	public Truck reportPart(int id, Part part);
	public Truck reportParts(int id, Set<Part> part);
	List<Truck> getTruckBy();
	
}
