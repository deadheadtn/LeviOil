package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Contrat;
import entity.Type;
import entity.User;
import interfaces.UserRemote;


@Stateless
@LocalBean
public class UserService implements UserRemote {

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	public User getMembreByUsernamePassword (String firstName,String password) {
		User u = null;
		try {
			u = em.createQuery(
				"SELECT u FROM user u  WHERE u.login=:login ", User.class)
		.setParameter("login", firstName).getSingleResult();
		
		
		 
			
	} catch (NoResultException e){
	    
	    
	}
		return u;
	}
	public User getUserbyID(int id) {
		
		User u =em.find(User.class,id);
		
		return u;
	}
	
	
	@Override
	public User addUser(User user) { 
		em.persist(user);
		System.out.println("user ajouté");
		return user; }

	

	public List<User> findAllUsers() {
	
	List<User> users = em.createQuery("from user", User.class).getResultList(); 
	
	return users; 
	}
	
	@Override
	public Contrat addContract(Contrat cont) { 
		em.persist(cont);
		System.out.println("user ajouté");
		return cont; }

public User updateUserbyID(int id,Contrat c) {
		
		User u =em.find(User.class,id);
		 u.setContrat(c);
		
		return u;
	}
public Contrat geContratbyID(int id) {
	
	Contrat u =em.find(Contrat.class,id);
	
	return u;
}

/*public User updateProfilebyID(int id,String login, String password, String firstName, String lastName, String email,
		 int phoneNumber, String address,String image,Role role) {
	
	User u =em.find(User.class,id);
	
	
	return u;
}*/
public Contrat updateContratbyID(int id,float salary,Date enddate , Date startdate,Type type) {
	
	Contrat u =em.find(Contrat.class,id);
	 u.setSalary(salary);
	 u.setEnddate(enddate);
	 u.setStartdate(startdate);
	 u.setType(type);
	
	return u;
}
public User updateUserpicbyID(int id,String profilepic) {
	
	User u =em.find(User.class,id);
	 u.setProfilePicture(profilepic);
	return u;
}

public User updateUserprofileID(int id,String username,String firstname,String lastname,String email,String address, int phonenumber,String password) {
	
	User u =em.find(User.class,id);
	 u.setLogin(username);
	 u.setFirstName(firstname);
	 u.setLastName(lastname);
	 u.setAddress(address);
	 u.setEmail(email);
	 u.setPassword(password);
	 u.setPhoneNumber(phonenumber);
	return u;
}
public User updateUserprofilewithoutpID(int id,String username,String firstname,String lastname,String email,String address, int phonenumber) {
	
	User u =em.find(User.class,id);
	 u.setLogin(username);
	 u.setFirstName(firstname);
	 u.setLastName(lastname);
	 u.setAddress(address);
	 u.setEmail(email);
	 
	 u.setPhoneNumber(phonenumber);
	return u;
}
public void deleteUserbyid(int id) {
	User u =em.find(User.class,id);
	em.remove(u);
	
}

public User ActivateAccount(int id) {
	
	User u =em.find(User.class,id);
	 u.setState(1);
	return u;
}


public User DisactivateAccount(int id) {
	
	User u =em.find(User.class,id);
	 u.setState(0);
	return u;
}

public User AccountQuestionA(int id,String answer,String security_question) {
	
	User u =em.find(User.class,id);
	 u.setAnswer(answer);
	 u.setSecurity_question(security_question);
	return u;
}
public User NewPassword(int id,String pass) {
	
	User u =em.find(User.class,id);
	 u.setPassword(pass);
	return u;
}
public User finduserbyname (String Name) {
	User u = null;
	try {
		u = em.createQuery(
			"SELECT u FROM user u  WHERE u.firstName=:Name ", User.class)
	.setParameter("Name", Name).getSingleResult();
	
	
	 
		
} catch (NoResultException e){
    
    
}
	return u;
}

}
