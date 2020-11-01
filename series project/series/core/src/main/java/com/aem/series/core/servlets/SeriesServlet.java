package com.aem.series.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.series.core.service.FibonacciSeries;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Series Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/series" })

public class SeriesServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Reference
	FibonacciSeries fibonacciSeries;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String firstNumber = request.getParameter("firstNumber");
		String secondNumber = request.getParameter("secondNumber");
		String limit = request.getParameter("limit");
		if (Integer.parseInt(firstNumber) < 2147483647 || Integer.parseInt(secondNumber) < 2147483647) {
			if (firstNumber != null && secondNumber != null && limit != null) {
				int firstNo = Integer.parseInt(firstNumber);
				response.getWriter().write(firstNumber + ",");
				int secondNo = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber);
				response.getWriter().write(secondNumber);
				int resNo = firstNo + secondNo;
				int limit1 = Integer.parseInt(limit);
				String servletResponse = fibonacciSeries.fibonacciseries(resNo, firstNo, secondNo, limit1);
				response.getWriter().write(servletResponse);
			} else if (firstNumber != null && limit != null) {
				int firstNo = Integer.parseInt(firstNumber);
				response.getWriter().write(firstNo + "," + "1");
				int secondNo = 1;
				int resNo = firstNo + 1;
				int limit1 = Integer.parseInt(limit);
				String servletResponse = fibonacciSeries.fibonacciseries(resNo, firstNo, secondNo, limit1);
				response.getWriter().write(servletResponse);
			} else if (firstNumber != null) {
				int firstNo = Integer.parseInt(firstNumber);
				response.getWriter().write(firstNo + "," + "1");
				int secondNo = 1;
				int resNo = firstNo + 1;
				int limit1 = 8;
				String servletResponse = fibonacciSeries.fibonacciseries(resNo, firstNo, secondNo, limit1);
				response.getWriter().write(servletResponse);

			}

		} else {
			response.getWriter().write("Max value for First Number & second Number should be 2147483647");
		}

	}

}
