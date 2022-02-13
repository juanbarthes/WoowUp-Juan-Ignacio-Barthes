import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class AlertSystem {
	private ArrayList<User> users;
	/*
	 * Debido a que hay alertas generales que se envian a todos los usuarios me parecio mas performante
	 * guardarlas para recuperarlas cuando sea necesario en lugar de ciclar todos los usuarios y recuperar muchas alertas repetidas.
	 */
	private ArrayList<Alert> alerts;
	private ArrayList<String> topics;
	
	public AlertSystem() {
		this.users = new ArrayList<User>();
		this.alerts = new ArrayList<Alert>();
		this.topics = new ArrayList<String>();	
	}
	
	/*
	 * Agrega una alerta al sistema
	 */
	public void addAlert(LocalDate created, LocalDate expire, boolean urgent, String topic, String description) {
		Alert alert = new Alert(this.alerts.size(), created, expire, urgent, topic, description);
		this.alerts.add(alert);
	}
	
	/*
	 * Recupera la alerta que este en la posicion del arreglo que coincide con alertId
	 */
	public Alert getAlert(int alertId) {
		return alerts.get(alertId);
	}
	
	/*
	 * Retorna todas las alertas cargadas en el sistema
	 */
	public ArrayList<Alert> getAlerts() {
		return alerts;
	}
	
	/*
	 * Filtra y retorna todas las alertas clasificadas en un mismo topico que se recibe por parametro
	 */
	public ArrayList<Alert> getAlertsByTopic(String topic) {
		ArrayList<Alert> alertsByTopic = new ArrayList<Alert>();
		for (Alert systemAlert : this.getAlerts()) {
			if ((!systemAlert.isExpired()) && (systemAlert.getTopic() == topic)) {
				alertsByTopic.add(systemAlert);
			}
		}
		if (alertsByTopic.size() > 1) {
			Collections.sort(alertsByTopic, Collections.reverseOrder());
		}
		return alertsByTopic;
	}
	
	/*
	 * Agrega un usuario al sistema
	 */
	public void addUser(String name) {
		User user = new User(this.users.size(), name);
		this.users.add(user);
	}
	
	/*
	 * Retorna el usuario que este en la posicion del arreglo que coincide con userId
	 */
	public User getUser(int userId) {
		return users.get(userId);
	}
	
	/*
	 * Retorna todos los usuarios cargados en el sistema
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/*
	 * Agrega un topico al sistema
	 */
	public void addTopic(String topic) {
		this.topics.add(topic);
	}

	/*
	 * Retorna el tipico que este en la posicion del arreglo que coincide con index
	 */
	public String getTopic(int index) {
		return this.topics.get(index);
	}
	
	/*
	 * Retorna todos los topicos cargados en el sistema
	 */
	public ArrayList<String> getTopics() {
		return topics;
	}
	
	
	/*
	 * Chequea si una alerta debe o no ser aceptada por un usuario
	 */
	public boolean isAllowed(Alert alert, User user) {
		if ((alert.isUrgent()) || (user.isSusbribed(alert.getTopic()))) {
			return true;
		}
		return false;
	}
	
	/*
	 * Crea una UserAlert y llama al metodo necesario de usuario para cargarla en el mismo
	 */
	private void sendAlert(Alert alert, User user) {
		if (this.isAllowed(alert, user)) {			
			UserAlert userAlert = new UserAlert(user.getAlerts().size(), alert.getCreated(), alert.getExpire(), alert.isUrgent(), alert.getTopic(), alert.getDescription());
			user.addAlert(userAlert);
		}
	}
	
	/*
	 * Se encarga de enviar una alerta a todos los usuarios
	 */
	public void sendGeneralAlert(Alert alert) {
		for (User user : users) {
			this.sendAlert(alert, user);
		}
	}
	
	/*
	 * Crea y envia una alerta hecha para un unico usuario especifico
	 */
	public void sendPrivateAlert(User user, LocalDate created, LocalDate expire, boolean urgent, String topic, String description) {
		if ((user.isSubscribed(topic)) || (urgent)) {
			UserAlert alert = new UserAlert(user.getAlerts().size(), created, expire, urgent, topic, description);
			user.addAlert(alert);
			PrivateAlert privateAlert = new PrivateAlert(this.alerts.size(), created, expire, urgent, topic, description, user);
			this.alerts.add(privateAlert);
		}
	}
	

}
