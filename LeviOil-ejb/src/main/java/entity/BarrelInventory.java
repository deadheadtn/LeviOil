package entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 */

@Entity
public class BarrelInventory implements Serializable {

    /**
     * Default constructor
     */
	
	private int test;
    public BarrelInventory() {
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
    private Integer barrelNumber;

    /**
     *
     */
    private float capacity;

    /**
     *
     */
    @OneToMany(mappedBy="barrelInventory")
    private List<Fuel> Fuel;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getBarrelNumber() {
        return barrelNumber;
    }

    public void setBarrelNumber(Integer barrelNumber) {
        this.barrelNumber = barrelNumber;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }
    
    
    public List<Fuel> getFuel() {
        return Fuel;
    }

    public void setFuel(List<Fuel> Fuel) {
        this.Fuel = Fuel;
    }



    

}
