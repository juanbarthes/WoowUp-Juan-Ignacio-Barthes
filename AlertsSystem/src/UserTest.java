

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	String topic;
	public User user;
	UserAlert alert;
	UserAlert alert2;
	UserAlert alert3;
	
	@BeforeEach
	public void before() {
		System.out.println("before()");
		topic = "cumpleaños";
		user = new User(0, "Juan");
		alert = new UserAlert(0, LocalDate.of(2021, Month.DECEMBER, 20), null, false, topic, "Hoy es el cumpleaños de alguien!");
		alert2 = new UserAlert(1, LocalDate.of(2022, Month.JANUARY, 20), LocalDate.of(2022, Month.JANUARY, 22), false, topic, "Hoy es el cumpleaños de alguien!");
		alert3 = new UserAlert(2, LocalDate.of(2022, Month.FEBRUARY, 10), null, false, topic, "Hoy es el cumpleaños de alguien!");
	}
	
	@Test
	public void addAlertTest() {
		System.out.println("addAlertTest()");
		this.user.addAlert(alert);
		assertTrue("No se agrego la alerta", user.getAlerts().size() > 0);
	}

	@Test
	public void markReadedalertTest() {
		System.out.println("mardReadedAlertTest()");
		user.addAlert(alert);
		user.markReadedAlert(0);
		assertTrue("La alerta no fue leida", user.getAlert(0).isReaded());
	}
	
	@Test
	public void getUnreadAlertsTest() {
		user.addAlert(alert);
		user.addAlert(alert2);
		user.addAlert(alert3);
		user.markReadedAlert(0);
		ArrayList<UserAlert> unreadAlerts = user.getUnreadAlerts();
		int expectedSize = 1;
		int res = unreadAlerts.size();
		assertEquals(expectedSize, res);
	}
}
