import java.time.LocalDate;

public class PrivateAlert extends Alert{
	//Se agrega el atributo user, ya que este tipo especial de alerta tiene como objetivo un unico destinatario
	User user;

	public PrivateAlert(int id, LocalDate created, LocalDate expire, boolean urgent, String topic, String description, User user) {
		super(id, created, expire, urgent, topic, description);
		this.user = user;
	}

	/*
	 * Retorna el usuario destinatario de la alerta
	 */
	public String getDestinatary() {
		return this.user.toString();
	}

	@Override
	public String toString() {
		return super.toString() + ", destinatary=" + this.getDestinatary() + "]";
	}
	
	

}
