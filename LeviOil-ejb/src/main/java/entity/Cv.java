package entity;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cv implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String CV;
private int etat;
@OneToOne
private User employee;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getEtat() {
	return etat;
}
public void setEtat(int etat) {
	this.etat = etat;
}
public String getCV() {
	return CV;
}
public void setCV(String CV) {
	this.CV = CV;
}
public User getEmployee() {
	return employee;
}

public void setEmployee(User employee) {
	this.employee = employee;
}
public Cv(int id, int etat, User employee, String CV) {
	super();
	this.id = id;
	this.etat = etat;
	this.employee = employee;
	this.CV =CV;
}
public Cv() {
	super();
}
public Cv(int id, String cV, int etat) {
	super();
	this.id = id;
	CV = cV;
	this.etat = etat;
}
@Override
public String toString() {
	return "Cv [id=" + id + ", CV=" + CV + ", etat=" + etat + "]";
}
public Cv(String cV) {
	super();
	CV = cV;
	this.etat = 0;
}

}
