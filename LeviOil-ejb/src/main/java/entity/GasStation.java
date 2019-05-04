package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = "GasStation")
public class GasStation implements Serializable {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column(name="IdGas") 
	private int IdGas;
	private String name ;
	private float latitude ;
	private float longitude;
	private String image;
	//Added by Amir
	@OneToOne(fetch = FetchType.EAGER)
	private User manager;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="gasStationWorker") 
	private List<User> workers;

	
	
	///////
	
	@OneToMany(mappedBy="gasStation",cascade= {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
	private List<PumpMeter> pumpMeters;

	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	private String street;
	
	

@OneToMany(mappedBy="GasStation",cascade= {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
	private Set<Washing> Washing;	
	
@ManyToOne
@JoinColumn(name="id")
User User;


	
	public Set<Washing> getWashing() {
	return Washing;
}
public void setWashing(Set<Washing> washing) {
	Washing = washing;
}
public User getUser() {
	return User;
}
public void setUser(User user) {
	User = user;
}
	public int getIdGas() {
		return IdGas;
	}
	public void setIdGas(int idGas) {
		IdGas = idGas;
	}
	public GasStation(String name, String image, String street, User user) {
		super();
		this.name = name;
		this.image = image;
		this.street = street;
		User = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	

	

	
	public GasStation(String name, String image, String street) {
		super();
		this.name = name;
		this.image = image;
		this.street = street;
	}
	public GasStation() {
		super();
	}
	public GasStation( String name, float latitude, float longitude, String image, String street, User iduser) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image = image;
		this.street = street;
		this.User=iduser; 
	}
	public GasStation(int idGas, String name, float latitude, float longitude, String image, String street,
			User user) {
		super();
		IdGas = idGas;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image = image;
		this.street = street;
		User = user;
	}
	
	public GasStation(int idGas, String name, String image, String street) {
		super();
		IdGas = idGas;
		this.name = name;
		this.image = image;
		this.street = street;
	}
	
	
	public List<PumpMeter> getPumpMeters() {
		return pumpMeters;
	}
	public void setPumpMeters(List<PumpMeter> pumpMeters) {
		this.pumpMeters = pumpMeters;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GasStation other = (GasStation) obj;
		if (IdGas != other.IdGas)
			return false;
		if (User == null) {
			if (other.User != null)
				return false;
		} else if (!User.equals(other.User))
			return false;
		if (Washing == null) {
			if (other.Washing != null)
				return false;
		} else if (!Washing.equals(other.Washing))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (Float.floatToIntBits(latitude) != Float.floatToIntBits(other.latitude))
			return false;
		if (Float.floatToIntBits(longitude) != Float.floatToIntBits(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pumpMeters == null) {
			if (other.pumpMeters != null)
				return false;
		} else if (!pumpMeters.equals(other.pumpMeters))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	public GasStation(int idGas, String name, float latitude, float longitude, String image,
			List<PumpMeter> pumpMeters, String street, Set<Washing> washing,
			User user) {
		super();
		IdGas = idGas;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image = image;
		this.pumpMeters = pumpMeters;
		this.street = street;
		Washing = washing;
		User = user;
	}
	public GasStation(String name, float latitude, float longitude, String image, List<PumpMeter> pumpMeters,
			String street, Set<Washing> washing, User user) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image = image;
		this.pumpMeters = pumpMeters;
		this.street = street;
		Washing = washing;
		User = user;
	}
	
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public List<User> getWorkers() {
		return workers;
	}
	public void setWorkers(List<User> workers) {
		this.workers = workers;
	}
	
	
	
	
}
