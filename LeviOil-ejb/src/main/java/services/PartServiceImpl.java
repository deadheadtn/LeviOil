package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Part;
import entity.PartTypeEnum;
import interfaces.PartService;

@Stateless
public class PartServiceImpl implements PartService{

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	@Override
	public void updatePart(int id, Part part) {
		// TODO Auto-generated method stub
		Part part2= em.find(Part.class, id);
		part2.setQuantity(part.getQuantity());
		
	}

	@Override
	public void addParts(int id, int n) {
		// TODO Auto-generated method stub
		Part part= em.find(Part.class, id);
		part.setQuantity(part.getQuantity()+n);
		
	}
	@Override
	public Part getPart(int id) {
		// TODO Auto-generated method stub
		return em.find(Part.class, id);
	}
	
	@Override
	public Part getPartByType(PartTypeEnum type) {
		// TODO Auto-generated method stub
		/*TypedQuery<Part> query = em.createQuery("select p from Part p where p.type=:type", Part.class);
		query.setParameter("type", type);
		
		return query.getSingleResult();*/
		return new Part();
	}

	@Override
	public List<Part> getAllParts() {
		// TODO Auto-generated method stub
		return em.createQuery("from Part",Part.class).getResultList();
	}

	
}
