import demo.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HibernateUtility;

import java.util.Date;
import java.util.List;

/**
 * Created by ant on 2015/7/5.
 */
public class EventTest {
    private Session session;
    private Transaction transaction;

    @Before
    public void setUp() {
        session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
//        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        session.getTransaction().commit();
//        transaction.commit();
//        session.close();
    }

    @Test
    public void testSave() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setTitle("Hello" + i);
            event.setDate(new Date());
            session.save(event);
        }
    }

    @Test
    public void testList() throws Exception {
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setTitle("Hello" + i);
            event.setDate(new Date());
            session.save(event);
        }

        List events = listEvents();
        for (Object obj : events) {
            Event event = (Event) obj;
            System.out.println("Event: " + event.getTitle());
        }
    }

    private List listEvents() {
        List result = session.createQuery("from Event").list();
        return result;
    }
}
