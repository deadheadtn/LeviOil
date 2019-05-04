package interfaces;

import java.util.List;


import javax.ejb.Remote;

import entity.Pump;

@Remote
public interface PumpIService {
	public int addPump(Pump Pump);
	public void removePump(int idPump);
	public void updatePump(Pump Pump);
	public Pump findPumpById(int idPump);
	public List<Pump> findAllPump();
	public void usedpump(Pump Pump) ;
	public List<Pump> findUsedPump();
	public List<Pump> findnotUsedPump();
	public void updateUsedPump(Pump Pump);
}
