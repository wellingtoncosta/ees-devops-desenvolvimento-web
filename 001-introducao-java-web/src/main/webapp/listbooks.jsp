<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.uece.eesdevop.introducaojavaweb.Book" %>
<%--
  Created by IntelliJ IDEA.
  User: wellingtonpereira
  Date: 06/04/20
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List books</title>
    </head>
    <body>
        <h1>List of books:</h1>
        <br/>
        <table border="1px">
            <%
                List<Book> books = new ArrayList<>();

                books.add(new Book("Book One", "Lorem Ipsum"));
                books.add(new Book("Book Two", "Lorem Ipsum"));
                books.add(new Book("Book Three", "Lorem Ipsum"));

                for (Book book : books) { %>
                    <tr>
                        <td><%=book.getTitle() %></td>
                        <td><%=book.getAuthor() %></td>
                    </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
