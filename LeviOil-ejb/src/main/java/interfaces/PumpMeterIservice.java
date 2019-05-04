package interfaces;

import java.util.List;


import javax.ejb.Remote;

import entity.GasStation;
import entity.PumpMeter;


@Remote
public interface PumpMeterIservice {
	public int addPumpMeter(PumpMeter PumpMeter);
	public void DeletePumpMeter(int idPumpMeter);
	public void updatePumpMeter(PumpMeter PumpMeter);
	public PumpMeter findPumpMeterById(int idPumpMeter);
	public List<PumpMeter> findAllPumpMeter();
	public List<PumpMeter> findPumpByGasStation(GasStation GasStation);
	public int findbigid();

}
