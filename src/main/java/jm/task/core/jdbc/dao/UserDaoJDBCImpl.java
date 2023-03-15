package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = null;

        query = "CREATE TABLE IF NOT EXISTS `mydbtest`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));\n";

        try {connection = Util.getConnection();
            st = connection.prepareStatement(query);
            st.executeUpdate();
            System.out.println("БД users создана!");

            st.close();
            connection.close();
            //System.out.println("Подключение закрыто");

        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = null;

        query = "DROP TABLE IF EXISTS `mydbtest`.`users`";

        try {connection = Util.getConnection();
            st = connection.prepareStatement(query);
            st.executeUpdate();
            System.out.println("БД users удалена!");

            st.close();
            connection.close();
            //System.out.println("Подключение закрыто");

        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = null;

        query = "insert into `mydbtest`.`users` (name, lastName, age) values ('"+ name +"','"+ lastName + "'," + age + ")";


        try {connection = Util.getConnection();
            st = connection.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Челик " + name + " " + lastName + " добавлен");

            st.close();
            connection.close();
            //System.out.println("Подключение закрыто");
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = null;

        query = "DELETE FROM `mydbtest`.`users` where ID="+id;

        try {connection = Util.getConnection();
            st = connection.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Челик с ID= " + id +" удален!");

            st.close();
            connection.close();
            //System.out.println("Подключение закрыто");

        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = null;

        List<User> userList = new ArrayList<>();

        query = "SELECT * FROM mydbtest.users;";


        try {connection = Util.getConnection();
            st = connection.prepareStatement(query);
            //st.executeUpdate();

            rs = st.executeQuery();
            System.out.println("Список всех Users получен!");

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));

                userList.add(user);
                System.out.println(user.toString());

            }

            rs.close();
            st.close();
            connection.close();
            //System.out.println("Подключение закрыто");


        }
        catch (Exception e) {
            throw new RuntimeException();
        }

        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = null;

        query = "TRUNCATE TABLE `mydbtest`.`users`";

        try {connection = Util.getConnection();
            st = connection.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Удалены все строки в таблице users!");

            st.close();
            connection.close();
            //System.out.println("Подключение закрыто");

        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
