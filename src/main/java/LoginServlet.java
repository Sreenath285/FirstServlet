import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Admin"),
                @WebInitParam(name = "password", value = "123456")
        }
    )
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get request params for user and password
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");
        // get servlet config init params
        String userName = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        if (userName.equals(user) && password.equals(pwd)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("<font color=red>Either user name or password is wrong</font>");
            requestDispatcher.include(req, resp);
        }
    }
}
