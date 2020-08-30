package pl.sda.serlvets.utilis;


import javax.servlet.http.HttpServletResponse;

public class ResponseUtilis {
    public static void setWriter(HttpServletResponse response){
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
    }
}
