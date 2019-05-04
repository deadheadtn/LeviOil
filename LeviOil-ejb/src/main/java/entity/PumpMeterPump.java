package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table( name = "PumpMeterPump")
public class PumpMeterPump implements Serializable {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column(name="IdPumpMeterPump") 
	private int IdPumpMeterPump;
	@ManyToOne(cascade= {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
	@JoinColumn(name="IdPump")
	Pump pump;


		@ManyToOne(cascade= {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
		@JoinColumn(name="IdPumpMeter")
		PumpMeter pumpMeter;
		
		
		@Column(name="ConfirmPump")
	    private  Boolean ConfirmPump ;


		public int getIdPumpMeterPump() {
			return IdPumpMeterPump;
		}


		public void setIdPumpMeterPump(int idPumpMeterPump) {
			IdPumpMeterPump = idPumpMeterPump;
		}


		public Pump getPump() {
			return pump;
		}


		public void setPump(Pump pump) {
			this.pump = pump;
		}


		public PumpMeter getPumpMeter() {
			return pumpMeter;
		}


		public void setPumpMeter(PumpMeter pumpMeter) {
			this.pumpMeter = pumpMeter;
		}


		public Boolean getConfirmPump() {
			return ConfirmPump;
		}


		public void setConfirmPump(Boolean confirmPump) {
			ConfirmPump = confirmPump;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PumpMeterPump other = (PumpMeterPump) obj;
			if (ConfirmPump == null) {
				if (other.ConfirmPump != null)
					return false;
			} else if (!ConfirmPump.equals(other.ConfirmPump))
				return false;
			if (IdPumpMeterPump != other.IdPumpMeterPump)
				return false;
			if (pump == null) {
				if (other.pump != null)
					return false;
			} else if (!pump.equals(other.pump))
				return false;
			if (pumpMeter == null) {
				if (other.pumpMeter != null)
					return false;
			} else if (!pumpMeter.equals(other.pumpMeter))
				return false;
			return true;
		}


		public PumpMeterPump(Pump pump, PumpMeter pumpMeter, Boolean confirmPump) {
			super();
			this.pump = pump;
			this.pumpMeter = pumpMeter;
			ConfirmPump = confirmPump;
		}


		public PumpMeterPump(int idPumpMeterPump, Pump pump, PumpMeter pumpMeter, Boolean confirmPump) {
			super();
			IdPumpMeterPump = idPumpMeterPump;
			this.pump = pump;
			this.pumpMeter = pumpMeter;
			ConfirmPump = confirmPump;
		}


		public PumpMeterPump(Pump pump, PumpMeter pumpMeter) {
			super();
			this.pump = pump;
			this.pumpMeter = pumpMeter;
		}


		public PumpMeterPump() {
			super();
		}
		
		
		
}
