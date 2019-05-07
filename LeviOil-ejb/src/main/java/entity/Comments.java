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
@Table(name="comments")
public class Comments implements Serializable {

    
    
    private int id;
    
    private String content;

    private Date createdAt;
        
    private User commented_user;
    
    private news post;
    
    Boolean removed;
    
    public Comments() {
    	
    }

    public Comments(String content, User commented_user, news post, Boolean removed) {
		super();
		this.content = content;
		this.commented_user = commented_user;
		this.post = post;
		this.removed = removed;
	}

	//GETTERS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }


    @Column(name = "content")
    public String getContent() {
        return content;
    }

 

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @ManyToOne
    @JoinColumn(name = "commented_user")
    public User getcommented_user() {
        return commented_user;
    }
    
    @ManyToOne
    @JoinColumn(name = "post")
    public news getpost() {
        return post;
    }
    
    @Column(name = "removed")
    public boolean isRemoved() {
		return removed;
	}


	

	//SETTERS
    public void setId(int id) {
        this.id = id;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

	
	
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	////OTHER
	@Transient
	public String getComplainantFullName()
	{
		return commented_user.getFullName();
	}

	public User getCommented_user() {
		return commented_user;
	}

	public void setCommented_user(User commented_user) {
		this.commented_user = commented_user;
	}

	public news getPost() {
		return post;
	}

	public void setPost(news post) {
		this.post = post;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}


    

}