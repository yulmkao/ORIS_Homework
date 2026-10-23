package ru.itis.servlets;

import ru.itis.models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "kisa123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String COUNT_SQL = "SELECT COUNT(*) FROM drivers";
    private static final String SQL_INSERT = "INSERT INTO drivers (first_name, last_name, age, email, city, phone) VALUES ('";
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_SQL)) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (count == 0) {
            count = 1;
        }
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(i);
        }
        request.setAttribute("numbersForJsp", numbers);
        request.getRequestDispatcher("/jsp/save.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String countParam = request.getParameter("count");
        int count = Integer.parseInt(countParam);
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String name = request.getParameter("name_" + i);
            String surname = request.getParameter("surname_" + i);
            String ageStr = request.getParameter("age_" + i);
            int age = 0;
            if (ageStr != null && !ageStr.isEmpty()) {
                age = Integer.parseInt(ageStr);
            }
            String email = request.getParameter("email_" + i);
            String city = request.getParameter("city_" + i);
            String phone = request.getParameter("phone_" + i);
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .age(age)
                    .email(email)
                    .city(city)
                    .phone(phone)
                    .build();
            users.add(user);
        }
        try (Statement statement = connection.createStatement()) {
            for (User user : users) {
                String sql = SQL_INSERT
                        + user.getName() + "', '"
                        + user.getSurname() + "', "
                        + user.getAge() + ", '"
                        + user.getEmail() + "', '"
                        + user.getCity() + "', '"
                        + user.getPhone() + "')";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/html/added.html");
    }
}