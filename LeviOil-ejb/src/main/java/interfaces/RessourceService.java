package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Ressource;
import entity.RessourceTypeEnum;

@Remote
public interface RessourceService {

	void updateRessource(int id, Ressource ressource);

	Ressource getRessource(int id);

	List<Ressource> getAllRessources();

	void addRessource(Ressource ressource);

	void deleteRessource(int id);

	List<Integer> getEfficiencyByType(RessourceTypeEnum ressourceTypeEnum);

//	Ressource getRessourceByTypeAndEfficiency(RessourceTypeEnum ressourceTypeEnum, Integer n);

}
