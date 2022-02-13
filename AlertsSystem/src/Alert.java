import java.time.LocalDate;

public class Alert implements Comparable<Alert>{
	//La id no cumple ningun proposito pero asumo que en un proyecto real cada alerta deberia tener un identificador unico
	private int id;
	//La fecha de creacion sirve solo de simulacion ya que en un proyecto real se cargaria automaticamente la fecha de creacion real
	private LocalDate created;
	private LocalDate expire;
	private boolean urgent;
	private String topic;
	private String description;
	public Alert(int id, LocalDate created, LocalDate expire, boolean urgent, String topic, String description) {
		super();
		this.id = id;
		this.setCreated(created);
		this.setExpire(expire);
		this.urgent = urgent;
		this.topic = topic;
		this.description = description;
	}

	/*
	 * Si se intenta crear una alerta con fecha de creacion posterior a la fecha actual, se asigna la fecha actual en su lugar
	 */
	private void setCreated(LocalDate date) {
		LocalDate now = LocalDate.now();
		if (date != null) {
			if (date.compareTo(now) > 0) {
				this.created = now;
			}
			else
				this.created = date;
		}
		else
			this.created = now;
	}
	
	/*
	 * Si se intenta crear una alerta con fecha de expiracion anterior a la fecha de creacion, se asigna null en su lugar
	 * ya que no tiene sentido crear una alerta expirada que no se le mostrara al usuario
	 */
	private void setExpire(LocalDate date) {
		if (date != null) {
			if (date.compareTo(this.getCreated()) < 0) {
				this.expire = null;
			}
			else
				this.expire = date;
		}
		else
			this.expire = date;
	}
	
	/*
	 * Retorna la id de la alerta
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Retorna la fecha de creacion de la alerta
	 */
	public LocalDate getCreated() {
		return this.created;
	}
	
	/*
	 * Retorna la fecha de expiracion de la alerta
	 */
	public LocalDate getExpire() {
		return expire;
	}

	/*
	 * Retorna el topico sobre el cual trata la alerta
	 */
	public String getTopic() {
		return topic;
	}

	/*
	 * Retorna la descripcion de la alerta
	 */
	public String getDescription() {
		return description;
	}
	
	/*
	 * Chequea si la alerta ya expiro comparandola con la fecha del momento en el que se utilice el metodo
	 */
	public boolean isExpired() {
		if (this.getExpire() != null) {
			LocalDate now = LocalDate.now();
			if (this.expire.compareTo(now) < 0) {
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * Indica a quien va dirigida la alerta
	 */
	public String getDestinatary() {
		return "para todos los usuarios";
	}
	
	/*
	 * Chequea si la alerta es urgente
	 */
	public boolean isUrgent() {
		return this.urgent;
	}

	@Override
	public String toString() {
		if (this.getExpire() == null) {
			return "[id=" + id + ", created=" + this.getCreated() + ", urgent=" + urgent + ", topic=" + topic + ", description="
					+ description + ", destinatary=" + this.getDestinatary() + "]";
		}
		else
			return "[id=" + id + ", created=" + this.getCreated() + ", expire=" + expire + ", urgent=" + urgent + ", topic=" + topic + ", description="
				+ description + ", destinatary=" + this.getDestinatary() + "]";
	}

	@Override
	/*
	 * Implemente el compareTo para poder ordenar las alertas por fecha de creacion
	 */
	public int compareTo(Alert a) {
		int num =  this.getCreated().compareTo(a.getCreated());
		if (num == 0) {
			return this.getId() - a.getId();
		}
		else
			return num;
	}
	
	
}
