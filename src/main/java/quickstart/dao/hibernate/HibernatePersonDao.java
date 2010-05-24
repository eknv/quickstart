package quickstart.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import quickstart.dao.PersonDao;
import quickstart.model.Person;

import java.util.List;


@Repository("personDao")
@Transactional
public class HibernatePersonDao implements PersonDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Person");
        return query.list();
    }

    public void save(Person person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    public void remove(int id) {
        Person person = (Person) sessionFactory.getCurrentSession().get(
                Person.class, id);
        sessionFactory.getCurrentSession().delete(person);
    }

    public Person find(int id) {
        return (Person) sessionFactory.getCurrentSession().get(
                Person.class, id);
    }

}
