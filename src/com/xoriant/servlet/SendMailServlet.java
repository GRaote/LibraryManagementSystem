package com.xoriant.servlet;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.xoriant.mailing.SendUsingGmail;

public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doCommon(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doCommon(request, response);
	}

	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String email = request.getParameter("emailID");
			String bookTitle = request.getParameter("title");

			Document doc = new Document();
			String file = "D:/gaurang/LibraryManagementSystem/pdf/test.pdf";

			Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12);

			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();
			doc.add(new Paragraph("Email: " + email, bf12));
			doc.add(new Paragraph("BookTitle: " + bookTitle, bf12));
			doc.close();
			SendUsingGmail.sendNotification(email, doc);

			request.setAttribute("transactionSuccessful",
					"The mail has been sent successfully to user with the email address"
							+ " " + email);

			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/jsp/Email.jsp");

			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println("Exception::" + e);
		}
	}
}
