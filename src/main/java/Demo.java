import demo.Event;
import org.hibernate.Session;
import utils.HibernateUtility;

import java.util.Date;

/**
 * Created by ant on 2015/7/5.
 */
public class Demo {
    public static void main(String[] args) {
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event event = new Event();
        event.setTitle("hello hibernate");
        event.setDate(new Date());
        session.save(event);

        session.getTransaction().commit();

        System.out.println("done!");
    }
}
