package controller;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entity.GasStation;
import interfaces.GasStationIservice;
import services.GasStationService;





@ManagedBean(name = "gasservice")
@SessionScoped
public class GasStationController {
	
@EJB
GasStationIservice gasStationIservice ;

private String nameGas ; 
private String streetGas ;
private List<GasStation> gasStations ;
public String getNameGas() {
	return nameGas;
}

@PostConstruct
public void init() {
	System.out.println("innnnnnniiiiiiiitttttttt");
	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+gasStationIservice.findAllGasStations());
}

public void setNameGas(String nameGas) {
	this.nameGas = nameGas;
}

public String getStreetGas() {
	return streetGas;
}

public void setStreetGas(String streetGas) {
	this.streetGas = streetGas;
}

public List<GasStation> getGasStations() {
	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa"+gasStationIservice.findAllGasStations());
	this.gasStations = gasStationIservice.findAllGasStations();
	return gasStations;
}

public void setGasStations(List<GasStation> gasStations) {
	this.gasStations = gasStations;
}


public GasStationController(String nameGas, String streetGas) {
	super();
	this.nameGas = nameGas;
	this.streetGas = streetGas;
}

public GasStationController() {
	
	
}

}
