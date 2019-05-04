package entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



@Entity
@Table(name="complaint")
public class Complaint implements Serializable {

            
    public enum Status{
        PENDING,ONGOING,RESOLVED,FAILED
    }
    //Specifies if the the complaint was filed by the customer or the employee or manager
    public enum Category{
    	CUSTOMER,EMPLOYEE,MANAGER
    }
    //Specifies if the customer complaint is filed against an employee or it's a general complaint
    public enum CustomerComplaintCategory{
        GENERAL,EMPLOYEE
    }
    
    
    private int id;

    private String subject;
    
    private String content;
      
    private Status status;
    
    private Date createdAt;
    
    private Date updatedAt;
    
    private Category category;
    
    private CustomerComplaintCategory customerComplaintCategory;
    
    private User complainant;
    
    private User complainee;
    
    private User handler;
    
    public boolean removed = false;
    
    private String comment;
    
    
    public Complaint() {
    	
    }

    //GETTERS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

   

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    public Category getCategory() {
        return category;
    }
    
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_complaint_category")
	public CustomerComplaintCategory getCustomerComplaintCategory() {
		return customerComplaintCategory;
	}


    @ManyToOne
    @JoinColumn(name = "complainant_id")
    public User getComplainant() {
        return complainant;
    }

    @ManyToOne
    @JoinColumn(name = "complainee_id")
    public User getComplainee() {
		return complainee;
	}
    
    @ManyToOne
    @JoinColumn(name = "handler_id")
    public User getHandler() {
        return handler;
    }
    
    @Column(name = "removed")
    public boolean isRemoved() {
		return removed;
	}
    
    @Column(name = "comment")
	public String getComment() {
		return comment;
	}
    
   private String managerGasStationAddress;

	

	//SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComplainant(User complainant) {
        this.complainant = complainant;
    }

    public void setHandler(User handler) {
        this.handler = handler;
    }

	public void setCustomerComplaintCategory(CustomerComplaintCategory customerComplaintCategory) {
		this.customerComplaintCategory = customerComplaintCategory;
	}
	
	public void setComplainee(User complainee) {
		this.complainee = complainee;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	////OTHER
	@Transient
	public String getComplainantFullName()
	{
		return complainant.getFullName();
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", subject=" + subject + ", content=" + content + ", status=" + status
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", category=" + category
				+ ", customerComplaintCategory=" + customerComplaintCategory + ", complainant=" + complainant
				+ ", complainee=" + complainee + ", handler=" + handler + ", removed=" + removed + ", comment="
				+ comment + "]";
	}

	@Transient
	public String getManagerGasStationAddress() {
		   return handler.getGasStation().getStreet();
		}


	@Transient
	public void setManagerGasStationAddress(String managerGasStationAddress) {
		this.managerGasStationAddress = managerGasStationAddress;
	}

    

}