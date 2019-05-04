package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.news;
import services.NewsService;


@ManagedBean
@SessionScoped
public class NewsBean {
	private int id;

	private String image;

	private String text;

	private String title;
	public NewsBean() {
	}
	@EJB
	NewsService newsService;
	public  List<news> findall() {
		List<news> newlist =newsService.getAllposts();
		System.out.println(newlist.toString());
		return newlist;
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