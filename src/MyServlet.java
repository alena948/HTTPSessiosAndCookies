import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Алёна on 18.03.2017.
 */
@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Parser parser = new Parser();
        PrintWriter out = response.getWriter();
        StringBuilder builder = new StringBuilder("<html>");
        String inputLogin = request.getParameter("login");
        String inputPassword = request.getParameter("password");
        ArrayList<String> data = parser.parse(new File("web/users.txt"));
        String[] storageData = (String[]) data.toArray();
        boolean isEqual = false;
        for (int i = 0; i < storageData.length; i++) {
            if (storageData[i] == inputLogin) {
                isEqual = true;
                if (storageData[i+1] == inputPassword)
                    response.sendRedirect("Welcome Page.html");
                else response.sendRedirect("Error!");
            }
        }
        if (isEqual == false) {
            response.sendRedirect("create.html");
            //считать новый логин и добавить в файл
        }
        builder.append("</html>");
        out.println(builder);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
