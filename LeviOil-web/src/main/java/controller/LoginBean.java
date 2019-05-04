package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Role;
import entity.User;
import entity.UserLog;
import services.UserLogService;
import services.UserService;

@ManagedBean
@SessionScoped
public class LoginBean {
	private String login;
	private String password;
	private User user;
	private Boolean loggedIn;
	private UserLog log;
	private int i;
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@EJB
	UserService userservice;
	@EJB
	UserLogService userlogservice;
	
	public boolean checkpass(String  password) {
		if (BCrypt.checkpw(password, user.getPassword())==true) {
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String dologin() {
		
		String navigateTo = "null";
		user = userservice.getMembreByUsernamePassword(login, password);
		if(user.getState()==1) {
			if (checkpass(password)==true) {
				if (user.getRole() == Role.Admin ) {
					
					navigateTo = "UserDetail?faces-redirect=true";
					loggedIn = true;
					LocalDate sdate=LocalDate.now();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 	Date startdate = Date.valueOf(sdate);
				 	Date s=Date.valueOf(format.format(startdate));
					
					userlogservice.sendlog("Successful Login", user);
					log=new UserLog(s," Successful Login ", user);
					userlogservice.AddLog(log);
					
							}
				else if (user.getRole() != Role.Admin) {
					navigateTo = "NewFile?faces-redirect=true";
					loggedIn = true;
					LocalDate sdate=LocalDate.now();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 	Date startdate = Date.valueOf(sdate);
				 	Date s=Date.valueOf(format.format(startdate));
					
					userlogservice.sendlog("Successful Login", user);
					log=new UserLog(s," Successful Login ", user);
					userlogservice.AddLog(log);
					
					navigateTo = "NewFile?faces-redirect=true";
					loggedIn = true;
					
							}
				
		}
			else {
				if (i<3) {
					
					i=i+1;
					int s=3-i;
					FacesContext.getCurrentInstance().addMessage("form:btn",
							new FacesMessage("You Have Only "+s+" login Atttempts"));
				}
				
				if(i==3) {
				System.out.println(i);
			
			LocalDate sdate=LocalDate.now();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 	Date startdate = Date.valueOf(sdate);
		 	Date s=Date.valueOf(format.format(startdate));
			
			userlogservice.sendlog("Three failed Login attempts", user);
			log=new UserLog(s,"Three failed Login attempts", user);
			userlogservice.AddLog(log);
			userservice.DisactivateAccount(user.getId());
			navigateTo = "login?faces-redirect=true";
				}
			}
		
		
		
		}
		else {
			
			FacesContext.getCurrentInstance().addMessage("form:btn",
				new FacesMessage("Votre Compte est desactivé"));
		
	}
		/*if (i==3) {
			FacesContext.getCurrentInstance().addMessage("form:btn",
					new FacesMessage("Verifier votre login et mot de passe"));
			LocalDate sdate=LocalDate.now();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 	Date startdate = Date.valueOf(sdate);
		 	Date s=Date.valueOf(format.format(startdate));
			
			userlogservice.sendlog("Three failed Login attempts", user);
			log=new UserLog(s,"Three failed Login attempts", user);
			userlogservice.AddLog(log);
			userservice.DisactivateAccount(user.getId());
			
		}
		else {
			
		}*/
	
		
	return navigateTo;
		
	}
	
	
	
	public String dosignup() {
		
		return "sign2?faces-redirect=true";
		
	}
	
	
	


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public UserLog getLog() {
		return log;
	}

	public void setLog(UserLog log) {
		this.log = log;
	}

	public UserLogService getUserlogservice() {
		return userlogservice;
	}

	public void setUserlogservice(UserLogService userlogservice) {
		this.userlogservice = userlogservice;
	}
	
	
}
