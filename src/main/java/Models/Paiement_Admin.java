package Models;

public class Paiement_Admin extends Paiment{
	int id;
	Admin a;
	/**
	 * @param id
	 * @param a
	 */
	
	public int getId() {
		return id;
	}
	/**
	 * 
	 */
	public Paiement_Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param date
	 */
	public Paiement_Admin(int id, String date) {
		this.id = id;
		this.a = a;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param p
	 */
	public Paiement_Admin(Paiment p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param a
	 */
	public Paiement_Admin(int id, String date,Admin a,int id_e) {
		super(id, date);
		this.a = a;
		this.id = id_e;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Admin getA() {
		return a;
	}
	public void setA(Admin a) {
		this.a = a;
	}
	@Override
	public String toString() {
		return "Paiement_Admin [id=" + id + ", a=" + a + "]";
	}
	
}
