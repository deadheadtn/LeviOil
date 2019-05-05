package entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity(name="truck_lease")
public class TruckLease implements Serializable {

    /**
     * Default constructor
     */
    public TruckLease() {
    }

    /**
     *
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryStartedAt;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryEndedAt;

    /**
     *
     */
    @ManyToOne
    private Truck truck;

    /**
     *
     */
    @ManyToOne
    private User transporter;

    /**
     *
     */
    @OneToOne
    private FuelOrder fuelOrder;

    
    public Integer getId() {
        return id;
    }

    
    public Date getDeliveryStartedAt() {
        return deliveryStartedAt;
    }

    
    public Date getDeliveryEndedAt() {
        return deliveryEndedAt;
    }

    
    public Truck getTruck() {
        return truck;
    }

    
    public User getTransporter() {
        return transporter;
    }

    
    public FuelOrder getFuelOrder() {
        return fuelOrder;
    }


	public void setId(Integer id) {
		this.id = id;
	}


	public void setDeliveryStartedAt(Date deliveryStartedAt) {
		this.deliveryStartedAt = deliveryStartedAt;
	}


	public void setDeliveryEndedAt(Date deliveryEndedAt) {
		this.deliveryEndedAt = deliveryEndedAt;
	}


	public void setTruck(Truck truck) {
		this.truck = truck;
	}


	public void setTransporter(User transporter) {
		this.transporter = transporter;
	}


	public void setFuelOrder(FuelOrder fuelOrder) {
		this.fuelOrder = fuelOrder;
	}


    
}
