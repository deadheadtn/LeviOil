package services;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.User;
import entity.UserLog;
import interfaces.LogUserRemote;

@Stateless
@LocalBean
public class UserLogService implements LogUserRemote {
	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	@Override
	public UserLog AddLog(UserLog logu) {
		
		em.persist(logu);
		System.out.println("log ajouté");
		return logu;
	}
	
	@Override
	public List<UserLog> findallLog(){
		
		
		 List<UserLog>	 listalllog=em.createQuery("from userlog", UserLog.class).getResultList(); 
		 
		 return listalllog;
	}
	@Override
	public List<UserLog> finallLogbyUserId(int id){
		
		
		 List<UserLog>	 Singleuserlog= em.createQuery("Select l from userlog l where user.id=:id", UserLog.class)
				 .setParameter("id",id).getResultList();
		 
		 return Singleuserlog;
	}
	@Override
	public void sendlog(String msg ,User u) {
		 Logger logger = Logger.getLogger("MyLog");  
		    FileHandler fh;  
		    boolean append = true;

		    try {  

		        // This block configure the logger with handler and formatter  
		        fh = new FileHandler("C:/Users/admin/eclipse-workspace/LeviOil/LeviOil-web/src/main/webapp/resources/logs/UserLog.log",append);  
		        logger.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  

		        // the following statement is used to log any messages  
		         if(msg.equals("Three failed Login attempts")) {
		        	 logger.warning((u.getFullName()+" Three failed  Login attempts"));;

		        	 
		         }
		         else if (msg.equals("update profile picture")) {
		        	 logger.info(u.getFullName()+" update profile picture");
		        	 
		         }
		         else if (msg.equals("update profile credential")) {
		        	 logger.info(u.getFullName()+" update profile credential");
		        	 
		         }
		         else if(msg.equals("Successful Login")) {
		        	 
		        	 logger.info(u.getFullName()+" Successful Login ");

		         }

		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  

		    
		
		
		
	}
	@Override
	public long FailureAttemptForUser(User u , String log) {
		 long Singleusermsg= (long)(em.createQuery("SELECT COUNT(*) FROM userlog l where l.user.id=:id AND l.Log=:log")
				 .setParameter("id",u.getId()).setParameter("log", log).getSingleResult());
		
		return Singleusermsg;
	}
	
}
