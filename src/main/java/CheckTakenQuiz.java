import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;

@WebServlet("/CheckTakenQuiz")
public class CheckTakenQuiz extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String[]> pars = new HashMap<>(httpServletRequest.getParameterMap());
        Map<Integer, Map<String, String[]>> pairs = new HashMap<>();

        String startTimeStr = pars.get("startTime")[0];
        pars.remove("startTime");
        long startTime = Long.parseLong(startTimeStr);

        for(String key : pars.keySet()) {
            int n = getNumber(key);
        }

        /*for(String s : pars.keySet()) {
            System.out.print(s);
            System.out.print(": ");
            String[] arr = pars.get(s);
            for(int i = 0; i<arr.length; i++) {
                if(i != 0) {
                    System.out.print(", ");
                }
                System.out.print(arr[i]);
            }
            System.out.println(";");
        }*/
    }

    private int getNumber(String str) {
        str = str.substring(3);
        String number = str.substring(0, str.indexOf("_"));
        return Integer.parseInt(number);
    }

}
