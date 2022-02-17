package com.classroom.international.telus.dsu.weatherrestserver.resources;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.international.telus.dsu.weatherrestserver.vo.Weather;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author geovanni.santos
 */
/**
 * Servlet implementation class WeatherController
 */
@WebServlet(name="service",urlPatterns = {"/Weather"})
public class Weatherbo extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ArrayList<Weather> arrWeather = new ArrayList<>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Weatherbo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param request
     * @param response
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
		response.setContentType("application/json");
		
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    @Override
    public String toString() {
        return "[" + arrWeather + "," + arrWeather + ']';
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        arrWeather.clear();
         response.setContentType("application/json");
        String countries = request.getParameter("countries");
        String dates = request.getParameter("dates");
        String cities = request.getParameter("cities");

        if (countries == null || dates == null || cities == null) {
            response.getWriter().append("Error, you have to send countries, dates and cities").append("\n");
            return;

        }

        String[] arrCountries = countries.split(",");
        String[] arrDates = dates.split(",");
        String[] arrCities = cities.split(",");

        if (arrCountries.length != arrDates.length || arrDates.length != arrCities.length) {
            response.getWriter().append("Error, You have to enter the same number of elements in countries, dates and cities");
            return;
        }

        for (int a = 0; a < arrCountries.length; a++) {
            arrWeather.add(new Weather(arrCities[a], arrCountries[a], arrDates[a]));
        }

        response.getWriter().append(arrWeather.toString());
    }

}
