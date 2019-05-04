package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entity.Cv;
@Remote
public interface CvRemote {
	public List<Cv> getAllposts ();
	public int addCv(Cv CV);
	int delete(Cv CV);
	int modifyCv(Cv CV);
}
