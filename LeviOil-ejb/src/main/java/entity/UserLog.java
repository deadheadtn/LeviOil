package entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="userlog")
public class UserLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Date logDate;
	private String Log;
	  @ManyToOne
	 // @JoinColumn(name="id_user")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLog() {
		return Log;
	}

	public void setLog(String log) {
		Log = log;
	}

	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}

	public UserLog(Date logDate, String log, User user) {
		super();
		this.logDate = logDate;
		Log = log;
		this.user = user;
	}
	
	public UserLog() {
		
		super();
	}
	
	


}
