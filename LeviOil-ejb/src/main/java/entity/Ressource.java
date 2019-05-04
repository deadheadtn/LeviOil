package entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
/**
 *
 */
@Entity
public class Ressource implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Ressource() {
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
    @Enumerated(EnumType.STRING)
    private RessourceTypeEnum type;

    /**
     *
     */
    private String description;
    
    private Integer quantity;
    
    private Integer efficiency;
    
    @ManyToMany(mappedBy="ressources")
    private List<OilWell> oilWells;
   
    public int getId() {
        return id;
    }

    
    public RessourceTypeEnum getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    
   

	public void setId(Integer id) {
		this.id = id;
	}


	public void setType(RessourceTypeEnum type) {
		this.type = type;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getEfficiency() {
		return efficiency;
	}


	public void setEfficiency(Integer efficiency) {
		this.efficiency = efficiency;
	}


	public List<OilWell> getOilWells() {
		return oilWells;
	}


	public void setOilWells(List<OilWell> oilWells) {
		this.oilWells = oilWells;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	
    
    
}
