package entity;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity(name="contrat")
public class Contrat implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private float salary;

	private Date startdate;

	private Date enddate;
	@Enumerated(EnumType.STRING)
	private Type type;
	@OneToOne
	private User employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Contrat(int id, float salary, Date startdate, Date enddate, Type type, User employee) {
		super();
		this.id = id;
		this.salary = salary;
		this.startdate = startdate;
		this.enddate = enddate;
		this.type = type;
		this.employee = employee;
	}
	public Contrat( float salary, Date startdate, Date enddate, Type type, User employee) {
		super();
		this.salary = salary;
		this.startdate = startdate;
		this.enddate = enddate;
		this.type = type;
		this.employee = employee;
	}
	public Contrat() {
		super();
	}
	

	

}
