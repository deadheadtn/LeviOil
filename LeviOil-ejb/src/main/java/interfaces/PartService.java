package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Part;
import entity.PartTypeEnum;
import entity.Truck;

@Remote
public interface PartService {

	public void updatePart(int id, Part part);
	public Part getPart(int id);
	public List<Part> getAllParts();
	public Part getPartByType(PartTypeEnum type);
	void addParts(int id, int n);
	
	
}
