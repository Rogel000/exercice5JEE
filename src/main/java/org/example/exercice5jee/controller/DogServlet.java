package org.example.exercice5jee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exercice5jee.entity.Dog;
import org.example.exercice5jee.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(value = {"/list", "/add", "/dogs/*"})
public class DogServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DogServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ok get");
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
        System.out.println("ok liste");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Dog> dogs = session.createQuery("from Dog", Dog.class).list();
            req.setAttribute("dogs", dogs);
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching dogs", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching dogs");
        }
    }

    private void forwardToAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    private void viewDogDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        logger.info("Received request to view dog details with ID: " + idParam);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            long id = Long.parseLong(idParam);
            Dog dog = session.get(Dog.class, id);
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching dog details", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching dog details");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if ("/add".equals(path)) {
            handleAddDog(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid path");
        }
    }

    private void handleAddDog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthdate"));

        Dog dog = new Dog(name, breed, birthDate);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(dog);
            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding dog", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding dog");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
