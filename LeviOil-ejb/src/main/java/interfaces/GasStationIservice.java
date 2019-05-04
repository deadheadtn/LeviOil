package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.GasStation;



@Remote
public interface GasStationIservice {
	public int addGasStation(GasStation gasStation);
	public void removeGasStation(int idGasStation);
	public void updateGasStation(GasStation  gasGasStation);
	public GasStation findGasStationById(int idGasStation);
	public List<GasStation> findAllGasStations();
	public List<GasStation> findGasStationbynameuser(String Name);
	public GasStation findGasbyname(String Name);
}
