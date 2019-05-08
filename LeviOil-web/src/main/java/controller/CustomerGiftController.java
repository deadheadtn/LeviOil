package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import javax.validation.constraints.Digits;

import model.CustomerGift;
import model.CustomerGift.GiftState;
import model.GasStation;
import model.Gift;
import model.GiftConfig;
import model.Ticket;
import model.TicketState;
import model.User;
import service.CustomerGiftService;
import service.GiftConfigService;
import service.GiftService;
import service.TicketService;
import service.UserService;

@ManagedBean(name = "customerGiftController")
@SessionScoped
public class CustomerGiftController implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum PaymentMethod {
		TICKET, FIDELITY_POINTS
	}

	private String name;
	private String description;
	@Digits(integer = 8, fraction = 0, message = "Quantity must be a number")
	private int quantity;
	private int pointsRequired;
	private String picture;
	private Part imageFile;
	private PaymentMethod paymentMethod;

	@EJB
	GiftConfigService giftConfigService;
	@EJB
	GiftService giftService;
	@EJB
	private CustomerGiftService customerGiftService;
	@EJB
	private TicketService ticketService;
	@EJB
	private UserService userService;

	public CustomerGiftController() {
		// TODO Auto-generated constructor stub
	}

	public String navigateToBuy(Gift gift) {
		this.name = gift.getName();
		this.description = gift.getDescription();
		this.quantity = gift.getQuantity();
		this.pointsRequired = gift.getPointsRequired();
		this.picture = gift.getPicture();
		return "buy?faces-redirect=true";
	}

	public String buyWithPoints() {
		User customer = (User) userService.findById(User.class, 10);
		int totalPointsRequired = this.pointsRequired * this.quantity;
		if (paymentMethod == PaymentMethod.FIDELITY_POINTS) {
			// Adding the gifts to the customer
			for (int i = 0; i < quantity; i++) {
				Gift gift = giftService.findByName(name);
				CustomerGift customerGift = new CustomerGift();
				customerGift.setCustomer(customer);
				customerGift.setGift(gift);
				customerGift.setState(GiftState.AVAILABLE);
				customerGift.setCode(UUID.randomUUID().toString());
				customerGiftService.add(customerGift);
				// Updating gift quantity
				gift.setQuantity(gift.getQuantity() - 1);
				giftService.update(gift);
			}
			// Updating Customer Fidelity Points
			customer.setFidelityPoints(customer.getFidelityPoints() - totalPointsRequired);
			userService.update(customer);
			return "index?faces-redirect=true";
		}

		return "buy_with_tickets?faces-redirect=true";
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////
	private List<Ticket> tickets;

	@PostConstruct
	public void init() {
		tickets = new ArrayList<Ticket>();
		// Ticket ticket = new Ticket();
		// ticket.setId(1);
		// tickets.add(ticket);
	}

	public void add() {
		Ticket ticket = new Ticket();
		ticket.setId(tickets.size() + 1);
		tickets.add(ticket);
	}

	public void remove(Ticket ticket) {
		tickets.remove(ticket);
	}

	public void printTicket() {
		System.out.println("items: " + tickets);
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public String buyWithTickets() {
		User customer = (User) userService.findById(User.class, 10);
		User manager = (User) userService.findById(User.class, 20);
		GasStation gasStation = manager.getGasStation();
		int totalPointsRequired = this.pointsRequired * this.quantity;

		// Loading Tickets from database
		List<Ticket> ticketsDb = new ArrayList<>();
		for (Ticket ticket : tickets)
			ticketsDb.add(ticketService.findBySerialNumber(ticket.getSerialNumber()));
		// Calculating total cash amount of tickets
		int totalCashAmount = 0;
		int totalTicketsPoints = 0;
		for (Ticket ticket : ticketsDb)
			totalCashAmount += ticket.getCashAmount();
		// Converting cash to points
		GiftConfig giftConfig = giftConfigService.findByGasStationId(gasStation.getId());
		totalTicketsPoints = totalCashAmount * giftConfig.getPointsToOneDinar();

		if (totalTicketsPoints < totalPointsRequired) {
			String errorMessage = "Insufficient Points: Total points required = " + totalPointsRequired
					+ ", your total ticket points = " + totalTicketsPoints;
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
		} else {
			for (int i = 0; i < quantity; i++) {
				// Adding Gift to customer
				Gift gift = giftService.findByName(name);
				CustomerGift customerGift = new CustomerGift();
				customerGift.setCustomer(customer);
				customerGift.setGift(gift);
				customerGift.setState(GiftState.AVAILABLE);
				customerGift.setCode(UUID.randomUUID().toString());
				customerGiftService.add(customerGift);
				// Updating gift quantity
				gift.setQuantity(gift.getQuantity() - 1);
				giftService.update(gift);
			}
			// Changing tickets state to sold
			for (Ticket ticket : ticketsDb) {
				ticket.setState(TicketState.SOLD);
				ticketService.update(ticket);
			}
			return "index?faces-redirect=true";
		}
	}

	public void validatePayment(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		PaymentMethod paymentMethod = (PaymentMethod) value;
		User customer = (User) userService.findById(User.class, 10);
		int totalPointsRequired = this.pointsRequired * this.quantity;

		String errorMessage = "Insufficient Points: Total points required = " + totalPointsRequired
				+ ", your fidelity points = " + customer.getFidelityPoints();

		if (paymentMethod == PaymentMethod.FIDELITY_POINTS)
			if (customer.getFidelityPoints() < totalPointsRequired)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
	}

	public void validateSerialNumber(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String errorMessage = "";
		System.out.println("In validate serial Number");
		if (value != null) {
			System.out.println("In validate before conversion");
			int serialNumberInt = (int) value;
			String serialNumber = String.valueOf(serialNumberInt);
			System.out.println("In validate serial Number = " + serialNumber);
			if (serialNumber.isEmpty())
				errorMessage = "Serial number is required";
			else if (serialNumber.length() != 8 || !serialNumber.matches("[0-9]+"))
				errorMessage = "Serial number must be composed of 8 digits";
			else if (ticketService.findBySerialNumber(Integer.parseInt(serialNumber)) == null)
				errorMessage = "This serial number doesn't exist";
		} else
			errorMessage = "Serial number is required";
		if (!errorMessage.isEmpty())
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
	}

	@SuppressWarnings("unchecked")
	public List<Gift> findAll() {
		List<Gift> gifts = (List<Gift>) (Object) giftService.findAll(Gift.class);
		return gifts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(int pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Part getImageFile() {
		return imageFile;
	}

	public void setImageFile(Part imageFile) {
		this.imageFile = imageFile;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public PaymentMethod[] getPaymentMethods() {
		return PaymentMethod.values();
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public GiftService getGiftService() {
		return giftService;
	}

	public void setGiftService(GiftService giftService) {
		this.giftService = giftService;
	}

	public CustomerGiftService getCustomerGiftService() {
		return customerGiftService;
	}

	public void setCustomerGiftService(CustomerGiftService customerGiftService) {
		this.customerGiftService = customerGiftService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
