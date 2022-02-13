import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlertSystemTest {
	
	static AlertSystem as;
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("beforeAll()");
		as = new AlertSystem();
		//Agregando topicos
		as.addTopic("cumpleaños");
		as.addTopic("clima");
		as.addTopic("deporte");
		//Agregando usuarios
		as.addUser("Juan Barthes");
		as.addUser("Marcos Sanchez");
		as.addUser("David Luciano Lagos");
		//Creando Alertas
		as.addAlert(null, LocalDate.of(2022, Month.MARCH, 12), false, as.getTopic(0), "Hoy es el cumpleaños de Federico!");
		as.addAlert(LocalDate.of(2021, Month.MARCH, 12), null, false, as.getTopic(1), "Tormenta electrica pronosticada para hoy");
		as.addAlert(LocalDate.of(2021, Month.AUGUST, 20), LocalDate.of(2021, Month.AUGUST, 24), true, as.getTopic(2), "Partido Copa America hoy a las 22:00hs");
		//Suscribiendo usuarios a topicos
		as.getUser(0).addTopic(as.getTopic(0));
		as.getUser(0).addTopic(as.getTopic(2));
		as.getUser(1).addTopic(as.getTopic(1));
		//Enviando alertas a los usuarios
		as.sendGeneralAlert(as.getAlert(2));
		as.sendGeneralAlert(as.getAlert(0));
		as.sendPrivateAlert(as.getUser(0), null, null, true, as.getTopic(1), "Dia Soleado pronosticado para hoy");
	}
	
	@Test
	public void getAlertsByTopicTest() {
		System.out.println("getAlertsByTopicTest()");
		ArrayList<Alert> alertsByTopic = as.getAlertsByTopic(as.getTopic(1));
		boolean correct = true;
		for (Alert alert : alertsByTopic) {
			if (!alert.getTopic().equals(as.getTopic(1))) {
				correct = false;
			}
		}
		assertTrue("Se retorno una alerta sobre un topico no solicitado", correct);
	}
	
	@Test
	public void isAllowedTest() {
		/*
		 * Realiza el chequeo la primera vez con una alerta que no deberia recibir el 
		 * usuario, y la segunda vez con una alerta urgente que debe recibirse si o si
		 */
		boolean result1 = as.isAllowed(as.getAlert(0), as.getUser(2));
		boolean result2 = as.isAllowed(as.getAlert(2), as.getUser(2));
		assertFalse("Error: esta alerta deberia ser rechazada debido a que el usuario no esta suscripto y no es urgente", result1);
		assertTrue("Error: esta alerta deberia haber sido aceptada debido a que es urgente", result2);
	}
}
