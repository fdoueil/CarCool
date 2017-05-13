package carcool.com.servlet;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogsServlets extends Logger {
	private String nomServlet;
	private String fichierLog;
	Handler consoleHandler = null;

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

		// Création du consoleHandler et du fileHandler
		consoleHandler = new ConsoleHandler();

		// Assigner les gestionnaires à l'objet LOGGER
		this.addHandler(consoleHandler);

		// Paramétrer les niveaux de messages aux gestionnaires et au LOGGER
		consoleHandler.setLevel(Level.ALL);
		this.setLevel(Level.ALL);

		this.config("Début du suivi de la Servlet.");

	}

	public void logger_end() {
		this.config("Fin du suivi de la Servlet.");
		this.removeHandler(consoleHandler);
	}
}