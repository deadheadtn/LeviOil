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
public class Part implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Part() {
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
    private PartTypeEnum type;

    /**
     *
     */
    @ManyToMany(mappedBy="partsRequested")
    private List<Truck> trucks;
    
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    
    public PartTypeEnum getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }


	public void setId(Integer id) {
		this.id = id;
	}


	public void setType(PartTypeEnum type) {
		this.type = type;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Part [id=" + id + ", type=" + type + ",quantity=" + quantity + "]";
	}

	
    
}
