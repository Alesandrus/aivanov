package ru.job4j.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>CRUD</title></head><body>");
        out.println("<center>\n" +
                "\t\t\t\t\t\t<form action=\"userServlet\" method=\"GET\">\n" +
                "\t\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t\t<caption>Получить пользователя</caption>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>Login</td>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"login\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td colspan=2 align=\"center\"><input type=\"submit\" value=\"get\"></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr><td><br></td></tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</form>\n" +

                "\t\t\t\t\t\t<form action=\"userServlet\" method=\"POST\">\n" +
                "\t\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t\t<caption>Создать пользователя</caption>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>Name</th>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"name\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>Login</td>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"login\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>Email</th>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"email\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td colspan=2 align=\"center\"><input type=\"submit\" value=\"create\"></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr><td><br></td></tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</form>\n" +
                "\t\t\t\t\t\t<form action=\"userServlet\" method=\"PUT\">\n" +
                "\t\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t\t<caption>Редактировать пользователя</caption>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>Login</td>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"login\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td colspan=2 align=\"center\">Данные для редактирования</td> \n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>New name</td>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"name\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>New login</td>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"newlogin\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>New email</td>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"email\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td colspan=2 align=\"center\"><input type=\"submit\" value=\"update\"></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr><td><br></td></tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</form>\n" +

                "\t\t\t\t\t\t<form action=\"userServlet\" method=\"DELETE\">\n" +
                "\t\t\t\t\t\t\t<table>\n" +
                "\t\t\t\t\t\t\t\t<caption>Удалить пользователя</caption>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td>Login</td>\n" +
                "\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"login\" ></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t<td colspan=2 align=\"center\"><input type=\"submit\" value=\"delete\"></td>\n" +
                "\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t<tr><td><br></td></tr>\n" +
                "\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t</form>\n" +

                "\t\t</center>");
        out.println("</body></html>");
    }
}
