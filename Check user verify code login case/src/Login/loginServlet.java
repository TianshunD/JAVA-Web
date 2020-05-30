package Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        Object veriCode = request.getSession().getAttribute("veriCode");
        String veri_str = veriCode.toString();

        if (veri_str.equalsIgnoreCase(checkCode)) {
            //check username and password then
            if ("cass".equals(username) && "cass01".equals(password)) {
                request.getSession().setAttribute("user",username);
                response.sendRedirect(request.getContextPath() + "/success.jsp");

            } else {
                request.setAttribute("user_error","check username or password error"); //request field
                request.getRequestDispatcher("/Login.jsp").forward(request,response);
            }
        } else {
            request.setAttribute("cc_error","check code error"); //request field
            request.getRequestDispatcher("/Login.jsp").forward(request,response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
