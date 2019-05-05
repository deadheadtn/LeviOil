package interfaces;

import javax.ejb.Remote;

import entity.FuelOrder;


@Remote
public interface FuelOrderService extends IGenericService {

	void updateFuelOrder(FuelOrder fuelOrder);
	
	
}
