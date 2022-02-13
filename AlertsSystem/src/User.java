import java.util.ArrayList;
import java.util.Collections;

public class User {

	private int id;
	private String name;
	private ArrayList<UserAlert> alerts;
	private ArrayList<String> topics;
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.alerts = new ArrayList<UserAlert>();
		this.topics = new ArrayList<String>();
	}
	
	/**
	 * Retorna la Id del usuario
	 **/
	public int getId() {
		return id;
	}
	
	/**
	 * Retorna el nombre del usuario
	 **/
	public String getName() {
		return name;
	}
	
	/**
	 * Retorna la alerta que se encuentra en la posicion que coincide con id
	 **/
	public UserAlert getAlert(int id) {
		UserAlert alert = this.alerts.get(id);
		return alert;
	}
	
	/**
	 * Retorna todas las alertas del usuario
	 **/
	public ArrayList<UserAlert> getAlerts() {
		return alerts;
	}
	
	/**
	 * Retorna las alertas no leidas y sin expirar del usuario, ordenadas por fecha mas reciente
	 **/
	public ArrayList<UserAlert> getUnreadAlerts() {
		ArrayList<UserAlert> unreadAlerts = new ArrayList<UserAlert>();
		for (UserAlert userAlert : this.getAlerts()) {
			if ((!userAlert.isExpired()) && (!userAlert.isReaded())) {
				unreadAlerts.add(userAlert);
			}
		}
		if (unreadAlerts.size() > 1) {
			Collections.sort(unreadAlerts, Collections.reverseOrder());
		}
		return unreadAlerts;
	}
	
	/**
	 * Marca como leida una alerta del usuario
	 **/
	public void markReadedAlert(int alertId) {
		if ((alertId >= 0) && (alertId < this.alerts.size())) {
			this.getAlert(alertId).setReaded();
		}
		else
			System.out.println("No existe una alerta con ese nro de id.");
	}
	
	/**
	 * Retorna la lista de topicos a los que esta suscripto el usuario
	 **/
	public ArrayList<String> getTopics() {
		return topics;
	}
	
	/**
	 * Agrega una alerta al arreglo de alertas (La logica de cheackear si el usuario debe o no recibirla decidi que sea externa a esta clase)
	 **/
	public void addAlert(UserAlert alert) {
		this.alerts.add(alert);
	}
	
	/**
	 * Suscribe al usuario a un nuevo topico
	 **/
	public void addTopic(String topic) {
		this.topics.add(topic);
	}
	
	/**
	 * Elimina un topico del usuario para que ya no este suscripto
	 **/
	public void removeTopic(String topic) {
		this.topics.remove(topic);
	}
	
	/**
	 * Comprueba si el usuario esta suscripto a un topico recibido por parametro
	 **/
	public boolean isSusbribed(String topic) {
		if (this.topics.contains(topic)) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + "]\n";
	}
	public boolean isSuscribed(String topic) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isSubscribed(String topic) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
