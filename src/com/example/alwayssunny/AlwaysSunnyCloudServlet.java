package com.example.alwayssunny;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AlwaysSunnyCloudServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		List<WeatherStation> sunnyStations = WeatherStation.loadSunny(pm);
		String jsonString = formatAsJson(sunnyStations);
		out.println(jsonString);
	}
	
	public String formatAsJson(List<WeatherStation> stations) {
		String jsonString = "[";
		for(WeatherStation s : stations){
			jsonString += s.formatAsJson() + ",";
		}
		if(stations.size() > 0){
			jsonString = jsonString.substring(0, jsonString.length() - 1);
		}
		return jsonString + "]";
	}
	
	
}
