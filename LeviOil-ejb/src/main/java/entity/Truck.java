package entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 */
@Entity
public class Truck implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * Default constructor
     */
    public Truck() {
    }
    
    

    public Truck(String registration, String marque, TruckStateEnum state, float unitPrice) {
		super();
		this.registration = registration;
		this.marque = marque;
		this.state = state;
		this.unitPrice = unitPrice;
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
    private String registration;

    /**
     *
     */
    private String marque;

    /**
     *
     */
    @Enumerated(EnumType.STRING)
    private TruckStateEnum state;

    /**
     *
     */
    @Column(name="unit_price")
    private float unitPrice;

    /**
     *
     */
    @ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE},fetch=FetchType.EAGER)
    @JoinTable(
        name = "truck_part_requested", 
        joinColumns = { @JoinColumn(name = "truck_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "part_id") }
    )
    private Set <Part> partsRequested = new HashSet<>();

    
    public Integer getId() {
        return id;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMarque() {
        return marque;
    }

    
    public TruckStateEnum getState() {
        return state;
    }
    
    public float getUnitPrice() {
        return unitPrice;
    }

    
    public Set<Part> getPartsRequested() {
        return partsRequested;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setState(TruckStateEnum state) {
		this.state = state;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setPartsRequested(Set<Part> partsRequested) {
		this.partsRequested = partsRequested;
	}

	@Override
	public String toString() {
		return "Truck [id=" + id + ", registration=" + registration + ", marque=" + marque + ", state=" + state
				+ ", unitPrice=" + unitPrice + "]";
	}

	
    
    
}
