package interfaces;

import java.util.List;


import javax.ejb.Remote;

import entity.MaterielWashing;


@Remote
public interface MaterielWashingIservice {
	public int addMaterielWashing(MaterielWashing MaterielWashing);
	public void removeMaterielWashing(int idMaterielWashing);
	public void updateMaterielWashing(MaterielWashing MaterielWashing) ;
	public MaterielWashing findMaterielWashingById(int idMaterielWashing);
	public List<MaterielWashing> findAllMaterielWashing();
	public void addMateriel1();
	public void addMateriel2();
	public void addMateriel3();
}
