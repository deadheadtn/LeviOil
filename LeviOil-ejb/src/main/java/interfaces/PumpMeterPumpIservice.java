package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.PumpMeter;
import entity.PumpMeterPump;


@Remote
public interface PumpMeterPumpIservice {
	public void addPumpMeterPump(PumpMeterPump PumpMeterPump);
	public List<PumpMeterPump> findAllAPumpnonconfirm();
	public List<PumpMeterPump> findAllAPumpconfirm(PumpMeter pumpMeter) ;
	public void updatenonconfirmPump(PumpMeterPump PumpmeterPump);
	public	void updateconfirmPump(PumpMeterPump PumpmeterPump);
	public List<PumpMeter> findMateriel(PumpMeter pump);
}
