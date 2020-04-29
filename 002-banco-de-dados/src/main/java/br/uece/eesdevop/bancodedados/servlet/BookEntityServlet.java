package br.uece.eesdevop.bancodedados.servlet;

import br.uece.eesdevop.bancodedados.model.BookEntity;
import br.uece.eesdevop.bancodedados.util.GsonUtil;
import br.uece.eesdevop.bancodedados.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book_entities")
public class BookEntityServlet extends HttpServlet {

    private EntityManager entityManager;

    @Override
    public void init() {
        entityManager = HibernateUtil.INSTANCE.getEntityManagerFactory().createEntityManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        List<BookEntity> books = new ArrayList<>();

        if (entityManager != null && entityManager.isOpen()) {
            books = entityManager.createQuery("select b from BookEntity b", BookEntity.class).getResultList();
        }

        PrintWriter writer = resp.getWriter();
        for (BookEntity book : books) {
            writer.println(book.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BookRequest request = GsonUtil.INSTANCE.parse(req.getReader(), BookRequest.class);

        if (entityManager != null && entityManager.isOpen()) {
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();
                BookEntity entity = new BookEntity();
                entity.setTitle(request.title);
                entity.setAuthor(request.author);
                entityManager.persist(entity);
                entityManager.flush();
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw new RuntimeException("Unable to save the new book: " + e.getMessage());
            }

        }
    }

    @Override
    public void destroy() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    private static class BookRequest {

        final String title;
        final String author;

        public BookRequest(String title, String author) {
            this.title = title;
            this.author = author;
        }
    }

}
