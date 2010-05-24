package quickstart.action;

import java.util.List;

import quickstart.model.Person;
import quickstart.dao.PersonDao;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class PersonAction{



    private PersonDao dao;
    private List<Person> persons;
    private Person person;
    private Integer id;

    public PersonAction(PersonDao dao) {
        this.dao = dao;
    }


    @Action(
            value="/list",
            results={
                    @Result(name=QuickstartAction.SUCCESS,location="pages/list.jsp"),
                    @Result(name=QuickstartAction.INPUT,location="pages/list.jsp")
            }
    )
    public String execute() {
        this.persons = dao.findAll();
        return QuickstartAction.SUCCESS;
    }

    @Action(
            value="/",
            results={
                    @Result(location="/index.html")
            }
    )
    public String list() {
        return execute();
    }

    @Action(
            value="/save",
            results={
                    @Result(name=QuickstartAction.SUCCESS,location="/pages/list.jsp"),
                    @Result(name=QuickstartAction.INPUT,location="/pages/list.jsp")
            }
    )
    public String save() {
        this.dao.save(person);
        this.person = new Person();
        return execute();
    }

    @Action(
            value="/remove",
            results={
                    @Result(name=QuickstartAction.SUCCESS,location="/pages/list.jsp"),
                    @Result(name=QuickstartAction.INPUT,location="/pages/list.jsp")
            }
    )
    public String remove() {
        dao.remove(id);
        return execute();
    }


    public List<Person> getPersons() {
        return persons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void prepare() throws Exception {
        if (id != null)
            person = dao.find(id);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
