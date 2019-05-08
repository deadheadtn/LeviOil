package controller;

import java.io.Console;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.log.Log;
import org.primefaces.model.charts.bar.BarChartModel;

import entity.news;
import entity.Comments;
import services.NewsService;


@ManagedBean
@SessionScoped
public class NewsBean  implements Serializable {
	private int id;

	private String image;
	
	private String text;

	private String title;
	private boolean editable;
	news News;
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public NewsBean() {
	}
	@EJB
	NewsService newsService;
	public  List<news> findall() {
		List<news> newlist =newsService.getAllposts();
		return newlist;
	}
	public int addnews() {
		news n= new news(title,image,text);
		newsService.addNews(n);
		this.setImage("");
		this.setText("");
		this.setTitle("");
		return 0;
	}
	
	
	
	public news newsonly(){
		return this.getNews();
	}	
	
	public String gotonewssingle(news n){
		this.setNews(n);
		return "/pages/client/singlenews.xhtml";
	}

	public String Comments(news n){
		return  "/pages/client/comments?faces-redirect=true";
	}
	public int delete(news n) {
		newsService.delete(n);
		return 0;
	}
	public boolean isEditable() {
		return editable;
	}
	
	public String saveNews(){
		news n=new news(id,title,image,text);
		
		newsService.modifynews(n);
		return null;
    }
	public String editNews(news n){
		this.setId(n.getId());
		this.setImage(n.getImage());
		this.setText(n.getText());
		this.setTitle(n.getTitle());
		newsService.edit(n);
		return null;
	}
	public news getNews() {
		return News;
	}
	public void setNews(news news) {
		News = news;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}