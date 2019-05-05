package controller;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.Fuel;
import entity.FuelOrder;
import entity.FuelOrderStateEnum;
import entity.GasStation;
import entity.User;
import interfaces.FuelOrderService;
import interfaces.FuelService;
import services.GasStationService;


@ManagedBean(name = "fuelOrderBean") 
@SessionScoped
public class FuelOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer quantity;
	private Date createdAt;
    private Date updatedAt;
    private GasStation gasStation;
    private Fuel fuel;
    private FuelOrderStateEnum state;
    private String destination;
    private float fuelCost;
    private float tranportationCost;
    private float totalCost;
    private List<FuelOrder> FuelOrders;
    private List<Fuel> Fuels;
    private Integer idToBeUpdated;
    private FuelOrderStateEnum waiting = FuelOrderStateEnum.Waiting;
    private FuelOrderStateEnum inProgress = FuelOrderStateEnum.InProgress;
    private FuelOrderStateEnum arrived = FuelOrderStateEnum.Arrived;
    
	@EJB
	FuelOrderService fuelOrderService; 
	
	@EJB
	FuelService fuelService; 
	
	@EJB
	GasStationService gasStationService; 

	public FuelOrderBean() {}
 
	@PostConstruct
	public void init() {
	    fuel = new Fuel();
	}

	public String addFuelOrder(FuelOrderBean fuelOrderBean )
	{	
		String navigate="null";
		User user = new User();
		user.setId(1);
		GasStation gasStation = gasStationService.getGasStationByUser(user.getId());
		System.out.println("aaaaaaaaaaaa"+gasStation.toString());
		if (fuelOrderBean.getQuantity()>fuelService.getFuelQuantity(fuel))
		{
			System.out.println("D5al!!!!!!!!!!!!!!!!!!!!");
			System.out.println(fuelService.getFuelQuantity(fuel));
			FacesContext.getCurrentInstance().addMessage("btn", new FacesMessage("Quantity is too High, enter less than "+fuelService.getFuelQuantity(fuel)));
		}
		else {
		FuelOrder fuelOrder = new FuelOrder();
		fuelOrder.setCreatedAt(new Date());
		fuelOrder.setGasStation(gasStation);
		fuelOrder.setState(FuelOrderStateEnum.Waiting);
		fuelOrder.setDestination(gasStation.getAddress());
		fuelOrder.setFuelCost(fuelService.calculateFuelCost(this.getFuel(),this.getQuantity()));
		fuelOrder.setQuantity(this.getQuantity());
		fuelOrder.setFuel(this.getFuel());
		fuelOrder.setTranportationCost(0);
		fuelOrder.setTotalCost(fuelOrder.getFuelCost()+fuelOrder.getTranportationCost());
	
		fuelOrderService.add(fuelOrder);
		navigate="/pages/client/listFuelOrder";
		}
		return navigate;
		
	}
	public void deleteFuelOrder(Integer fuelOrderId) {
		fuelOrderService.delete(FuelOrder.class, fuelOrderId);
	}
	
	public String updateFormFuelOrder(FuelOrder fuelOrder) { 
		
		this.setIdToBeUpdated(fuelOrder.getId());
		this.setFuel(fuelOrder.getFuel());
		this.setQuantity(fuelOrder.getQuantity());

		return "/pages/client/addFuelOrder?faces-redirect=true";
	}
	
	public String updateFuelOrder()
	{

		FuelOrder fuelOrder = new FuelOrder();
		fuelOrder=(FuelOrder) fuelOrderService.findById(FuelOrder.class, this.idToBeUpdated);
		fuelOrder.setCreatedAt(new Date());
		fuelOrder.setFuelCost(fuelService.calculateFuelCost(this.getFuel(),this.getQuantity()));
		fuelOrder.setQuantity(this.getQuantity());
		fuelOrder.setFuel(this.getFuel());
		fuelOrder.setTranportationCost(0);
		fuelOrder.setTotalCost(fuelOrder.getFuelCost()+fuelOrder.getTranportationCost());
	
		fuelOrderService.updateFuelOrder(fuelOrder);
		
		return "/pages/client/listFuelOrder?faces-redirect=true";
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public GasStation getGasStation() {
		return gasStation;
	}


	public void setGasStation(GasStation gasStation) {
		this.gasStation = gasStation;
	}


	public Fuel getFuel() {
		return fuel;
	}


	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}


	public FuelOrderStateEnum getState() {
		return state;
	}


	public void setState(FuelOrderStateEnum state) {
		this.state = state;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public float getFuelCost() {
		return fuelCost;
	}


	public void setFuelCost(float fuelCost) {
		this.fuelCost = fuelCost;
	}


	public float getTranportationCost() {
		return tranportationCost;
	}


	public void setTranportationCost(float tranportationCost) {
		this.tranportationCost = tranportationCost;
	}


	public float getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}


	@SuppressWarnings("unchecked")
	public List<FuelOrder> getFuelOrders() {
		System.out.println(fuelOrderService.findAll(FuelOrder.class).toString());
		return (List<FuelOrder>) (Object) fuelOrderService.findAll(FuelOrder.class);
	}


	public void setFuelOrders(List<FuelOrder> fuelOrders) {
		FuelOrders = fuelOrders;
	}


	public List<Fuel> getFuels() {
		return fuelService.getFuels();
	}


	public void setFuels(List<Fuel> fuels) {
		Fuels = fuels;
	}

	public FuelOrderStateEnum getWaiting() {
		return waiting;
	}

	public void setWaiting(FuelOrderStateEnum waiting) {
		this.waiting = waiting;
	}

	public FuelOrderStateEnum getInProgress() {
		return inProgress;
	}

	public void setInProgress(FuelOrderStateEnum inProgress) {
		this.inProgress = inProgress;
	}

	public FuelOrderStateEnum getArrived() {
		return arrived;
	}

	public void setArrived(FuelOrderStateEnum arrived) {
		this.arrived = arrived;
	}

	public Integer getIdToBeUpdated() {
		return idToBeUpdated;
	}

	public void setIdToBeUpdated(Integer idToBeUpdated) {
		this.idToBeUpdated = idToBeUpdated;
	}
	
	
	
}
