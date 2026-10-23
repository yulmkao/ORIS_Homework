package ru.itis.servlets;

import ru.itis.models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private List<User>  users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
        User userOne = User.builder()
                .id(1L)
                .name("yuli")
                .surname("oreshki")
                .age(20)
                .build();

        User userTwo = User.builder()
                .id(1L)
                .name("damir")
                .surname("g")
                .age(33)
                .build();

        User userThree = User.builder()
                .id(1L)
                .name("kisa")
                .surname("oreshki")
                .age(49)
                .build();

        users.add(userOne);
        users.add(userTwo);
        users.add(userThree);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
