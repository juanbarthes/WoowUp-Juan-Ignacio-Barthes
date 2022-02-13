import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlertSystem as = new AlertSystem();
		//Añadiendo algunos topicos
		as.addTopic("cumpleaños");
		as.addTopic("clima");
		as.addTopic("deporte");
		//Imprimo la lista de topicos del sistema
		System.out.println("Lista de topicos del sistema de alertas:");
		System.out.println(as.getTopics() + "\n");
		
		//Añadiendo algunos usuarios
		as.addUser("Juan Barthes");
		as.addUser("Marcos Sanchez");
		as.addUser("David Luciano Lagos");
		//Imprimo la lista de usuarios
		System.out.println("Lista de usuarios cargados en el sistema de alertas:");
		System.out.println(as.getUsers() + "\n");
		
		//Suscribiendo usuarios a topicos
		as.getUser(0).addTopic(as.getTopic(0));
		as.getUser(0).addTopic(as.getTopic(2));
		as.getUser(1).addTopic(as.getTopic(1));
		//Imprimo la lista de topicos de cada usuario
		System.out.println("Lista de topicos a los que esta suscripto cada usuario:");
		for (int i = 0; i < as.getUsers().size(); i++) {
			User user = as.getUser(i);
			System.out.println("Usuario= " + user.getName());
			System.out.println("Topicos= " + user.getTopics() + "\n");
		}
		
		//Creando Alertas
		as.addAlert(null, LocalDate.of(2022, Month.MARCH, 12), false, as.getTopic(0), "Hoy es el cumpleaños de Federico!");
		as.addAlert(LocalDate.of(2021, Month.MARCH, 12), null, false, as.getTopic(1), "Tormenta electrica pronosticada para hoy");
		as.addAlert(LocalDate.of(2021, Month.AUGUST, 20), LocalDate.of(2021, Month.AUGUST, 24), true, as.getTopic(2), "Partido Copa America hoy a las 22:00hs");
		//Enviando alertas a los usuarios
		as.sendGeneralAlert(as.getAlert(2));
		as.sendGeneralAlert(as.getAlert(0));
		as.sendPrivateAlert(as.getUser(0), null, null, true, as.getTopic(1), "Dia Soleado pronosticado para hoy");
		
		//Imprimo las alertas que recibio cada usuario
		System.out.println("Lista de notificaciones de cada usuario:");
		for (User user : as.getUsers()) {
			System.out.println(user.getName());
			for (UserAlert alert : user.getAlerts()) {
				System.out.println(alert);
			}
		}
		System.out.println("\n");
		
		//Recuperando Alertas de un usuario sin leer y sin expirar
		System.out.println("Alertas del usuario Juan sin leer ni expirar, ordenadas por fecha de creacion de forma descendente:");
		ArrayList<UserAlert> juanAlerts = as.getUser(0).getUnreadAlerts();
		for (UserAlert userAlert : juanAlerts) {
			System.out.println(userAlert.toString() + "\n");
		}
		System.out.println("\n");
		
		//Recuperando alertas del sistema del mismo topico
		System.out.println("Alertas sobre un topico especifico, sin expirar y ordenadas por fecha de creacion de forma descendente");
		ArrayList<Alert> alertsByTopic = as.getAlertsByTopic(as.getTopic(1));
		for (Alert alert : alertsByTopic) {
			System.out.println(alert.toString() + "\n");
		}
		
	}
}
