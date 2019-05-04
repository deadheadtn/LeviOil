package entity;

public enum Role { 
	
	Transoprteur(Values.Transoprteur),Gerant(Values.Gerant),Pompiste(Values.Pompiste),worker(Values.worker),Admin(Values.Admin),
	client(Values.client),Fournisseur(Values.Fournisseur);

	private Role (String s){
		
	}
	public static class Values{
		 public static final String Transoprteur = "transporteur";
		 public static final String Gerant = "gerant" ;
		 public static final String Pompiste = "pompiste"; 
		 public static final String worker = "worker";
		 public static final String Admin = "admin" ;
		 public static final String client = "client";
		 public static final String Fournisseur = "fournisseur";

	}
	
}
