package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the news database table.
 * 
 */
@Entity(name="news")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class news implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String image;

	@Lob
	private String text;

	private String title;
	public news() {
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
	public news(String title,String image,String text){
		this.title=title;
		this.text=text;
		this.image=image;
	}
	public news(int id,String title,String image,String text){
		this.id=id;
		this.title=title;
		this.text=text;
		this.image=image;
	}
}