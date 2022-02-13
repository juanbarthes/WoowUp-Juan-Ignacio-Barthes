import java.time.LocalDate;

public class UserAlert extends Alert{

	//Se agrega el atriburo readed para que se pueda simular "leer" la alerta
	private boolean readed;

	public UserAlert(int id, LocalDate created, LocalDate expire, boolean urgent, String topic, String description) {
		super(id, created, expire, urgent, topic, description);
		this.readed = false;
	}
	
	/*
	 * Chequea si la alerta ya fue leida
	 */
	public boolean isReaded() {
		return this.readed;
	}
	
	/*
	 * Marca una alerta como leida 
	 */
	public void setReaded() {
		this.readed = true;
	}

	@Override
	public String toString() {
		return super.toString() + ", readed: " + readed + "]";
	}
	
	

}
