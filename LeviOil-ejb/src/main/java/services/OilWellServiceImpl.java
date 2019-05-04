package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import entity.OilWell;
import entity.OilWellStateEnum;
import entity.Ressource;
import entity.RessourceTypeEnum;
import interfaces.OilWellService;
import javafx.scene.control.Alert;

@Stateless
public class OilWellServiceImpl implements OilWellService {

	@PersistenceContext(unitName = "LeviOil-ejb")
	EntityManager em;
	
	@Override
	public void addOilWell(OilWell oilWell) {
		// TODO Auto-generated method stub
		em.persist(oilWell);
	}

	@Override
	public Ressource getRessourceByTypeAndEfficiency(RessourceTypeEnum ressourceTypeEnum, Integer n) {
		// TODO Auto-generated method stub
		System.out.println(em.toString());
		TypedQuery<Ressource> query = em.createQuery("select r from Ressource r where  r.type=:ressourceType ", Ressource.class);
		query.setParameter("ressourceType", ressourceTypeEnum);
		//query.setParameter("efficiency", 29);
		System.out.println("mba3ad");
		return query.getResultList().get(0);
	}
	
	@Override
	public OilWell getOilWell(int id) {
		// TODO Auto-generated method stub
		return em.find(OilWell.class, id);
	}

	@Override
	public List<OilWell> getAllOilWell() {
		// TODO Auto-generated method stub
		return em.createQuery("from OilWell", OilWell.class).getResultList();
	}

	@Override
	public void deleteOilWell(int id) {
		// TODO Auto-generated method stub
		OilWell oilWell = em.find(OilWell.class, id);
		em.remove(oilWell);
	}

	@Override
	public int update2(int id,OilWell oilWell) {
		Query query = em.createQuery("update OilWell o set capacity=:capacity,state=:state,quantityExtracted=:quantityExtracted,"
				+" drillingTime=:drillingTime, "
				+ " extractionTime=:extractionTime"
				+ " where id=:id");
		query.setParameter("id", id);
		query.setParameter("drillingTime", oilWell.getDrillingTime());
		query.setParameter("extractionTime", oilWell.getExtractionTime());
		query.setParameter("state", OilWellStateEnum.ReadyForDrilling);
		query.setParameter("quantityExtracted", oilWell.getQuantityExtracted());
		query.setParameter("capacity", oilWell.getCapacity());
		//em.joinTransaction();
		return query.executeUpdate();
	}
	
	@Override
	public int updateOilWell(int id, OilWell newOilWell) {
		// TODO Auto-generated method stub
		OilWell oilWell = em.find(OilWell.class, id);
		oilWell.setRegistration(newOilWell.getRegistration());
		
	//	if (newOilWell.getCapacity()!=0)
		oilWell.setCapacity(newOilWell.getCapacity());
	//	if (newOilWell.getCost()!=0)
		oilWell.setCost(newOilWell.getCost());
	//	if (newOilWell.getEstimatedTime()!=0)
		oilWell.setDrillingTime(newOilWell.getDrillingTime());
		
		oilWell.setExtractionTime(newOilWell.getExtractionTime());
	//	if (newOilWell.getQuantityExtracted()!=0)
		oilWell.setQuantityExtracted(newOilWell.getQuantityExtracted());
	//	if (!newOilWell.getState().equals(""))
		oilWell.setState(newOilWell.getState());
		//System.out.println("fel update state + "+newOilWell.getState().toString());
		return id;
	}
	@Override
	public OilWell getOilWellByRegistration(String registration) {
		// TODO Auto-generated method stub
		TypedQuery<OilWell> query = em.createQuery("select o from OilWell o where o.registration=:registration", OilWell.class);
		query.setParameter("registration", registration);
		return query.getSingleResult();
	}

	@Override
	public OilWell addRessourceToOilWell(int id, Ressource ressource2) {
		// TODO Auto-generated method stub
		OilWell oilWell = em.find(OilWell.class, id);
		RessourceServiceImpl rs= new RessourceServiceImpl();
			System.out.println(ressource2.getType().toString()+" "+ ressource2.getEfficiency());
		Ressource ressource = this.getRessourceByTypeAndEfficiency(ressource2.getType(), ressource2.getEfficiency());
			System.out.println("null3");
		oilWell.getRessources().add(ressource);
		ressource.setQuantity(ressource.getQuantity()-1);
		if (ressource.getType().equals(RessourceTypeEnum.Drillingrig) && oilWell.getState().equals(OilWellStateEnum.Waiting) && oilWell.getDrillingTime()!=0)
			oilWell.setState(OilWellStateEnum.ReadyForDrilling);
		if ((ressource.getType().equals(RessourceTypeEnum.Pumpjack) ||ressource.getType().equals(RessourceTypeEnum.SteamInjector)) && oilWell.getDrillingTime()==0 && oilWell.getState().equals(OilWellStateEnum.Waiting))
			oilWell.setState(OilWellStateEnum.ReadyForExtraction);
		return oilWell;
	}

	@Override
	public OilWell removeRessourceFromOilWell(int id, Ressource ressource) {
		// TODO Auto-generated method stub

		OilWell oilWell = em.find(OilWell.class, id);
		Ressource ressource2 = em.find(Ressource.class, ressource.getId());
		oilWell.getRessources().remove(ressource2);
		return oilWell;
	}
	
	@Override
	public int startDrilling(int id) {
		OilWell oilWell = em.find(OilWell.class, id);
		int drillingRessource = 0;
		List<Ressource> listRessource = oilWell.getRessources();
		if (oilWell.getDrillingTime()==0)
			return 0;
		if (oilWell.getState().equals(OilWellStateEnum.Drilling))
			return 1;
		if (!oilWell.getState().equals(OilWellStateEnum.ReadyForDrilling))
			return 5;
		
			for (Ressource list : listRessource) {
			if (list.getType().equals(RessourceTypeEnum.Drillingrig))
				drillingRessource++;
		}
		if (drillingRessource==0)
			return 3;
			oilWell.setState(OilWellStateEnum.Drilling);

			return 4;
		
	}
	
	@Override
	public int startExtraction(int id) {
		OilWell oilWell = em.find(OilWell.class, id);
		int extractionRessource = 0;
		List<Ressource> listRessource = oilWell.getRessources();
		if (oilWell.getExtractionTime()==0) 
			return 0;
		if (oilWell.getDrillingTime()!=0) 
			return 1;
			for (Ressource list : listRessource) {
			if (list.getType().equals(RessourceTypeEnum.Pumpjack) || list.getType().equals(RessourceTypeEnum.SteamInjector))
				extractionRessource++;
		}
		if (extractionRessource==0)
			return 2;
		
			oilWell.setState(OilWellStateEnum.Extraction);
		return 3;
	}
	@Override
	public void refresh() {
	/*	Date date=new Date();
		Timer timer = new Timer();

		timer.schedule(new TimerTask(){
		     public void run(){
		          System.out.println("Im Running..."+new Date());
		  		List<OilWell> oilWellList = getAllOilWell();
				for (OilWell oilWell : oilWellList) {
					for (int i = 0;i<2500;i++) {
						
					}
					refreshProgress(oilWell.getId());
				}
		        //System.out.println("aaaaaaaaaaaaa");
				//System.out.println(update2(4, newOilWell));
	//	          System.out.println("id + "+oilWell.getId());

		     }
		},date, 10000);*/
	}
	
	public OilWell refreshProgress(int id)
	{
		OilWell oilWell= em.find(OilWell.class, id);
	
		if (oilWell.getState().equals(OilWellStateEnum.Waiting) && calculateDrillingPower(id)>0)
				oilWell.setState(OilWellStateEnum.ReadyForDrilling);
		if (oilWell.getState().equals(OilWellStateEnum.ReadyForDrilling) && calculateDrillingPower(id)==0)
				oilWell.setState(OilWellStateEnum.Waiting);
		if (oilWell.getDrillingTime()!=0 && oilWell.getState().equals(OilWellStateEnum.Drilling))
		{
			if(oilWell.getDrillingTime()-calculateDrillingPower(id)<=0)
				oilWell.setDrillingTime(0);
			else
			oilWell.setDrillingTime(oilWell.getDrillingTime()-calculateDrillingPower(id));
			if (oilWell.getDrillingTime()==0)
			{
				if(calculateExtractionPower(id)>0) //ma3neha 3andou ressources mta3 extraction 
				oilWell.setState(OilWellStateEnum.ReadyForExtraction);
				else
					oilWell.setState(OilWellStateEnum.Waiting);
			}
		}
		if (oilWell.getState().equals(OilWellStateEnum.ReadyForExtraction) && calculateExtractionPower(id)==0)
			oilWell.setState(OilWellStateEnum.Waiting);
		if (oilWell.getExtractionTime()!=0 && oilWell.getState().equals(OilWellStateEnum.Extraction))
		{
			if(oilWell.getExtractionTime()-calculateExtractionPower(id)<=0)
			{
				oilWell.setQuantityExtracted(oilWell.getQuantityExtracted()+oilWell.getExtractionTime());
				oilWell.setExtractionTime(0);//extracting
				oilWell.setState(OilWellStateEnum.Empty);
			}
			else {
			oilWell.setExtractionTime(oilWell.getExtractionTime()-calculateExtractionPower(id));
			oilWell.setQuantityExtracted(oilWell.getQuantityExtracted()+calculateExtractionPower(id));
			}
			}
		
		System.out.println(oilWell.toString());
		/*System.out.println(oilWell.toString());
		oilWell.setCapacity(5000);
		System.out.println(oilWell.toString());
		*/
	//	OilWell oilWell2 = new OilWell("123456789", 2000, 200, 200, 30000, 0, OilWellStateEnum.ReadyForDrilling);
		//oilWell.setState(OilWellStateEnum.Waiting);
		updateOilWell(id, oilWell);
		return em.find(OilWell.class, id);
	}
	
	public int calculateDrillingPower(int id)
	{
		OilWell oilWell = em.find(OilWell.class, id);
		int power=0;
		for (Ressource ressource: oilWell.getRessources()) {
			if (ressource.getType().equals(RessourceTypeEnum.Drillingrig))
				power+=ressource.getEfficiency();
		}
		return power;
	}
	
	public int calculateExtractionPower(int id)
	{
		OilWell oilWell = em.find(OilWell.class, id);
		int power=0;
		for (Ressource ressource: oilWell.getRessources()) {
			if (ressource.getType().equals(RessourceTypeEnum.Pumpjack)|| ressource.getType().equals(RessourceTypeEnum.SteamInjector))
				power+=ressource.getEfficiency();
		}
		return power;
	}

}
