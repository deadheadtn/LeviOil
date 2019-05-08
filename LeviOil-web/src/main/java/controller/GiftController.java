package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import model.Gift;
import model.User;
import service.CustomerGiftService;
import service.GiftService;
import service.UserService;

@ManagedBean(name = "giftController")
@SessionScoped
public class GiftController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	@NotNull(message = "Description is required")
	@Size(min = 10, max = 200, message = "Description must have 10 characters at least")
	private String description;
	@Digits(integer = 8, fraction = 0, message = "Quantity must be a number")
	private int quantity;
	@Digits(integer = 8, fraction = 0, message = "Quantity must be a number")
	private int pointsRequired;
	private String picture;
	private Part imageFile;
	@EJB
	GiftService giftService;

	public GiftController() {
		// TODO Auto-generated constructor stub
	}

	public String create() throws IOException {
		Gift gift = new Gift();
		gift.setName(this.name);
		gift.setDescription(this.description);
		gift.setQuantity(this.quantity);
		gift.setPointsRequired(this.pointsRequired);
		gift.setPicture(this.picture);
		// Get User By Session
		User currentUser = new User();
		currentUser.setId(1);
		// GasStation gasStation = currentUser.getGasStation();
		// gift.setGasStation(gasStation);
		giftService.add(gift);
		return "index?faces-redirect=true";
	}

	public void uploadImage() throws IOException {
		try (InputStream input = imageFile.getInputStream()) {
			// String folderPath = "H:\\Workspace\\oil images";
			// String folderPath =
			// "H:\\Workspace\\oil\\oil-web\\src\\main\\webapp\\pages\\manager\\gift\\resources\\images";
			String folderPath = "H:\\Workspace\\oil\\oil-web\\src\\main\\webapp\\resources\\images";
			String fileName = imageFile.getSubmittedFileName();
			Files.copy(input, new File(folderPath, fileName).toPath());
			this.picture = fileName;
			System.out.println("pic name " + this.picture);
		} catch (IOException e) {
			System.out.println(e);
			// Show faces message?
		}
	}

	public String edit(Gift gift) {
		this.name = gift.getName();
		this.description = gift.getDescription();
		this.quantity = gift.getQuantity();
		this.pointsRequired = gift.getPointsRequired();
		// this.picture = gift.getPicture();
		return "edit?faces-redirect=true";
	}

	public String update() {
		Gift gift = giftService.findByName(this.name);
		gift.setName(this.name);
		gift.setDescription(this.description);
		gift.setQuantity(this.quantity);
		gift.setPointsRequired(this.pointsRequired);
		// gift.setPicture(this.picture);
		giftService.update(gift);
		return "index?faces-redirect=true";
	}

	public String delete(int id) {
		giftService.delete(Gift.class, id);
		return "index?faces-redirect=true";
	}

	@SuppressWarnings("unchecked")
	public List<Gift> findAll() {
		List<Gift> gifts = (List<Gift>) (Object) giftService.findAll(Gift.class);
		return gifts;
	}

	public void validateImage(FacesContext context, UIComponent component, Object value) {
		Part file = (Part) value;
		String errorMessage = isImageValid(file);
		if (!errorMessage.isEmpty())
			throw new ValidatorException(new FacesMessage(errorMessage));
	}

	public String isImageValid(Part image) {
		String errorMessage = "";
		boolean extensionIsValid = false;
		boolean sizeIsValid = false;
		// Checking if extension is invalid
		String[] extensions = { "jpeg", "jpg", "png", "bmp" };
		String fileName = image.getSubmittedFileName();
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		for (String extension : extensions)
			if (fileExtension.equals(extension))
				extensionIsValid = true;

		// Checking if file size is > 10 MB
		long fileSizeInBytes = image.getSize();
		long fileSizeInKB = fileSizeInBytes / 1024;
		long fileSizeInMB = fileSizeInKB / 1024;
		if (fileSizeInMB < 10)
			sizeIsValid = true;

		if (!extensionIsValid)
			errorMessage = "Image extension is invalid, valid extensions are : jpeg, jpg, png, bmp";
		else if (!sizeIsValid)
			errorMessage = "File is too large, Maximum is 10 MB";

		return errorMessage;
	}

	public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String errorMessage = "";
		if (value != null) {
			String name = (String) value;
			if (name.isEmpty() || name.trim().isEmpty())
				errorMessage = "Name is required";
			else if (name.length() < 2)
				errorMessage = "Name must have 2 characters at least";
			else if (Pattern.matches("[a-zA-Z]+", name) == false)
				errorMessage = "Name must contain one letter at least";
			else if (giftService.findByName(name) != null)
				errorMessage = "This name already exists, it must be unique";
		} else
			errorMessage = "Name is required";
		if (!errorMessage.isEmpty())
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
	}

	@EJB
	UserService userService;

	public int getConnectedUserFidelityPoints() {
		User customer = (User) userService.findById(User.class, 10);
		return customer.getFidelityPoints();
	}

	/*
	 * public List<User> getUserRanking() {
	 * 
	 * }
	 */

	@EJB
	CustomerGiftService customerGiftService;
	private int boughtGiftsThisYear;
	private int boughtGiftsThisMonth;
	private int boughtGiftsThisDay;

	public int getBoughtGiftsThisYear() {
		System.out.println("Gifts bougt this year = " + customerGiftService.getBoughtGiftsThisYear());
		return customerGiftService.getBoughtGiftsThisYear();
	}

	public int getBoughtGiftsThisMonth() {
		System.out.println("Gifts bougt this Month = " + customerGiftService.getBoughtGiftsThisMonth());
		return customerGiftService.getBoughtGiftsThisMonth();

	}

	public int getBoughtGiftsThisDay() {
		System.out.println("Gifts bougt this Day = " + customerGiftService.getBoughtGiftsThisDay());
		return customerGiftService.getBoughtGiftsThisDay();
	}
	
	

	public void setBoughtGiftsThisYear(int boughtGiftsThisYear) {
		this.boughtGiftsThisYear = boughtGiftsThisYear;
	}

	public void setBoughtGiftsThisMonth(int boughtGiftsThisMonth) {
		this.boughtGiftsThisMonth = boughtGiftsThisMonth;
	}

	public void setBoughtGiftsThisDay(int boughtGiftsThisDay) {
		this.boughtGiftsThisDay = boughtGiftsThisDay;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
