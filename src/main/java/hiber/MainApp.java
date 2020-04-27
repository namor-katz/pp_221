package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Petrovich", "Ivanov", "schwain_petr@mail.ru", new Car("Oka", 154)));
      userService.add(new User("Ivan", "Govnov", "ivan@mail.gov", new Car("Zaz", 968)));

      CarService carService = context.getBean((CarService.class));
      userService.add(new User("Vasja", "Pupkin", "superVasja@ya.ru", new Car("Vaz", 777)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      List<Car> cars = carService.listCar();
      for (Car car : cars) {
         System.out.println("name Car is " + car.getName());
         System.out.println("car number is " + car.getSeries());
         System.out.println();
      }

      User userT = userService.getUserByCar("Vaz", 777);
      System.out.println("сейчас будет показан пользователь, владеющий Vaz 777");
      System.out.println("имя владельца " + userT.getLastName() + " а мыло " + userT.getEmail());
      context.close();
   }
}
