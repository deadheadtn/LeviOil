package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.model.UploadedFile;

import entity.Role;
import entity.User;
import entity.UserLog;
import services.UserLogService;
import services.UserService;

@ManagedBean
@SessionScoped
public class InscriptionBean {
	@EJB
	UserService userservice;
	@EJB
	UserLogService userlogservice;
	private UserLog log;
	private String username;
	private String FirstName;
	private String LastName;
	private String Password;
	private String RepPassword;
	private String SecurityQ;
	private String Answer;
	private int Phonenumber;
	private String Address;
	private String Email;
	private String Profilepic;
	private Part image;
	
	
	

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	private User user;

	public boolean validerEmail() {

		String mail = Email;
		Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher m = p.matcher(mail);
		if (m.find() && m.group().equals(mail)) {
			return true;
		} else {

			return false;
		}
	}

	public String DoInscription() throws IOException {
		String navigateTo = "null";
		String ph = BCrypt.hashpw(Password, BCrypt.gensalt(10));
		String ah = BCrypt.hashpw(Answer, BCrypt.gensalt(10));
		if (username.length() < 4 && FirstName.length() < 4 && LastName.length() < 4) {

			FacesContext.getCurrentInstance().addMessage("form:u", new FacesMessage("username invalide"));
		}
		
		else if (Password.length() < 8) {
			FacesContext.getCurrentInstance().addMessage("form:password",
					new FacesMessage("Password invalide minimum char  8"));
		} else if (!(RepPassword.equals(Password))) {

			FacesContext.getCurrentInstance().addMessage("form:Rpassword", new FacesMessage("Not equal to Password"));
		}

		else if (validerEmail() == false) {

			FacesContext.getCurrentInstance().addMessage("form:email", new FacesMessage("Mail Format invalid"));
		}

		else if (Answer.length() > 6) {
			FacesContext.getCurrentInstance().addMessage("form:answer", new FacesMessage("Answer is not Strong"));

		} else {

		String uuu = UUID.randomUUID().toString();
		
			Path from = Paths.get(image.getSubmittedFileName());
			Path to = Paths.get("C:/Users/admin/eclipse-workspace/LeviOil/LeviOil-web/src/main/webapp/resources/images/" + uuu + ".jpeg");
			CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
					StandardCopyOption.COPY_ATTRIBUTES };

			try {
				Files.copy(from, to, options);

			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
			 * try{ InputStream in=image.getInputStream();
			 * 
			 * File f=new File("C:/Users/admin/eclipse-workspace/profilepic/"+image.
			 * getSubmittedFileName()); f.createNewFile(); FileOutputStream out=new
			 * FileOutputStream(f);
			 * 
			 * byte[] buffer=new byte[1024]; int length;
			 * 
			 * while((length=in.read(buffer))>0){ out.write(buffer, 0, length); }
			 * 
			 * out.close(); in.close();
			 * 
			 * FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
			 * "path", f.getAbsolutePath()); upladed=true;
			 * 
			 * }catch(Exception e){ e.printStackTrace(System.out); }
			 */
			LocalDate sdate=LocalDate.now();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 	Date startdate = Date.valueOf(sdate);
		 	Date s=Date.valueOf(format.format(startdate));
			user = new User(username, ph, FirstName, LastName, Email,uuu+".jpeg", Phonenumber, Address, 1, Role.client, ah,SecurityQ,s);
			userservice.addUser(user);
			
			
			userlogservice.sendlog("User registred", user);
			
			log=new UserLog(s,"User registred", user);
			userlogservice.AddLog(log);

			navigateTo = "/pages/client/login?faces-redirect=true";

		}

		return navigateTo;

	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRepPassword() {
		return RepPassword;
	}

	public void setRepPassword(String repPassword) {
		RepPassword = repPassword;
	}

	public String getSecurityQ() {
		return SecurityQ;
	}

	public void setSecurityQ(String securityQ) {
		SecurityQ = securityQ;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public int getPhonenumber() {
		return Phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		Phonenumber = phonenumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getProfilepic() {
		return Profilepic;
	}

	public void setProfilepic(String profilepic) {
		Profilepic = profilepic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "No activity.", "What are you doing over there?"));
      
    }
 
    public String onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Welcome Back", "Well, that's a long coffee break!"));
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/pages/client/login?faces-redirect=true/";
    }
	

}
