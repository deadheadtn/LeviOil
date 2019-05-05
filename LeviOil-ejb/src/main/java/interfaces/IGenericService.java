package interfaces;

import java.util.List;

public interface IGenericService {

	void add(Object object);

	void delete(Class<?> cls, int id);

	Object findById(Class<?> cls, int id);

	List<Object> findAll(Class<?> cls);

}
