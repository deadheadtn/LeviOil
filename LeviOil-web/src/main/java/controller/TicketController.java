package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import model.GasStation;
import model.Ticket;
import model.TicketState;
import model.User;
import service.TicketService;
import service.UserService;

@ManagedBean(name = "ticketController")
@SessionScoped
public class TicketController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String serialNumber;

	private int cashAmount;

	@NotNull(message = "State is required")
	private TicketState state;

	@NotNull(message = "Expiry Date is required")
	@Future(message = "Expiry Date must be in the future")
	private Date expiryDate;

	@EJB
	TicketService ticketService;
	@EJB
	UserService userService;

	public TicketController() {
		// Default Constructor
	}

	public String create() {
		Ticket ticket = new Ticket();
		ticket.setState(TicketState.PENDING);
		ticket.setSerialNumber(Integer.parseInt(this.serialNumber));
		ticket.setCashAmount(this.cashAmount);
		ticket.setExpiryDate(this.expiryDate);
		// Get User By Session
		User manager = (User) userService.findById(User.class, 20);
		GasStation gasStation = manager.getGasStation();
		ticket.setGasStation(gasStation);
		ticketService.add(ticket);
		return "index?faces-redirect=true";
	}

	public String edit(Ticket ticket) {
		this.serialNumber = Integer.toString(ticket.getSerialNumber());
		this.cashAmount = ticket.getCashAmount();
		this.state = ticket.getState();
		this.expiryDate = ticket.getExpiryDate();
		return "edit?faces-redirect=true";
	}

	public String update() {
		int serialNumber = Integer.parseInt(this.serialNumber);
		Ticket ticket = ticketService.findBySerialNumber(serialNumber);
		ticket.setCashAmount(cashAmount);
		ticket.setExpiryDate(expiryDate);
		ticketService.update(ticket);
		return "index?faces-redirect=true";
	}

	public String delete(int id) {
		ticketService.delete(Ticket.class, id);
		return "index?faces-redirect=true";
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findAll() {
		List<Ticket> tickets = (List<Ticket>) (Object) ticketService.findAll(Ticket.class);
		return tickets;
	}

	public void validateSerialNumber(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String errorMessage = "";
		if (value != null) {
			String serialNumber = (String) value;
			if (serialNumber.isEmpty())
				errorMessage = "Serial number is required";
			else if (serialNumber.length() != 8 || !serialNumber.matches("[0-9]+"))
				errorMessage = "Serial number must be composed of 8 digits";
			else if (ticketService.findBySerialNumber(Integer.parseInt(serialNumber)) != null)
				errorMessage = "This serial number already exists, it must be unique";
		} else
			errorMessage = "Serial number is required";
		if (!errorMessage.isEmpty())
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
	}

	public void validateCashAmount(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String errorMessage = "";
		if (value != null) {
			int cashAmount = (int) value;
			String cashAmountString = String.valueOf(cashAmount);
			if (!cashAmountString.matches("[0-9]+"))
				errorMessage = "Cash amount must be a number";
		} else
			errorMessage = "Cash amount is required";
		if (!errorMessage.isEmpty())
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(int cashAmount) {
		this.cashAmount = cashAmount;
	}

	public TicketState getState() {
		return state;
	}

	public TicketState[] getStates() {
		return TicketState.values();
	}

	public void setState(TicketState state) {
		this.state = state;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
