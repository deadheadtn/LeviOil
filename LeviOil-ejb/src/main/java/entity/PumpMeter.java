package entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "PumpMeter")

public class PumpMeter implements Serializable {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column(name="IdPumpMeter") 
	private int IdPumpMeter;
	@Column(name="QuantitéCarburant") 
	private int QuantitéCarburant;
	private TypeCarburant TypePumpMeter;
	@OneToMany(mappedBy="pumpMeter")
	private Set<PumpMeterPump> pumpMeterPump ;
	public Set<PumpMeterPump> getPumpMeterPump() {
		return pumpMeterPump;
	}

	public void setPumpMeterPump(Set<PumpMeterPump> pumpMeterPump) {
		this.pumpMeterPump = pumpMeterPump;
	}

	@ManyToOne
	@JoinColumn(name="IdPump")
	Pump pump;
	
	@ManyToOne
	@JoinColumn(name="IdGasStation")
	GasStation gasStation;


	public GasStation getGasStation() {
		return gasStation;
	}

	public void setGasStation(GasStation gasStation) {
		this.gasStation = gasStation;
	}

	@ManyToOne
	@JoinColumn(name="id")
	User Users;
	
	
	

	public User getUsers() {
		return Users;
	}

	public void setUsers(User users) {
		Users = users;
	}

	public int getIdPumpMeter() {
		return IdPumpMeter;
	}

	public void setIdPumpMeter(int idPumpMeter) {
		IdPumpMeter = idPumpMeter;
	}

	public int getQuantitéCarburant() {
		return QuantitéCarburant;
	}

	public void setQuantitéCarburant(int quantitéCarburant) {
		QuantitéCarburant = quantitéCarburant;
	}

	public TypeCarburant getTypePumpMeter() {
		return TypePumpMeter;
	}

	public void setTypePumpMeter(TypeCarburant typePumpMeter) {
		TypePumpMeter = typePumpMeter;
	}

	public Pump getPump() {
		return pump;
	}

	public void setPump(Pump pump) {
		this.pump = pump;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IdPumpMeter;
		result = prime * result + QuantitéCarburant;
		result = prime * result + ((TypePumpMeter == null) ? 0 : TypePumpMeter.hashCode());
		result = prime * result + ((Users == null) ? 0 : Users.hashCode());
		//result = prime * result + ((gasStation == null) ? 0 : gasStation.hashCode());
		result = prime * result + ((pump == null) ? 0 : pump.hashCode());
		//result = prime * result + ((pumpMeterPump == null) ? 0 : pumpMeterPump.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PumpMeter other = (PumpMeter) obj;
		if (IdPumpMeter != other.IdPumpMeter)
			return false;
		if (QuantitéCarburant != other.QuantitéCarburant)
			return false;
		if (TypePumpMeter != other.TypePumpMeter)
			return false;
		if (Users == null) {
			if (other.Users != null)
				return false;
		} else if (!Users.equals(other.Users))
			return false;
		if (gasStation == null) {
			if (other.gasStation != null)
				return false;
		} else if (!gasStation.equals(other.gasStation))
			return false;
		if (pump == null) {
			if (other.pump != null)
				return false;
		} else if (!pump.equals(other.pump))
			return false;
		if (pumpMeterPump == null) {
			if (other.pumpMeterPump != null)
				return false;
		} else if (!pumpMeterPump.equals(other.pumpMeterPump))
			return false;
		return true;
	}

	public PumpMeter(int idPumpMeter, int quantitéCarburant, TypeCarburant typePumpMeter, Pump pump,
			User users) {
		super();
		IdPumpMeter = idPumpMeter;
		QuantitéCarburant = quantitéCarburant;
		TypePumpMeter = typePumpMeter;
		this.pump = pump;
		Users = users;
	}

	public PumpMeter(int quantitéCarburant, TypeCarburant typePumpMeter, Pump pump, User users) {
		super();
		QuantitéCarburant = quantitéCarburant;
		TypePumpMeter = typePumpMeter;
		this.pump = pump;
		Users = users;
	}

	public PumpMeter() {
	}
	
	

	

}
