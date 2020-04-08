package session;

import entity.Roles;
import entity.User;
import entity.UserRoles;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {

    @PersistenceContext(unitName = "myschoolPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }

    public List<Roles> findRolesByUser(User user) {
        try {
            return em.createQuery("SELECT ur.role FROM UserRoles ur WHERE ur.user = :user")
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public void removeRoles(User user) {
        em.createQuery("DELETE FROM UserRoles ur WHERE ur.user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }
    
}
