package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("Волга", 21);
      user1.setCar(car1);
      userService.add(user1);

      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car2 = new Car("ВАЗ", 2103);
      user3.setCar(car2);
      userService.add(user3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car3 = new Car("Москвич", 412);
      user4.setCar(car3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<User> users2 = userService.listUsers("Волга", 21);
      for (User user : users2) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = "+user.getCar().getModel()+" "+user.getCar().getSeries());
         }
         System.out.println();
      }
      context.close();
   }
}
