package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

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
    private BarChartModel barModel;
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
    
	public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Removed comments per month");
        List<Number> values = new ArrayList<>();
        int i=1;
        while( i<13) {
        
    	Number failureTattemptnumber=(Number)cs.getremovedComments(i);

            values.add(failureTattemptnumber);
            i++;
            System.out.println(failureTattemptnumber);

            }

        barDataSet.setData(values);
         
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");

        barDataSet.setBackgroundColor(bgColor);
         
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(201, 203, 207)");
        
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
         
        data.addChartDataSet(barDataSet);
         
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("Juillet");
        labels.add("Aout");
        labels.add("September");
        labels.add("November");
        labels.add("October");
        labels.add("Decempber");
        data.setLabels(labels);
        barModel.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("comment removed chart");
        options.setTitle(title);
 
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        barModel.setOptions(options);
        this.setBarModel(barModel);
    }
	

	public BarChartModel getBarModel() {
		return barModel;
	}
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	
	
}
