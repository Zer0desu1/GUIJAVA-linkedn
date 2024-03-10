// Users.java Author: Vural Bilgin ID: 22095034
// Includes users infos, attibutes and methods- retrive data from DATABASE
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users implements UserI{
    private String name,surname,password,email,job;


   
    public void viewProfile(Users user) {
        System.out.println("Name: " + user.getName());
        System.out.println("Surname: " + user.getSurname());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        System.out.println("------------------------");
    }

   
    public void editProfile(Users user) {
        
    }

   
    public void uploadProfilePhoto() {
        
    }

    public Users(String name, String surname, String password, String email,String job) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.job=job;

        //insertIntoDatabase();
        
    }
public void insertIntoDatabase(Users user) {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Linkedn",
                    "postgres",
                    "0539"
            );

            // Prepare the SQL query
            String query = "INSERT INTO users (name, surname, password, email, job) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.name);
                preparedStatement.setString(2, user.surname);
                preparedStatement.setString(3, user.password);
                preparedStatement.setString(4, user.email);
                preparedStatement.setString(5, user.job);

                // Execute the query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User inserted into the database successfully.");
                } else {
                    System.out.println("Failed to insert user into the database.");
                }
            }

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Users> getAllUsersFromDatabase() {// Retrive all the users from database
        List<Users> users = new ArrayList<>();

        try {// try to connect to db
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Linkedn",
                    "postgres",
                    "0539"
            );

            // Prepare the SQL query
            String query = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) { //try to retrive data
                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the result set and create UserA objects
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String job = resultSet.getString("job");

                    Users user = new Users(name, surname, password, email, job);
                    users.add(user);
                }
            }

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return users;
    }
    


    //GEtters and Setters
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getJob() {
        return job;
    }
    public void setJob(String job) {
    this.job=job;
    }

    //Update the changed data about users
public static void updateUserData(Users user,String newName,String newSurname,String newPassword,String newEmail,String newJob) {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Linkedn",
                    "postgres",
                    "0539"
            );

            // Prepare the SQL query
            String query = "UPDATE users SET name=?, surname=?, email=?, password=?, job=? WHERE name=? AND surname=? AND email=? AND job=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, newSurname);
                preparedStatement.setString(3, newEmail);
                preparedStatement.setString(4, newPassword);
                preparedStatement.setString(5, newJob);
                preparedStatement.setString(6, user.name);
                preparedStatement.setString(7, user.surname);
                preparedStatement.setString(8, user.email);
                preparedStatement.setString(9, user.job);

                // Execute the query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User inserted into the database successfully.");
                } else {
                    System.out.println("Failed to insert user into the database.");
                }
            }

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

public static void updateJob(Users user,String newJob) {// update only job data
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Linkedn",
                    "postgres",
                    "0539"
            );

            // Prepare the SQL query
            String query = "UPDATE users SET  job=? WHERE name=? AND surname=? AND email=? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                
                preparedStatement.setString(1, newJob);
                preparedStatement.setString(2, user.name);
                preparedStatement.setString(3, user.surname);
                preparedStatement.setString(4, user.email);
                

                // Execute the query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User inserted into the database successfully.");
                } else {
                    System.out.println("Failed to insert user into the database.");
                }
            }

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    
    
}