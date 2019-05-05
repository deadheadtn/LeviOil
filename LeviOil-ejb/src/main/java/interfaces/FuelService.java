package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Fuel;
import entity.FuelTypeEnum;
@Remote
public interface FuelService extends IGenericService {

	Fuel getFuelByType(FuelTypeEnum fuelTypeEnum);

	List<Fuel> getFuels();

	float calculateFuelCost(Fuel fuel, int quantity);

	float getFuelQuantity(Fuel fuel);

}
