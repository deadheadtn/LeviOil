package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.User;
import services.UserLogService;
import services.UserService;

@ManagedBean
@SessionScoped
public class DetailUserLog {
	
	@EJB
	UserLogService userlogservice;
	@EJB
	UserService userservice;
	private User u;
	private String FailureAttemptNumber;
	private String image;
	private String Name;
	private String Role;
	private String phonenumber;
	private String AttemptLogNumber;
	private String SuccessfulLoginnumber;
	private String Updatenumber;
	public String getUpdatenumber() {
		return Updatenumber;
	}


	public void setUpdatenumber( String  updatenumber) {
		Updatenumber = updatenumber;
	}


	public String getSuccessfulLoginnumber() {
		return SuccessfulLoginnumber;
	}


	public void setSuccessfulLoginnumber( String  successfulLoginnumber) {
		SuccessfulLoginnumber = successfulLoginnumber;
	}
	private String email;
	private String Address;
	private int userdetailid;
	public int getUserdetailid() {
		return userdetailid;
	}


	public void setUserdetailid( int  userdetailid) {
		this.userdetailid = userdetailid;
	}


	public UserService getUserservice() {
		return userservice;
	}


	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getAttemptLogNumber() {
		return AttemptLogNumber;
	}


	public void setAttemptLogNumber( String  attemptLogNumber) {
		AttemptLogNumber = attemptLogNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public UserLogService getUserlogservice() {
		return userlogservice;
	}
	

	public void setUserlogservice(UserLogService userlogservice) {
		this.userlogservice = userlogservice;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getFailureAttemptNumber() {
		return FailureAttemptNumber;
	}

	public void setFailureAttemptNumber( String  failureAttemptNumber) {
		FailureAttemptNumber = failureAttemptNumber;
	}

	public void LogDetail(User userU) {
		

		System.out.println(FailureAttemptNumber);
		this.setName(userU.getFullName());
		this.setEmail(userU.getEmail());
		this.setImage(userU.getProfilePicture());
		this.setAddress(userU.getAddress());
		this.setRole(userU.getRole().toString());
		this.setPhonenumber(String.valueOf(userU.getPhoneNumber()));
		this.setUserdetailid(userU.getId());
		String tr= String.valueOf(( int) userlogservice.FailureAttemptForUser(userU,"Three failed Login attempts"));
		String s= String.valueOf((int )userlogservice.FailureAttemptForUser(userU," Successful Login")); 
		String Upd= String.valueOf((int ) userlogservice.FailureAttemptForUser(userU,"update profile picture"));
		this.setFailureAttemptNumber(tr);
		this.setSuccessfulLoginnumber(s);
		this.setUpdatenumber(Upd);

		System.out.println(image);
		
	}
	
	/*public String Fattempt() {
		User us = userservice.getUserbyID(userdetailid);
		
		
		return FailureAttemptNumber;
		
	}
	public String Sattmpt() {
		User us = userservice.getUserbyID(userdetailid);

		 String  s= ( String )userlogservice.FailureAttemptForUser(us," Successful Login"); 
return s;
	}
	public String Updattmpt() {
		User us = userservice.getUserbyID(userdetailid);
		 String  Upd= ( String ) userlogservice.FailureAttemptForUser(us,"update profile picture");
return Upd;
	}*/
	public User findbyid(int id) {
		
		User us = userservice.getUserbyID(id);
		 String  Upd= String.valueOf( userlogservice.FailureAttemptForUser(us,"update profile picture"));
		 String  s= String.valueOf(userlogservice.FailureAttemptForUser(us," Successful Login")); 
		this.setSuccessfulLoginnumber(s);
		this.setAttemptLogNumber(FailureAttemptNumber);
		this.setUpdatenumber(Upd);
		this.setU(us);
		return us;	
	}
	public List<User> findall(){
		
		List<User> lU =userservice.findAllUsers();
		
		return lU;
	}
	

}
