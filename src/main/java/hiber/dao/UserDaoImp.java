package hiber.dao;

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

   public User getUserByCar(String name, int series) {
      List users = new ArrayList();
      String hql = "select c.user_link FROM Car c WHERE c.name = :name AND c.series = :series";

      Session session = sessionFactory.getCurrentSession();
      Query query = session.createQuery(hql);
      query.setParameter("name", name);
      query.setParameter("series", series);

      users = query.list();
      if (users.size() > 0) {
         return (User) users.get(0);
      }
      else {
         return new User("ни имени...", "ни фамилии...", "ни мейла...");
      }
   }
}
