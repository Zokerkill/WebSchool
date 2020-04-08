package util;

import entity.Roles;
import entity.User;
import entity.UserRoles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.RolesFacade;
import session.UserRolesFacade;

public class RoleManager {

    private RolesFacade rolesFacade;
    private UserRolesFacade userRolesFacade;
    private Roles roleAdmin;
    private Roles roleManager;
    private Roles roleUser;

    public RoleManager() {
        Context context;
        try {
            context = new InitialContext();
            this.rolesFacade = (RolesFacade) context.lookup("java:module/RolesFacade");
            this.userRolesFacade = (UserRolesFacade) context.lookup("java:module/UserRolesFacade");
            roleAdmin = rolesFacade.getRoleByName("ADMIN");
            roleManager = rolesFacade.getRoleByName("MANAGER");
            roleUser = rolesFacade.getRoleByName("USER");
        } catch (NamingException ex) {
            Logger.getLogger(RoleManager.class.getName()).log(Level.SEVERE, "Не удалось найти bin", ex);
        }
    }

    public boolean isRoleUser(String roleName, User user) {
        roleName = roleName.toUpperCase();
        Roles role = rolesFacade.getRoleByName(roleName);
        List<Roles> listRoles = userRolesFacade.findRolesByUser(user);
        if (null == role || null == listRoles) {
            return false;
        }
        return listRoles.contains(role);
    }

    public String getTopRoleName(User user) {
        List<Roles> userRoles = userRolesFacade.findRolesByUser(user);
        if (userRoles.contains(roleAdmin)) {
            return "ADMIN";
        }
        if (userRoles.contains(roleManager)) {
            return "MANAGER";
        }
        if (userRoles.contains(roleUser)) {
            return "USER";
        }
        return null;
    }

    public void setRoleUser(Roles role, User user) {
        this.removeAllRoles(user);
        UserRoles userRoles = new UserRoles();
        userRoles.setUser(user);
        if (role.equals(roleAdmin)) {
            userRoles.setRole(roleAdmin);
            userRolesFacade.create(userRoles);
            userRoles.setRole(roleManager);
            userRolesFacade.create(userRoles);
            userRoles.setRole(roleUser);
            userRolesFacade.create(userRoles);
        }
        if (role.equals(roleManager)) {
            userRoles.setRole(roleManager);
            userRolesFacade.create(userRoles);
            userRoles.setRole(roleUser);
            userRolesFacade.create(userRoles);
        }
        if (role.equals(roleUser)) {
            userRoles.setRole(roleUser);
            userRolesFacade.create(userRoles);
        }
    }

    public void removeAllRoles(User user) {
        userRolesFacade.removeRoles(user);
    }
}
