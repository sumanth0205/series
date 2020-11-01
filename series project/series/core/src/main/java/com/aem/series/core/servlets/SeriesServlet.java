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

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Series Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/series" })

public class SeriesServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
				for (int i = 0; i < Integer.parseInt(limit) - 2; i++) {
					resNo = firstNo + secondNo;

					response.getWriter().write("," + resNo);
					firstNo = secondNo;
					secondNo = resNo;

				}
			} else if (firstNumber != null && limit != null) {
				int firstNo = Integer.parseInt(firstNumber);
				response.getWriter().write(firstNo + "," + "1");
				int secondNo = 1;
				int resNo = firstNo + 1;
				for (int i = 0; i < Integer.parseInt(limit) - 2; i++) {
					resNo = firstNo + secondNo;

					response.getWriter().write("," + resNo);
					firstNo = secondNo;
					secondNo = resNo;

				}
			} else if (firstNumber != null) {
				int firstNo = Integer.parseInt(firstNumber);
				response.getWriter().write(firstNo + "," + "1");
				int secondNo = 1;
				int resNo = firstNo + 1;
				for (int i = 0; i < 8; i++) {
					resNo = firstNo + secondNo;

					response.getWriter().write("," + resNo);
					firstNo = secondNo;
					secondNo = resNo;
				}

			}

		} else {
			response.getWriter().write("Max value for First Number & second Number should be 2147483647");
		}

	}

}
