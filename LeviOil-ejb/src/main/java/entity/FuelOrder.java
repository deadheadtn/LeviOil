package entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FuelOrder implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String nn;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNn() {
	return nn;
}
public void setNn(String nn) {
	this.nn = nn;
}
public FuelOrder(int id, String nn) {
	super();
	this.id = id;
	this.nn = nn;
}
public FuelOrder() {
	super();
}


}
