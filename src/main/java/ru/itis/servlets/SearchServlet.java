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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "kisa123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM drivers WHERE email = '";
    private static final String SELECT_BY_CITY = "SELECT * FROM drivers WHERE city = '";
    private static final String SELECT_BY_PHONE = "SELECT * FROM drivers WHERE phone = '";
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
        request.getRequestDispatcher("/jsp/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        if (email != null && !email.isEmpty()) {
            selectByEmail(email);
        }
        if (city != null && !city.isEmpty()) {
            selectByCity(city);
        }
        if (phone != null && !phone.isEmpty()) {
            selectByPhone(phone);
        }
        response.sendRedirect("/search");
    }

    private void selectByEmail(String email) {
        executeSelectQuery(SELECT_BY_EMAIL + email + "'");
    }

    private void selectByCity(String city) {
        executeSelectQuery(SELECT_BY_CITY + city + "'");
    }

    private void selectByPhone(String phone) {
        executeSelectQuery(SELECT_BY_PHONE + phone + "'");
    }

    private void executeSelectQuery(String sql) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("first_name"))
                        .surname(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .email(resultSet.getString("email"))
                        .city(resultSet.getString("city"))
                        .phone(resultSet.getString("phone"))
                        .build();
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}