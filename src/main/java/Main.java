import java.util.Iterator;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;

import com.otp.hibernate.pojo.Employee;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder
				.build());
		Session session = factory.openSession();
		
		Filter filter = session.enableFilter("employeeFilter");
		filter.setParameter("salary", 4000);
		Query query = session.createQuery("from Employee e");
		List list = query.list();
		Iterator it =list.iterator();
		while (it.hasNext()) {
			Employee emp = (Employee) it.next();
			System.out.println("Employee Name : "+emp.getEmployeeName() +" , Salary : "+emp.getSalary());
		}	
	}

}
