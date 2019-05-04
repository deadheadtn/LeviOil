package services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.util.Calendar.*;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Complaint;
import entity.GasStation;
import entity.Role;
import entity.User;
import entity.Complaint.Status;
import interfaces.ComplaintService;

@Stateless
@LocalBean
public class ComplaintServiceImpl implements ComplaintService {

	// badelha GestionUser-ejb b Complaint - ejb wela hkeya haka
	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;

	@Override
	public void addComplaint(Complaint complaint) {
		em.persist(complaint);
	}

	// Check user or not
	@Override
	public Complaint getComplaint(int id) {
		return em.find(Complaint.class, id);
	}

	// Check used or not
	@Override
	public List<Complaint> getAllCustomerComplaintByManager(User manager) {
		// thabet fe customer_complaint_category or customerComp
		TypedQuery<Complaint> query = em.createQuery(
				"select c from Complaint c where c.category=:category and c.handler=:manager", Complaint.class);
		query.setParameter("category", Complaint.Category.CUSTOMER);
		query.setParameter("manager", manager);
		return query.getResultList();
	}

	// maybe change name
	@Override
	public List<Complaint> getComplaintsByComplainant(User complainant) {
		// chouf ki tna7i star 59 te5dem walle
		complainant = em.find(User.class, complainant.getId());
		TypedQuery<Complaint> query = em.createQuery(
				"select c from Complaint c where c.complainant=:complainant and c.removed=:removed", Complaint.class);
		query.setParameter("complainant", complainant);
		query.setParameter("removed", false);
		return query.getResultList();
	}

	@Override
	public List<Complaint> getEmployeesComplaints() {
		// thabet fe customer_complaint_category or customerComp
		TypedQuery<Complaint> query = em.createQuery("select c from Complaint c where c.category=:category",
				Complaint.class);
		query.setParameter("category", "EMPLOYEE");
		return query.getResultList();
	}

	@Override
	public List<Complaint> getHandlerComplaints(int handlerId) {
		TypedQuery<Complaint> query = em.createQuery("select c from Complaint c where c.handler=:handler",
				Complaint.class);
		query.setParameter("handler", handlerId);
		return query.getResultList();
	}

	/*
	 * @Override public List<Complaint> getSenderComplaints(int senderId) {
	 * TypedQuery<Complaint> query =
	 * em.createQuery("select c from Complaint c where c.sender=:sender",
	 * Complaint.class); query.setParameter("sender", senderId); return
	 * query.getResultList(); }
	 */

	// yomken boolean blaset void heya wel delete wel fire employee
	@Override
	public void updateComplaintStatus(int id, Status status) {
		Complaint complaint = em.find(Complaint.class, id);
		complaint.setStatus(status);
		java.util.Date date = new Date();
		Object updatedAt = new java.sql.Timestamp(date.getTime());
		complaint.setUpdatedAt((Date) updatedAt);

		em.merge(complaint);
	}

	@Override
	public void setComplaintAsRemoved(int id, String comment) {
		Complaint complaint = em.find(Complaint.class, id);
		complaint.setComment(comment);
		complaint.setRemoved(true);
		em.merge(complaint);
	}

	@Override
	public List<Complaint> getAllEmployeeManagerComplaint() {
		// zid pompiste fel or w chouf famech o5rin tzidhom, chouf tzidch complaint men
		// 5adem lel big company
		TypedQuery<Complaint> query = em.createQuery(
				"select c from Complaint c where c.complainant.role=:worker or c.complainant.role=:Gerant",
				Complaint.class);
		query.setParameter("worker", Role.worker);
		query.setParameter("Gerant", Role.Gerant);
		return query.getResultList();
	}

	@Override
	public List<GasStation> getAllGasStation() {
		// this is just for testing purposes
		List<GasStation> gasStations = em.createQuery("SELECT gs FROM GasStation gs").getResultList();
		return gasStations;
	}
	@Override
	public User getManagerByIdGasStation(int idGasStation)
	{
		GasStation gasStation = em.find(GasStation.class, idGasStation);
		return gasStation.getManager();
	}
	@Override
	public List<User> getAllGasStationEmployee(int idGasStation) {
		// this is just for testing purposes
		// List<GasStation> gasStations = em.createQuery("SELECT u FROM users u where
		// u.gas_station=").getResultList();
		// List<User> employees = em.createQuery("SELECT u FROM user
		// u").getResultList();
		// return employees;
		GasStation gasStation = em.find(GasStation.class, idGasStation);
		TypedQuery<User> query = em.createQuery("select u from user u where u.gasStationWorker=:idGasSation",
				User.class);
		query.setParameter("idGasSation", gasStation);
		return query.getResultList();
	}

	@Override
	public User getUser(int id) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM user u where id=:id", User.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public GasStation getGasStation(int id) {
		return em.find(GasStation.class, id);
	}

	/////////////////////// hethi fel integration a3mlha fe service
	/////////////////////// user//////////////////////
	@Override
	public User getAdmin() {
		TypedQuery<User> query = em.createQuery("Select u From user u where u.role=:adminRole", User.class);
		query.setParameter("adminRole", Role.Admin);
		return query.getSingleResult();
	}

	@Override
	public Map<String, Float> employeeComplaintStats(int employeeId) {
		User employee = em.find(User.class, employeeId);
		Map<String, Float> stats = new HashMap<>();
		float numberOfComplaints = employee.getComplaintsFiledAgainst().size();
		stats.put("numberOfComplaints", numberOfComplaints);
		int differenceMonths = getDiffMonths(employee.getCreatedAt(), new Date());
		float complaintPerMonth = (int) Math.round(numberOfComplaints / differenceMonths);
		stats.put("complaintPerMonth", complaintPerMonth);
		float complaintPerYear;
		if (differenceMonths < 12)
			complaintPerYear = numberOfComplaints;
		else
			complaintPerYear = numberOfComplaints / ((float) differenceMonths / 12);
		stats.put("complaintPerYear", complaintPerYear);
		return stats;
	}

	public static int getDiffMonths(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = b.get(MONTH) - a.get(MONTH);
		return diff;
	}

	/*
	 * public static int getDiffYear(Date first, Date last) { Calendar a =
	 * getCalendar(first); Calendar b = getCalendar(last); int diff = b.get(YEAR) -
	 * a.get(YEAR); if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH)
	 * && a.get(DATE) > b.get(DATE))) { diff--; } //if(diff == 0) //diff==12;
	 * 
	 * 
	 * return diff; }
	 */

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	// chouf t3adi int or objects
	/*
	 * @Override public void assignComplaintToHandler(int complaintId, int
	 * handlerId) { User handler = em.find(User.class, handlerId); Complaint
	 * complaint =em.find(Complaint.class,complaintId);
	 * complaint.setHandler(handler); em.merge(complaint); }
	 */
	//////////////// zeyda////////////////:

	////////////////////////////////////
	@Override
	public List<Complaint> getAllComplaint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComplaint(Complaint complaint) {
		complaint = em.find(Complaint.class, complaint.getId());
		em.remove(complaint);
	}

}
