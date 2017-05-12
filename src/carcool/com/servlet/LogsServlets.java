package carcool.com.servlet;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogsServlets extends Logger {
	private String nomServlet;
	private String fichierLog;
	Handler consoleHandler = null;
	Handler fileHandler  = null;

	protected LogsServlets(String nomServlet, String resourceBundleName, String fichierLog) {
		super(nomServlet, resourceBundleName);
		this.nomServlet = nomServlet;
		this.fichierLog = fichierLog;
	}

	public String getNomServlet() {
		return nomServlet;
	}

	public void setNomServlet(String actionMessage) {
		this.nomServlet = actionMessage;
	}

	public String getFichierLog() {
		return fichierLog;
	}

	public void setFichierLog(String cheminFichierLog) {
		this.fichierLog = cheminFichierLog;
	}

	public void logger_begin(String nomServlet) {
		
		SimpleFormatter simpleFormatter = null;
		
		//Création du consoleHandler et du fileHandler
		consoleHandler = new ConsoleHandler();
		simpleFormatter = new SimpleFormatter();

		
		try {
			System.out.println("C:/Logs/"+this.getFichierLog()+".%g.log");
			fileHandler  = new FileHandler("C:/Logs/"+this.getFichierLog()+".%g.log");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Assigner les gestionnaires à l'objet LOGGER
		this.addHandler(consoleHandler);
		this.addHandler(fileHandler);
		
		
		// Formater le gestionnaire pour des messages textes simples non XML
		fileHandler.setFormatter(simpleFormatter);
		
		//Paramétrer les niveaux de messages aux gestionnaires et au LOGGER
		consoleHandler.setLevel(Level.ALL);
		fileHandler.setLevel(Level.ALL);
		this.setLevel(Level.ALL);
		
		this.config("Début du suivi de la Servlet.");
			
	}
	
	public void logger_end() {
		this.config("Fin du suivi de la Servlet.");
		this.removeHandler(consoleHandler);
		this.removeHandler(fileHandler);
	}
}