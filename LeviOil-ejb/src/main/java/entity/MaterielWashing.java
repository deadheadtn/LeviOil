package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table( name = "MaterielWashing")
public class MaterielWashing implements Serializable{
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column(name="IdMaterielWashing") 
	private int IdMaterielWashing;
	@Column(name="NameMateriel") 
	private String NameMateriel;
	
	
	@OneToMany(mappedBy="materielWashing",cascade=CascadeType.ALL)
	private Set<WashingMaterielWashing> washingMaterielWashing ;


	public Set<WashingMaterielWashing> getWashingMaterielWashing() {
		return washingMaterielWashing;
	}


	public void setWashingMaterielWashing(Set<WashingMaterielWashing> washingMaterielWashing) {
		this.washingMaterielWashing = washingMaterielWashing;
	}


	public int getIdMaterielWashing() {
		return IdMaterielWashing;
	}


	public void setIdMaterielWashing(int idMaterielWashing) {
		IdMaterielWashing = idMaterielWashing;
	}


	public String getNameMateriel() {
		return NameMateriel;
	}


	public void setNameMateriel(String nameMateriel) {
		NameMateriel = nameMateriel;
	}



	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IdMaterielWashing;
		result = prime * result + ((NameMateriel == null) ? 0 : NameMateriel.hashCode());
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
		MaterielWashing other = (MaterielWashing) obj;
		if (IdMaterielWashing != other.IdMaterielWashing)
			return false;
		if (NameMateriel == null) {
			if (other.NameMateriel != null)
				return false;
		} else if (!NameMateriel.equals(other.NameMateriel))
			return false;
		
		return true;
	}


	public MaterielWashing(int idMaterielWashing, String nameMateriel) {
		super();
		IdMaterielWashing = idMaterielWashing;
		NameMateriel = nameMateriel;
	}


	public MaterielWashing(String nameMateriel) {
		super();
		NameMateriel = nameMateriel;
	}


	public MaterielWashing() {
		super();
	}



}
