package entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.ManyToOne;


@Entity
@Table( name = "Pump")
public class Pump implements Serializable {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column(name="IdPump") 
	private int IdPump;
	@Column(name="Name") 
	private String Name;
	
	@OneToMany(mappedBy="pump")
	private Set<PumpMeterPump> pumpMeterPump ;



	public Set<PumpMeterPump> getPumpMeterPump() {
		return pumpMeterPump;
	}
	public void setPumpMeterPump(Set<PumpMeterPump> pumpMeterPump) {
		this.pumpMeterPump = pumpMeterPump;
	}
	public int getIdPump() {
		return IdPump;
	}
	public void setIdPump(int idPump) {
		IdPump = idPump;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IdPump;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((pumpMeter == null) ? 0 : pumpMeter.hashCode());
		result = prime * result + ((pumpMeterPump == null) ? 0 : pumpMeterPump.hashCode());
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
		Pump other = (Pump) obj;
		if (IdPump != other.IdPump)
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (pumpMeter == null) {
			if (other.pumpMeter != null)
				return false;
		} else if (!pumpMeter.equals(other.pumpMeter))
			return false;
		if (pumpMeterPump == null) {
			if (other.pumpMeterPump != null)
				return false;
		} else if (!pumpMeterPump.equals(other.pumpMeterPump))
			return false;
		return true;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Set<PumpMeter> getPumpMeter() {
		return pumpMeter;
	}
	public void setPumpMeter(Set<PumpMeter> pumpMeter) {
		this.pumpMeter = pumpMeter;
	}

	@OneToMany(mappedBy="pump")
	private Set<PumpMeter> pumpMeter;

	public Pump(int idPump, String name) {
		super();
		IdPump = idPump;
		Name = name;
	}
	
	public Pump() {
	}
	
	public Pump(String name) {
		super();
		Name = name;
	}
	
}
