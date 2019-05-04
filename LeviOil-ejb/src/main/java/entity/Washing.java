package entity;
import javax.persistence.CascadeType;
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
@Table( name = "Washing") 
public class Washing  implements Serializable{
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column(name="IdWashing") 
	private int IdWashing;
	@Column(name="Etat") 
	private String Etat;
	
	@Column(name="name") 
	private String name;
	


	public Washing(String etat, String name, GasStation gasStation) {
		super();
		Etat = etat;
		this.name = name;
		GasStation = gasStation;
	}








	public String getName() {
		return name;
	}








	public void setName(String name) {
		this.name = name;
	}








	


	@ManyToOne
	@JoinColumn(name="IdGas")
	GasStation GasStation;
	
	

	
	
	public GasStation getGasStation() {
		return GasStation;
	}








	public void setGasStation(GasStation gasStation) {
		GasStation = gasStation;
	}








	







	








	@OneToMany(mappedBy="washing",cascade= CascadeType.ALL)
	private Set<WashingMaterielWashing> washingMaterielWashing ;

	public int getIdWashing() {
		return IdWashing;
	}








	public void setIdWashing(int idWashing) {
		IdWashing = idWashing;
	}








	public String getEtat() {
		return Etat;
	}








	public void setEtat(String etat) {
		Etat = etat;
	}








	public Set<WashingMaterielWashing> getWashingMaterielWashing() {
		return washingMaterielWashing;
	}








	public void setWashingMaterielWashing(Set<WashingMaterielWashing> washingMaterielWashing) {
		this.washingMaterielWashing = washingMaterielWashing;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Washing other = (Washing) obj;
		if (Etat == null) {
			if (other.Etat != null)
				return false;
		} else if (!Etat.equals(other.Etat))
			return false;
		if (GasStation == null) {
			if (other.GasStation != null)
				return false;
		} else if (!GasStation.equals(other.GasStation))
			return false;
		if (IdWashing != other.IdWashing)
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (washingMaterielWashing == null) {
			if (other.washingMaterielWashing != null)
				return false;
		} else if (!washingMaterielWashing.equals(other.washingMaterielWashing))
			return false;
		return true;
	}








	public Washing(int idWashing,  GasStation gasStation ,String etat) {
		super();
		IdWashing = idWashing;
		Etat = etat;
		GasStation = gasStation;
	}








	public Washing(String etat, GasStation gasStation) {
		super();
		Etat = etat;
		GasStation = gasStation;
	}








	public Washing() {
		super();
	}

}
