package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.User;
import entity.UserLog;

@Remote
public interface LogUserRemote {

	public UserLog AddLog(UserLog logu);
	public List<UserLog> findallLog();
	public List<UserLog> finallLogbyUserId(int id);
	public void sendlog(String msg ,User u);
	public long FailureAttemptForUser(User u , String log);
	public long  LogN (String log,int month);
	public long allLogNumber(String log);
}
