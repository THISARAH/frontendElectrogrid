package com.CustomerService.customer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



import com.CustomerService.customer.*;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customerAPI")
public class customerAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Customer Cus = new Customer() ;

    public customerAPI() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = Cus.insertCustomer( 
				
				request.getParameter("CustomerName"),
				request.getParameter("CustomerAddress"),
				request.getParameter("PremisesID"));
		
		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = Cus.updateCustomer(
				paras.get("ElectricityAccountNo").toString(),
				paras.get("CustomerName").toString(),
				paras.get("CustomerAddress").toString(),
				paras.get("PremisesID").toString());
		response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = Cus.deleteCustomer(
				paras.get("ElectricityAccountNo").toString());
		response.getWriter().write(output);

	}
	// Convert request parameters to a Map
			private static Map getParasMap(HttpServletRequest request) {
				Map<String, String> map = new HashMap<String, String>();
				try {
					Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
					String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					String[] params = queryString.split("&");
					for (String param : params) {

						String[] p = param.split("=");
						map.put(p[0], p[1]);
					}
				} catch (Exception e) {
				}
				return map;
			}
	

}
