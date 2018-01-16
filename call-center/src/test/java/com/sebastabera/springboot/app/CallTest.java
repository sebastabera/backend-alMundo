package com.sebastabera.springboot.app;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.sebastabera.springboot.app.dao.IEmployeeDaoRepository;
import com.sebastabera.springboot.app.dao.IPositionDaoRepository;
import com.sebastabera.springboot.app.models.entity.Employee;
import com.sebastabera.springboot.app.models.entity.Position;
import com.sebastabera.springboot.app.services.Dispatcher;
import com.sebastabera.springboot.app.services.EmployeeService;
import com.sebastabera.springboot.app.services.PositionService;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CallCenterApplication.class)
@TestConfiguration
@TestPropertySource(locations = "classpath:application-test.properties")
public class CallTest {

	@Autowired
	private IEmployeeDaoRepository employeeRepository;

	@Autowired
	private IPositionDaoRepository positionRepository;

	@Autowired
	private Dispatcher dispatcher;

	@Test
	public void call() {
		Position operador = new Position();
		operador.setName("operador");
		Position supervisor = new Position();
		supervisor.setName("supervisor");
		Position director = new Position();
		director.setName("director");
		positionRepository.save(operador);
		positionRepository.save(supervisor);
		positionRepository.save(director);

		Employee operador1 = new Employee("carlos", "1", operador);
		Employee operador2 = new Employee("alejandro", "2", operador);
		Employee supervisor1 = new Employee("andrea", "3", supervisor);
		Employee supervisor2 = new Employee("daniela", "4", supervisor);
		Employee supervisor3 = new Employee("leandro", "5", supervisor);
		Employee director1 = new Employee("luis", "6", director);
		employeeRepository.save(operador1);
		employeeRepository.save(operador2);
		employeeRepository.save(supervisor1);
		employeeRepository.save(supervisor2);
		employeeRepository.save(supervisor3);
		employeeRepository.save(director1);
		try {
			CountDownLatch cdl = new CountDownLatch(6);
			dispatcher.createCall(cdl);
			dispatcher.createCall(cdl);
			dispatcher.createCall(cdl);
			dispatcher.createCall(cdl);
			dispatcher.createCall(cdl);
			dispatcher.createCall(cdl);
			dispatcher.createCall(cdl);
			dispatcher.createCall(cdl);
			// CountDownLatch cdl = new CountDownLatch(1);

			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
