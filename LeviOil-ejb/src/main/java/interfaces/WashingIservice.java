package interfaces;

import java.util.List;
import javax.ejb.Remote;

import entity.GasStation;
import entity.Washing;

@Remote
public interface WashingIservice {
	public int addWashing(Washing Washing);
	public void removeWashing(int idWashing);
	public void updateWashing(Washing Washing);
	public Washing findWashingById(int idWashing);
	public List<Washing> findAllWashings();
	public List<Washing> findWashingByGasStation(GasStation GasStation) ;
	public int findbigid();
}
