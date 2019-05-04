package entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class BalanceSheet implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date Date;
	private Type2 Type;
	private User employee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public Type2 getType() {
		return Type;
	}
	public void setType(Type2 type) {
		Type = type;
	}
	public User getEmployee() {
		return employee;
	}
	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public BalanceSheet(int id, java.util.Date date, Type2 type, User employee) {
		super();
		this.id = id;
		Date = date;
		Type = type;
		this.employee = employee;
	}
	public BalanceSheet() {
		super();
	}
	
	

}
