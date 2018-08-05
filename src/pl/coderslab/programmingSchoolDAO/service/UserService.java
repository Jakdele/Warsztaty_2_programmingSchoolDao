package pl.coderslab.programmingSchoolDAO.service;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.programmingSchoolDAO.entity.User;

public class UserService {
    public static User hashPassword(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return user;
    }
}
