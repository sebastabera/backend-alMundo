package com.sebastabera.springboot.app;

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
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = CallCenterApplication.class
)
@AutoConfigureMockMvc
@TestConfiguration
@TestPropertySource(locations = "classpath:application-test.properties")
public class CallTest {
	
	@MockBean
	private IEmployeeDaoRepository employeeRepository;
	
	@MockBean
	private IPositionDaoRepository positionRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void call() throws Exception {
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
		Employee operador2 = new Employee("alejandro", "1", operador);
		Employee supervisor1 = new Employee("andrea", "1", supervisor);
		Employee supervisor2 = new Employee("daniela", "1", supervisor);
		Employee supervisor3 = new Employee("leandro", "1", supervisor);
		Employee director1 = new Employee("luis", "1", director);
		employeeRepository.save(operador1);
		employeeRepository.save(operador2);
		employeeRepository.save(supervisor1);
		employeeRepository.save(supervisor2);
		employeeRepository.save(supervisor3);
		employeeRepository.save(director1);
		
		
		
		Thread llamada1 = new Thread(new Dispatcher());
		Thread llamada2 = new Thread(new Dispatcher());
		Thread llamada3 = new Thread(new Dispatcher());
		Thread llamada4 = new Thread(new Dispatcher());
		Thread llamada5 = new Thread(new Dispatcher());
		Thread llamada6 = new Thread(new Dispatcher());
		Thread llamada7 = new Thread(new Dispatcher());
		Thread llamada8 = new Thread(new Dispatcher());
		Thread llamada9 = new Thread(new Dispatcher());
		Thread llamada10 = new Thread(new Dispatcher());
		
		llamada1.start();
		llamada2.start();
		llamada3.start();
		llamada4.start();
		llamada5.start();
		llamada6.start();
		llamada7.start();
		llamada8.start();
		llamada9.start();
		llamada10.start();
	}
}
