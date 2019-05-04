package entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 */
@Entity
public class Fuel implements Serializable {

    /**
     * Default constructor
     */
    public Fuel() {
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
    private FuelTypeEnum type;

    /**
     *
     */
    @OneToMany(mappedBy="fuelType")
    private List<OilWell> oilWell;
    
    @ManyToOne
    private BarrelInventory barrelInventory;
    
    private float price;

    public Integer getId() {
        return id;
    }

    
    public FuelTypeEnum getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }


	public void setId(Integer id) {
		this.id = id;
	}


	public void setType(FuelTypeEnum type) {
		this.type = type;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public List<OilWell> getOilWell() {
		return oilWell;
	}


	public void setOilWell(List<OilWell> oilWell) {
		this.oilWell = oilWell;
	}


	public BarrelInventory getBarrelInventory() {
		return barrelInventory;
	}


	public void setBarrelInventory(BarrelInventory barrelInventory) {
		this.barrelInventory = barrelInventory;
	}


	@Override
	public String toString() {
		return "Fuel [id=" + id + ", type=" + type + ", price=" + price + "]";
	}

	
	
}
