package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.IGenericService;



public class GenericServiceImpl implements IGenericService {
	
	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;

	@Override
	public void add(Object object) {
		em.persist(object);
	}

	@Override
	public void delete(Class<?> cls, int id) {
		Object object = em.find(cls, id);
		em.remove(object);
	}

	@Override
	public Object findById(Class<?> cls, int id) {
		return em.find(cls, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class<?> cls) {
		String query = "from "+cls.getName();

		return (List<Object>) em.createQuery(query,cls).getResultList();
	}

}
