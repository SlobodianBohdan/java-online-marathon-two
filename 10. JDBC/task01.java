// Data Base


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MyDataBase {

    private Connection connection;
    private Statement statement;
    private String schemaName;

    public Connection createConnection() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/test_db", "************", "************");
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = null;
    }

    public void createStatement() throws SQLException {
        if (statement == null || statement.isClosed()) {
            statement = connection.createStatement();
        }
        statement = connection.createStatement();
    }

    public void closeStatement() throws SQLException {
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
        statement = null;
    }

    public void createSchema(String schemaName) throws SQLException {
        final String SQL = "CREATE SCHEMA IF NOT EXISTS %s;";
        this.schemaName = schemaName;
        statement.executeUpdate(String.format(SQL, this.schemaName));
    }

    public void dropSchema() throws SQLException {
        final String SQL = "DROP SCHEMA IF EXISTS %s;";
        statement.executeUpdate(String.format(SQL, schemaName));
    }

    public void useSchema() throws SQLException {
        final String SQL = "SET SCHEMA %s;";
        statement.executeUpdate(String.format(SQL, schemaName));
    }

    public void createTableRoles() throws SQLException {
        statement.executeUpdate(
                "CREATE TABLE Roles (" +
                        "id SERIAL, " +
                        "roleName VARCHAR(20) NOT NULL, " +
                        "PRIMARY KEY (id) );"
        );
    }

    public void createTableDirections() throws SQLException {
        statement.executeUpdate(
                "CREATE TABLE Directions (" +
                        "id SERIAL, " +
                        "directionName VARCHAR(30) NOT NULL, " +
                        "PRIMARY KEY (id));"
        );
    }

    public void createTableProjects() throws SQLException {
        statement.executeUpdate(
                "CREATE TABLE Projects (" +
                        "id SERIAL, " +
                        "projectName VARCHAR(30) NOT NULL, " +
                        "directionId INT, " +
                        "PRIMARY KEY (id), " +
                        "FOREIGN KEY (directionId) REFERENCES Directions (id));"
        );
    }

    public void createTableEmployee() throws SQLException {
        statement.executeUpdate(
                "CREATE TABLE Employee (" +
                        "id SERIAL, " +
                        "firstName VARCHAR(30) NOT NULL, " +
                        "roleId INT, " +
                        "projectId INT, " +
                        "PRIMARY KEY (id), " +
                        "FOREIGN KEY (roleId) REFERENCES Roles (id), " +
                        "FOREIGN KEY (projectId) REFERENCES Projects (id));"
        );
    }

    public void dropTable(String tableName) throws SQLException {
        final String SQL = "DROP TABLE IF EXISTS %s;";
        statement.executeUpdate(String.format(SQL, tableName));
    }

    public void insertTableRoles(String roleName) throws SQLException {
        final String SQL = "INSERT INTO Roles (roleName) VALUES (%s);";
        roleName = statement.enquoteLiteral(roleName);
        statement.executeUpdate(String.format(SQL, roleName));
    }
    public void insertTableDirections(String directionName) throws SQLException {
        final String SQL = "INSERT INTO Directions (directionName) VALUES (%s);";
        directionName = statement.enquoteLiteral(directionName);
        statement.executeUpdate(String.format(SQL, directionName));
    }

    public void insertTableProjects(String projectName, String directionName) throws SQLException {
        final String SQL = "INSERT INTO Projects (projectName, directionId) VALUES (%s, %d);";
        projectName = statement.enquoteLiteral(projectName);
        int directionId = getDirectionById(directionName);
        statement.executeUpdate(String.format(SQL, projectName, directionId));
    }

    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
        final String SQL = "INSERT INTO Employee (firstName, roleId, projectId) VALUES (%s, %d, %d);";
        firstName = statement.enquoteLiteral(firstName);
        int roleId = getRoleById(roleName);
        int projectId = getProjectById(projectName);
        statement.executeUpdate(String.format(SQL, firstName, roleId, projectId));
    }

    public int getRoleById(String roleName) throws SQLException {
        final String SQL = "SELECT id FROM Roles WHERE roleName = %s;";
        roleName = statement.enquoteLiteral(roleName);
        return getById(String.format(SQL, roleName));
    }

    public int getDirectionById(String directionName) throws SQLException {
        final String SQL = "SELECT id FROM Directions WHERE directionName = %s;";
        directionName = statement.enquoteLiteral(directionName);
        return getById(String.format(SQL, directionName));
    }

    public int getProjectById(String projectName) throws SQLException {
        final String SQL = "SELECT id FROM Projects WHERE projectName = %s;";
        projectName = statement.enquoteLiteral(projectName);
        return getById(String.format(SQL, projectName));
    }

    public int getEmployeeById(String firstName) throws SQLException {
        final String SQL = "SELECT id FROM Employee WHERE firstName = %s;";
        firstName = statement.enquoteLiteral(firstName);
        return getById(String.format(SQL, firstName));
    }

    public List<String> getAllRoles() throws SQLException {
        final String SQL = "SELECT roleName FROM Roles;";
        return getSingleColumnOfStrings(SQL);
    }

    public List<String> getAllDirestion() throws SQLException {
        final String SQL = "SELECT directionName FROM Directions;";
        return getSingleColumnOfStrings(SQL);
    }

    public List<String> getAllProjects() throws SQLException {
        final String SQL = "SELECT projectName FROM Projects;";
        return getSingleColumnOfStrings(SQL);
    }

    public List<String> getAllEmployee() throws SQLException {
        final String SQL = "SELECT firstName FROM Employee;";
        return getSingleColumnOfStrings(SQL);
    }

    public List<String> getAllDevelopers() throws SQLException {
        final String SQL = "SELECT firstName " +
                "FROM Employee " +
                "INNER JOIN Roles ON Employee.roleId = Roles.id " +
                "WHERE Roles.roleName = 'Developer';";
        return getSingleColumnOfStrings(SQL);
    }

    public List<String> getAllJavaProjects() throws SQLException {
        final String SQL = "SELECT projectName " +
                "FROM Projects " +
                "INNER JOIN Directions ON Projects.directionId = Directions.id " +
                "WHERE directionName = 'Java';";
        return getSingleColumnOfStrings(SQL);
    }

    public List<String> getAllJavaDevelopers() throws SQLException {
        final String SQL = "SELECT Employee.firstName " +
                "FROM Employee " +
                "WHERE roleId IN " +
                "(SELECT id FROM Roles " +
                "WHERE Roles.roleName = 'Developer') " +
                "AND projectId IN " +
                "(SELECT Projects.id FROM Projects " +
                "INNER JOIN Directions ON Projects.directionId = Directions.id " +
                "WHERE Directions.directionName = 'Java');";
        return getSingleColumnOfStrings(SQL);
    }

    private int getById(String sql) throws SQLException {
        int result;
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            } else {
                throw new SQLException("Attempt to retrieve non-existent data: " + sql);
            }
        }
        return result;
    }

    private List<String> getSingleColumnOfStrings(String sql) throws SQLException {
        List<String> result = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        }
        return result;
    }
}


// Tests Data Base

public class RunnerDataBase {
    public static void main(String[] args) {
        try {
            MyDataBase dataBase = new MyDataBase();
            dataBase.createConnection();
            dataBase.createStatement();

            dataBase.createSchema("public");
            dataBase.createTableRoles();
            dataBase.createTableDirections();
            dataBase.createTableProjects();
            dataBase.createTableEmployee();

            dataBase.insertTableRoles("Developer");
            dataBase.insertTableRoles("DevOps");
            dataBase.insertTableRoles("Project Manager");

            dataBase.insertTableDirections("Java");
            dataBase.insertTableDirections("Python");
            dataBase.insertTableDirections("Ruby");

            dataBase.insertTableProjects("MoonLight", "Java");
            dataBase.insertTableProjects("Sun", "Java");
            dataBase.insertTableProjects("Mars", "Python");

            dataBase.insertTableEmployee("Ivan", "Developer", "MoonLight");
            dataBase.insertTableEmployee("Petro", "Developer", "Sun");
            dataBase.insertTableEmployee("Stepan", "Developer", "Mars");
            dataBase.insertTableEmployee("Andriy", "DevOps", "MoonLight");
            dataBase.insertTableEmployee("Vasyl", "DevOps", "Mars");
            dataBase.insertTableEmployee("Ira", "Developer", "MoonLight");
            dataBase.insertTableEmployee("Anna", "Project Manager", "MoonLight");
            dataBase.insertTableEmployee("Olha", "Project Manager", "Sun");
            dataBase.insertTableEmployee("Maria", "Project Manager", "Mars");

            String out1 = dataBase.getAllRoles().toString();
            System.out.println((out1.equals("[Developer, DevOps, Project Manager]") ? "Right " : "Error ") + out1);

            String out2 = dataBase.getAllDirestion().toString();
            System.out.println((out2.equals("[Java, Python, Ruby]") ? "Right " : "Error ") + out2);

            String out3 = dataBase.getAllProjects().toString();
            System.out.println((out3.equals("[MoonLight, Sun, Mars]") ? "Right " : "Error ") + out3);

            String out4 = dataBase.getAllEmployee().toString();
            System.out.println((out4.equals("[Ivan, Petro, Stepan, Andriy, Vasyl, Ira, Anna, Olha, Maria]")
                    ? "Right" : "Error") + " " + out4);

            String out5 = dataBase.getAllDevelopers().toString();
            System.out.println((out5.equals("[Ivan, Petro, Stepan, Ira]") ? "Right " : "Error ") + out5);

            String out6 = dataBase.getAllJavaProjects().toString();
            System.out.println((out6.equals("[MoonLight, Sun]") ? "Right " : "Error ") + out6);

            String out7 = dataBase.getAllJavaDevelopers().toString();
            System.out.println((out7.equals("[Ivan, Petro, Ira]") ? "Right " : "Error ") + out7);

            dataBase.dropTable("Employee");
            dataBase.dropTable("Projects");
            dataBase.dropTable("Directions");
            dataBase.dropTable("Roles");
            dataBase.dropSchema();
            dataBase.closeStatement();
            dataBase.closeConnection();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " +
                        ((SQLException)e).getSQLState());

                System.err.println("Error Code: " +
                        ((SQLException)e).getErrorCode());

                System.err.println("Message: " + e.getMessage());

                Throwable t = ex.getCause();
                while(t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

