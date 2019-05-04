package interfaces;

import javax.ejb.Remote;

import entity.Fuel;
import entity.FuelTypeEnum;
@Remote
public interface FuelService {

	Fuel getFuelByType(FuelTypeEnum fuelTypeEnum);

}
