package se.secure.foliechatt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import se.secure.foliechatt.domain.LoginAttemptDTO;
import se.secure.foliechatt.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository repo;
    @Autowired
    EntityManager em;

    public User saveUser(User user) {
        // TODO hash/salt handling
        return repo.save(user);
    }


    public List<User> getAll() {
        return repo.findAll();
    }

    public Boolean deleteUser(Long id) {
        Boolean userExists = repo.findOne(id) != null;
        if (userExists) {
            repo.delete(id);
        }
        return userExists;
    }

    public boolean isAuthorizedForLogin(LoginAttemptDTO loginAttempt) {
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", loginAttempt.getEmail());
        List<User> result = query.getResultList();

        System.out.println("result size is: " + result.size());
        // edge cases
        if(result.isEmpty()) {
            return false;
        } else if(result.size() > 1) {
            throw new RuntimeException("");
        }

        // actual password check
        System.out.println("result pass = " + result.get(0).getPassword());
        System.out.println("try pass = " + loginAttempt.getPassword());
        return result.get(0).getPassword().equals(loginAttempt.getPassword());
    }

}
