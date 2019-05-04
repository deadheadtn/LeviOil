package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Washing;
import entity.WashingMaterielWashing;
@Remote
public interface WashingmaterielwashingIservice {
	public void addWashingmaterielwashing(WashingMaterielWashing Washingmaterielwashing);
	public void DeleteWashingmaterielwashing(int idWashingmaterielwashing);
	public void updateWashingmaterielwashing100(WashingMaterielWashing Washingmaterielwashing);
 	public WashingMaterielWashing findWashingmaterielwashingById(int idWashingmaterielwashing);
	public List<WashingMaterielWashing> findAllWashingmaterielwashingvide();
	public List<WashingMaterielWashing> findAllWashingmaterielwashingplein();
	public List<WashingMaterielWashing> findAllWashingmaterielwashing();
	public void updateWashingmaterielwashingapointment(Washing washing) ;
	public List<WashingMaterielWashing> findAllWashingmaterielwashingbylavage(Washing wa);
	public List<Washing> findwashingbyquantity();
	public List<Washing> findMateriel(Washing wash);
}
