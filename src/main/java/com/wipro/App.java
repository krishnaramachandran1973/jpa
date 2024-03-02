package com.wipro;

import java.time.LocalDate;
import java.util.Arrays;

import com.wipro.entity.Department;
import com.wipro.entity.Employee;
import com.wipro.entity.EmployeeType;
import com.wipro.entity.ParkingSpace;
import com.wipro.entity.Project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	private static final String PERSISTENCE_UNIT_NAME = "EmployeePU";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		log.info("Creating projects");
		Arrays.asList(Project.builder()
				.name("Banking")
				.build(),
				Project.builder()
						.name("Airline")
						.build())
				.forEach(proj -> em.persist(proj));

		log.info("Creating parking space");
		ParkingSpace parkingSpace = ParkingSpace.builder()
				.parkingSpot("1A")
				.build();
		em.persist(parkingSpace);

		log.info("Dreating Department");
		Department dept1 = Department.builder()
				.name("IT")
				.build();

		em.persist(dept1);

		Employee emp1 = Employee.builder()
				.name("Krishna")
				.salary(123435)
				.type(EmployeeType.FULL_TIME_EMPLOYEE)
				.startDate(LocalDate.now())
				.build();

		em.persist(emp1);

		transaction.commit();

		emp1 = em.find(Employee.class, 1);
		log.info("Employee {}", emp1);

		transaction.begin();
		emp1.setDepartment(dept1);
		emp1.setParkingSpace(parkingSpace);
		parkingSpace.setEmployee(emp1);
		dept1.getEmployees()
				.add(emp1);
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.name=?1", Project.class);
		Project project = query.setParameter(1, "Airline")
				.getSingleResult();
		emp1.getProjects()
				.add(project);
		project.getEmployees()
				.add(emp1);

		transaction.commit();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

		Root<Employee> emp = cq.from(Employee.class);
		CriteriaQuery<Employee> query1 = cq.select(emp)
				.where(cb.equal(emp.get("id"), 1));
		TypedQuery<Employee> empQuery = em.createQuery(query1);
		Employee singleResult = empQuery.getSingleResult();
		log.info("Using CriteriaBuilder : {}", singleResult);

		transaction.begin();
		em.remove(emp1);
		transaction.commit();
	}
}
