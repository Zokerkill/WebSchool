package session;

import entity.History;
import entity.Person;
import entity.Subject;
import entity.Grade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "myschoolPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }

    public List<History> findPersons() {
        return em.createQuery("SELECT DISTINCT h.person FROM History h").getResultList();
    }
    
    public List<History> findGrades() {
        return em.createQuery("SELECT DISTINCT h.subject FROM History h").getResultList();
    }

}
