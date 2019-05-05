package entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name="fuel_order")
public class FuelOrder implements Serializable {

    /**
     * Default constructor
     */
    public FuelOrder() {
    }

    /**
     *
     */
    
    


    public FuelOrder(Integer quantity, Fuel fuel, FuelOrderStateEnum state, String destination, float fuelCost,
			float tranportationCost, float totalCost) {
		super();
		this.quantity = quantity;
		this.fuel = fuel;
		this.state = state;
		this.destination = destination;
		this.fuelCost = fuelCost;
		this.tranportationCost = tranportationCost;
		this.totalCost = totalCost;
	}

	/**
     *
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private Integer quantity;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /**
     *
     */
    @ManyToOne
    private GasStation gasStation;

    /**
     *
     */
    @ManyToOne
    private Fuel fuel;

    /**
     *
     */
    @Enumerated(EnumType.STRING)
    private FuelOrderStateEnum state;

    /**
     *
     */
    
    private String destination;

    /**
     *
     */
    private float fuelCost;

    /**
     *
     */
    private float tranportationCost;

    /**
     *
     */
    private float totalCost;

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

	@Override
	public String toString() {
		return "FuelOrder [id=" + id + ", quantity=" + quantity + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", fuel=" + fuel + ", state=" + state + ", destination=" + destination + ", fuelCost="
				+ fuelCost + ", tranportationCost=" + tranportationCost + ", totalCost=" + totalCost + "]";
	}

    
    
    


}
