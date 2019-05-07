package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entity.User;
import entity.news;
import services.CommentsService;
import entity.Comments;
@ManagedBean
@SessionScoped
public class CommentsBean implements Serializable{
private int id;
    
    private String content;

    private Date createdAt;
        
    private User commented_user;
   
    private news post;
    
    Boolean removed;
    
    private Comments Comment;
    @EJB
    CommentsService cs;
    
    public List<Comments> getallcomments(){
    	return cs.getAllcomments();	
    }
    public void performRedirect(news n) throws IOException{
        //Go to the detail view with the selected id as url parameter
        FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/client/news.xhtml?id=" + n.getId());
    }
    public String addcomment(news n,User u) throws IOException{
    	if (u == null){
    		return "/pages/client/login?faces-redirect=true";
    	}
    	Set<String> badWords = new HashSet<String>();
    	badWords.add("fuck");
    	badWords.add("shit");
    	if(hasBadWord(content, badWords)){
    		Comments c= new Comments(content,u,n,true);
    		java.util.Date date = new Date();
        	c.setCreatedAt((Date)new java.sql.Timestamp(date.getTime()));
        	cs.addComment(c);
        	return null;
    	}
    	else{
    		Comments c= new Comments(content,u,n,false);
    		java.util.Date date = new Date();
        	c.setCreatedAt((Date)new java.sql.Timestamp(date.getTime()));
        	cs.addComment(c);
        	return null;
    	}
    	
    }
    boolean hasBadWord(String filename, Set<String> badWords) {
        String filenameLower = filename.toLowerCase();
        for(String badWord : badWords) {
            if( filenameLower.contains(badWord) ) {
                return true;
            }
        }
        return false;
    }
    public List<Comments> commentByPost(news n){
    	return cs.getAllcommentsfrompost(n);
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public Comments getComment() {
		return Comment;
	}

	public void setComment(Comments comment) {
		Comment = comment;
	}
    
    
}
