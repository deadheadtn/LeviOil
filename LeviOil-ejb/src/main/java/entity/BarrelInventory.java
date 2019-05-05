package entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name="barrel_inventory")
public class BarrelInventory implements Serializable {

    /**
     * Default constructor
     */
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
    @ManyToOne
    private Fuel fuel;
    
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
    
    
    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    

}
