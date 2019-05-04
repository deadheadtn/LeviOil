package entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
public class OilWell implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public OilWell() {
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
    
    private Integer capacity;

    /**
     *
     */
    @Column(name="drilling_time")
    private Integer drillingTime;
    
    @Column(name="extraction_time")
    private Integer extractionTime;

    /**
     *
     */
    private float cost;

    /**
     *
     */
    @Column(name="quantity_extracted")
    private Integer quantityExtracted;
    
    @Temporal(TemporalType.DATE)
    @Column(name="last_shipment")
    private Date lastShipment;
    
    @ManyToOne
    private Fuel fuelType;

    /**
     *
     */
    @Enumerated(EnumType.STRING)
    private OilWellStateEnum state;
    
    /**
     *
     */
    @ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE},fetch=FetchType.EAGER)
    @JoinTable(
        name = "oilwell_ressource", 
        joinColumns = { @JoinColumn(name = "oilwell_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "ressource_id") }
    )
    private List<Ressource> ressources;

    @OneToMany(mappedBy="oilwell")
    private Set<User> employes;
    
    public Integer getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public float getCost() {
        return cost;
    }

    public Integer getQuantityExtracted() {
        return quantityExtracted;
    }
    
    
    public OilWellStateEnum getState() {
        return state;
    }


	public void setId(Integer id) {
		this.id = id;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	
	public Integer getDrillingTime() {
		return drillingTime;
	}

	public void setDrillingTime(Integer drillingTime) {
		this.drillingTime = drillingTime;
	}

	public Integer getExtractionTime() {
		return extractionTime;
	}

	public void setExtractionTime(Integer extractionTime) {
		this.extractionTime = extractionTime;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setQuantityExtracted(Integer quantityExtracted) {
		this.quantityExtracted = quantityExtracted;
	}

	public void setState(OilWellStateEnum state) {
		this.state = state;
	}

	

	public Date getLastShipment() {
		return lastShipment;
	}

	public void setLastShipment(Date lastShipment) {
		this.lastShipment = lastShipment;
	}

	public Fuel getFuelType() {
		return fuelType;
	}

	public void setFuelType(Fuel fuelType) {
		this.fuelType = fuelType;
	}

	public List<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(List<Ressource> ressources) {
		this.ressources = ressources;
	}

	public Set<User> getEmployes() {
		return employes;
	}

	public void setEmployes(Set<User> employes) {
		this.employes = employes;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}
	@Override
	public String toString() {
		return "OilWell [id=" + id + ", registration=" + registration + ", capacity=" + capacity + ", drillingTime="
				+ drillingTime + ", extractionTime=" + extractionTime + ", cost=" + cost + ", quantityExtracted="
				+ quantityExtracted + ", lastShipment=" + lastShipment + ", state=" + state + "]";
	}

	public OilWell(String registration, Integer capacity, Integer drillingTime, Integer extractionTime, float cost,
			Integer quantityExtracted, OilWellStateEnum state) {
		super();
		this.registration = registration;
		this.capacity = capacity;
		this.drillingTime = drillingTime;
		this.extractionTime = extractionTime;
		this.cost = cost;
		this.quantityExtracted = quantityExtracted;
		this.state = state;
	}

	
	
    
}
