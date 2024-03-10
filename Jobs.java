// Jobs.java Author: Vural Bilgin ID: 22095034
// Includes job infos, attibutes and methods- retrive data from DATABASE

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Jobs {
    private String name,company,description,location;
    private int salary;
    private LocalDate date;

    //GEtters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }





    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public Jobs(String name,String company,int salary,String description,String location,LocalDate date){
        this.name=name;
        this.company=company;
        this.description=description;
        this.location=location;
        this.salary=salary;
        this.date=date;
    }


    // Method to retrieve all jobs from the database
    public static List<Jobs> getAllJobsFromDatabase() {
        List<Jobs> jobs = new ArrayList<>();

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
            String query = "SELECT * FROM Job";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the result set and create Job objects
                while (resultSet.next()) {
                    // Retrieve data from the result set
                    String name = resultSet.getString("job_name");
                    String description = resultSet.getString("job_desc");
                    int salary = resultSet.getInt("salary");
                    String location = resultSet.getString("location");
                    LocalDate date = resultSet.getDate("post_date").toLocalDate();
                    String company = resultSet.getString("company");

                    // Create a Job object and add it to the list
                    Jobs job = new Jobs(name, company, salary, description, location, date);
                    jobs.add(job);
                }
            }

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return jobs;  // return the list full of jobs
    }

    // Method to insert a job into the database
    public static void insertIntoDatabase(Jobs job) {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Linkedn",
                    "postgres",
                    "0539"
            );

            // Prepare the SQL query for insertion
            String query = "INSERT INTO job (job_name, company, job_desc, salary, location, post_date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set values for the parameters in the prepared statement
                preparedStatement.setString(1, job.name);
                preparedStatement.setString(2, job.company);
                preparedStatement.setString(3, job.description);
                preparedStatement.setInt(4, job.salary);
                preparedStatement.setString(5, job.location);

                // Convert LocalDate to SQL Date and set it in the prepared statement
                LocalDate localDate = job.getDate();
                java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                preparedStatement.setDate(6, sqlDate);

                // Execute the query
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("Job inserted into the database successfully.");
                } else {
                    System.out.println("Failed to insert job into the database.");
                }
            }

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
    

