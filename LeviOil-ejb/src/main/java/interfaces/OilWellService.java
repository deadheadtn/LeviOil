package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.OilWell;
import entity.Ressource;
import entity.RessourceTypeEnum;
@Remote
public interface OilWellService {

	public void addOilWell(OilWell oilWell);
	public OilWell getOilWell(int id);
	//public Truck getOilWellByRegistration(String registration);
	public List<OilWell> getAllOilWell();
	public void deleteOilWell(int id);
	public int updateOilWell(int id, OilWell newOilWell);
	public OilWell addRessourceToOilWell(int id, Ressource ressource);
	public OilWell removeRessourceFromOilWell(int id, Ressource ressource);
	OilWell getOilWellByRegistration(String registration);
	int startDrilling(int id);
	int startExtraction(int id);
	OilWell refreshProgress(int id);
	void refresh();
	int update2(int id, OilWell oilWell);
	Ressource getRessourceByTypeAndEfficiency(RessourceTypeEnum ressourceTypeEnum, Integer n);
	
}
