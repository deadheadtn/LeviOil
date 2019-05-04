package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "WashingMaterielWashing")
public class WashingMaterielWashing implements Serializable {

	// private washingmatPK washingmatpk;
	// private Washing wash;
	// private MaterielWashing mat;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Idwashingmaterielwashing")
	private int Idwashingmaterielwashing;
	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH })
	@JoinColumn(name = "IdWashing")
	Washing washing;

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH })
	@JoinColumn(name = "IdMaterielWashing")
	MaterielWashing materielWashing;

	public int getIdwashingmaterielwashing() {
		return Idwashingmaterielwashing;
	}

	public void setIdwashingmaterielwashing(int idwashingmaterielwashing) {
		Idwashingmaterielwashing = idwashingmaterielwashing;
	}

	public Washing getWashing() {
		return washing;
	}

	public void setWashing(Washing washing) {
		this.washing = washing;
	}

	public MaterielWashing getMaterielWashing() {
		return materielWashing;
	}

	public void setMaterielWashing(MaterielWashing materielWashing) {
		this.materielWashing = materielWashing;
	}

	@Column(name = "QuantiteMateriel")
	private Integer QuantiteMateriel;
	@Column(name = "ConfirmMateriel")
	private Boolean ConfirmMateriel;

	/*
	 * @EmbeddedId public washingmatPK getWashingmatpk() { return washingmatpk; }
	 */

	public Integer getQuantiteMateriel() {
		return QuantiteMateriel;
	}

	public void setQuantiteMateriel(Integer quantiteMateriel) {
		QuantiteMateriel = quantiteMateriel;
	}

	public Boolean getConfirmMateriel() {
		return ConfirmMateriel;
	}

	public void setConfirmMateriel(Boolean confirmMateriel) {
		ConfirmMateriel = confirmMateriel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WashingMaterielWashing other = (WashingMaterielWashing) obj;
		if (ConfirmMateriel == null) {
			if (other.ConfirmMateriel != null)
				return false;
		} else if (!ConfirmMateriel.equals(other.ConfirmMateriel))
			return false;
		if (QuantiteMateriel == null) {
			if (other.QuantiteMateriel != null)
				return false;
		} else if (!QuantiteMateriel.equals(other.QuantiteMateriel))
			return false;
		if (materielWashing == null) {
			if (other.materielWashing != null)
				return false;
		} else if (!materielWashing.equals(other.materielWashing))
			return false;
		if (washing == null) {
			if (other.washing != null)
				return false;
		} else if (!washing.equals(other.washing))
			return false;
		return true;
	}

	public WashingMaterielWashing(int idwashingmaterielwashing, Washing washing, MaterielWashing materielWashing,
			Integer quantiteMateriel, Boolean confirmMateriel) {
		super();
		Idwashingmaterielwashing = idwashingmaterielwashing;
		this.washing = washing;
		this.materielWashing = materielWashing;
		QuantiteMateriel = quantiteMateriel;
		ConfirmMateriel = confirmMateriel;
	}

	public WashingMaterielWashing(int idwashingmaterielwashing, Integer quantiteMateriel) {
		super();
		Idwashingmaterielwashing = idwashingmaterielwashing;
		QuantiteMateriel = quantiteMateriel;
	}

	public WashingMaterielWashing(int idwashingmaterielwashing, Integer quantiteMateriel, Boolean confirmMateriel) {
		super();
		Idwashingmaterielwashing = idwashingmaterielwashing;
		QuantiteMateriel = quantiteMateriel;
		ConfirmMateriel = confirmMateriel;
	}

	public WashingMaterielWashing() {
	}

	public WashingMaterielWashing(Washing washing, MaterielWashing materielWashing) {
		super();
		this.washing = washing;
		this.materielWashing = materielWashing;

	}

	public WashingMaterielWashing(int idwashingmaterielwashing) {
		super();
		Idwashingmaterielwashing = idwashingmaterielwashing;
	}

}
