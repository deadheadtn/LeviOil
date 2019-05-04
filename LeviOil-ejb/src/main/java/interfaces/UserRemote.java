package interfaces;

import javax.ejb.Remote;

import entity.Contrat;
import entity.Type;
import entity.User;

import java.sql.Date;
import java.util.List;
@Remote
public interface UserRemote {

	public User getMembreByUsernamePassword(String firstName,String password);
	public User getUserbyID(int id);
	public User addUser(User user);
	public List<User> findAllUsers();
	public Contrat addContract(Contrat cont);
	public User updateUserbyID(int id,Contrat c);
	public Contrat geContratbyID(int id);
	public Contrat updateContratbyID(int id,float salary,Date enddate , Date startdate,Type type);
	public User updateUserpicbyID(int id,String profilepic);
	public User updateUserprofileID(int id,String username,String firstname,String lastname,String email,String address, int phonenumber,String password);
	public void deleteUserbyid(int id);
	public User ActivateAccount(int id);
	public User DisactivateAccount(int id);
	public User updateUserprofilewithoutpID(int id,String username,String firstname,String lastname,String email,String address, int phonenumber);
	public User AccountQuestionA(int id,String answer,String security_question);
	public User NewPassword(int id,String pass) ;
	User finduserbyname(String Name);
}
