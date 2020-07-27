package Quiz;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "QuizServlet", urlPatterns = "/QuizServlet")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        ServletContext context = getServletContext();
        //todo
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }
}
