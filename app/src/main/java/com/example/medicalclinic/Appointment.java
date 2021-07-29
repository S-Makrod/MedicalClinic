package com.example.medicalclinic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
        How to use date parser and formatter
        Date d = new Date(System.currentTimeMillis());

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		System.out.println(d.toString());
		System.out.println(dateFormatter.format(d));

		 // Format for input
		SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// Parsing the date


		Thread.sleep(5000);
		Date y = dateParser.parse(dateFormatter.format(d));
		System.out.println(y);
		System.out.println(dateFormatter.format(y));
 */
public interface Appointment {
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    final SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    String converter(Date d) throws ParseException;
}