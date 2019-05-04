package entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 */
@Entity(name="user")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    private  String login;
    private  String password;
    private String firstName;
    private  String lastName;
    private  String email;
    private  String profilePicture;
    private  int phoneNumber;    
    private  String address;
    @OneToMany(mappedBy="user")
    private Set<UserLog> log;
    
    private  String security_question;
    private String Answer;
    
    
    public Set<UserLog> getLog() {
		return log;
	}

	public void setLog(Set<UserLog> log) {
		this.log = log;
	}

	// Added By Amir
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    @OneToMany(mappedBy = "complainant")
    private List<Complaint> sentComplaints;
    @OneToMany(mappedBy = "handler")
    private List<Complaint> assignedComplaints;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "complainee")
    private List<Complaint> complaintsFiledAgainst;    
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "manager")
    private GasStation gasStation;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private GasStation gasStationWorker;
    
   
    
    public List<Complaint> getSentComplaints() {
    	return sentComplaints;
    	}
    	
    	public List<Complaint> getAssignedComplaints() {
    	return assignedComplaints;
    	}
    	
    	public List<Complaint> getComplaintsFiledAgainst() {
    	return complaintsFiledAgainst;
    	}
    	
    	public void setComplaintsFiledAgainst(List<Complaint> complaintsFiledAgainst) {
    	this.complaintsFiledAgainst = complaintsFiledAgainst;
    	}
    
    	
    	public void setSentComplaints(List<Complaint> sentComplaints) {
    		this.sentComplaints = sentComplaints;
    		}
    		
    		public void setAssignedComplaints(List<Complaint> assignedComplaints) {
    		this.assignedComplaints = assignedComplaints;
    		}
    		
    		public String getFullName() {
    			return this.getFirstName() + " " + this.getLastName();
    			}
    		
    		public GasStation getGasStation() {
    			return gasStation;
    			}
    			
    			public Date getCreatedAt() {
    			return createdAt;
    			}
    			
    			public void setCreatedAt(Date createdAt) {
    			this.createdAt = createdAt;
    			}
    			
    			public void setGasStation(GasStation gasStation) {
    			this.gasStation = gasStation;
    			}
    			
    			public GasStation getGasStationWorker() {
    			return gasStationWorker;
    			}
    			
    			public void setGasStationWorker(GasStation gasStationWorker) {
    			this.gasStationWorker = gasStationWorker;
    			}
    			
    			
    
    //////////
    
    
    
    
	public String getSecurity_question() {
		return security_question;
	}
	public void setSecurity_question(String security_question) {
		this.security_question = security_question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Complaint> Complaints;
    @OneToOne(cascade = CascadeType.ALL)
    private Cv cv;
    @Enumerated(EnumType.STRING)
    private  Role role;
    public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public OilWell getOilwell() {
		return oilwell;
	}
	public void setOilwell(OilWell oilwell) {
		this.oilwell = oilwell;
	}
	public Contrat getContrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	public Set<FuelOrder> getFuelOrders() {
		return FuelOrders;
	}
	public void setFuelOrders(Set<FuelOrder> fuelOrders) {
		FuelOrders = fuelOrders;
	}
	public Set<BalanceSheet> getBalancesheets() {
		return balancesheets;
	}
	public void setBalancesheets(Set<BalanceSheet> balancesheets) {
		this.balancesheets = balancesheets;
	}
	public Set<TruckLease> getTruckLeases() {
		return TruckLeases;
	}
	public void setTruckLeases(Set<TruckLease> truckLeases) {
		TruckLeases = truckLeases;
	}
	private int state;
    public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@ManyToOne
    OilWell oilwell;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Contrat contrat;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<FuelOrder> FuelOrders;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<BalanceSheet> balancesheets;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TruckLease> TruckLeases;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Set<Complaint> getComplaints() {
		return Complaints;
	}
	public void setComplaints(Set<Complaint> complaints) {
		Complaints = complaints;
	}
	public Cv getCv() {
		return cv;
	}
	public void setCv(Cv cv) {
		this.cv = cv;
	}
	public User(int id, String login, String password, String firstName, String lastName, String email,
			String profilePicture, int phoneNumber, String address,
			Set<Complaint> complaints, Cv cv) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profilePicture = profilePicture;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.Complaints = complaints;
		this.cv = cv;
	}
	public User( String login, String password, String firstName, String lastName, String email,
			String profilePicture, int phoneNumber, String address, int state,Role role) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profilePicture = profilePicture;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.state = state;
		this.role=role;
	}
	public User( String login, String password, String firstName, String lastName, String email,
			String profilePicture, int phoneNumber, String address, int state,Role role,String answer,String security_question,Date crDate) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profilePicture = profilePicture;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.state = state;
		this.role=role;
		this.Answer=answer;
		this.security_question=security_question;
		this.createdAt=crDate;
	}
	public User() {
		super();
	}
   



}