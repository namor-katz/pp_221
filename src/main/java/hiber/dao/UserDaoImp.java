package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUserByCar() {
      //SELECT u.name, u.last_name FROM users u INNER JOIN cars c ON u.car_id = c.id WHERE c.name = "Volkswagen" AND series = "568";
      List users = new ArrayList();
      String hql = "select c.user_link FROM Car c fetch all properties";
      Session session = sessionFactory.getCurrentSession();
      Query query = session.createQuery(hql);
      User user = (User) query.list().get(0);

      return user;
   }

}
