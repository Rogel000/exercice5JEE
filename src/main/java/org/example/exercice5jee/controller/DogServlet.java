package org.example.exercice5jee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exercice5jee.entity.Dog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@WebServlet(value = "/dogs/*")
public class DogServlet extends HttpServlet {
    private List<Dog> dogs;
    private AtomicLong idCounter;
    private static final Logger logger = Logger.getLogger("DogServlet");

    @Override
    public void init() {
        dogs = new ArrayList<>();
        idCounter = new AtomicLong();
        dogs.add(new Dog(idCounter.incrementAndGet(), "Adam", "Main Coon", LocalDate.of(2000, 1, 12)));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dogs", dogs);
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid path");
            return;
        }
        switch (pathInfo) {
            case "/list":
                forwardToList(req, resp);
                break;
            case "/add":
                forwardToAddForm(req, resp);
                break;
            case "/view":
                viewDogDetails(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid path");
                break;
        }
    }

    private void forwardToList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    private void forwardToAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    private void viewDogDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        logger.info("Received request to view dog details with ID: " + idParam);
        try {
            long id = Long.parseLong(idParam);
            Dog dog = dogs.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
            if (dog != null) {
                req.setAttribute("dog", dog);
                req.getRequestDispatcher("/view.jsp").forward(req, resp);
            } else {
                logger.warning("Dog not found with ID: " + id);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Dog not found");
            }
        } catch (NumberFormatException e) {
            logger.severe("Invalid dog ID: " + idParam);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid dog ID");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));

        Dog dog = new Dog(idCounter.incrementAndGet(), name, breed, birthDate);
        dogs.add(dog);
        resp.sendRedirect(req.getContextPath() + "/dogs/list");
    }
}
