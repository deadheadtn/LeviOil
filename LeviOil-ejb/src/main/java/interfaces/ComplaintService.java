package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entity.Complaint;
import entity.Complaint.Status;
import entity.GasStation;
import entity.User;

@Remote
public interface ComplaintService {
	
	public void addComplaint(Complaint complaint);

	public Complaint getComplaint(int id);

	public List<Complaint> getAllComplaint();

	public void deleteComplaint(Complaint complaint);

	public List<Complaint> getAllCustomerComplaintByManager(User manager); 

	public List<Complaint> getEmployeesComplaints();

	public void updateComplaintStatus(int id, Status status);

	public List<Complaint> getHandlerComplaints(int handlerId);

	public  Map<String, Float> employeeComplaintStats(int employeeId);

	public List<GasStation> getAllGasStation();

	public List<User> getAllGasStationEmployee(int idGasStation);

	public List<Complaint> getComplaintsByComplainant(User complainant);
	
	public List<Complaint> getAllEmployeeManagerComplaint();

	public void setComplaintAsRemoved(int id, String comment);

	public User getAdmin();
	
	public User getUser(int id);

	public GasStation getGasStation(int id);
	
	
	public User getManagerByIdGasStation(int idGasStation);
	

	//public void assignComplaintToHandler(int complaintId, int handlerId);

}
