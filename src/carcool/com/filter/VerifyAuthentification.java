package carcool.com.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import carcool.com.servlet.LogsServlets;
import carcool.com.servlet.RegisterUser;

/**
 * Servlet Filter implementation class VerifyAuthentification
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, 
		urlPatterns = {
				"/DeleteUser",
				"/UpdateUser"
		},
		description = "Vérifie qu'un utilisateur s'est bien authentifié pour accéder à certaines fonctionnalités", 
		servletNames = { 
				"DeleteUser", 
				"UpdateUser"
		})
public class VerifyAuthentification implements Filter {
	private LogsServlets LOGGER = new LogsServlets (VerifyAuthentification.class.getName(),null, VerifyAuthentification.class.getName());
    /**
     * Default constructor. 
     */
    public VerifyAuthentification() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		LOGGER.logger_begin(LOGGER.getName());
		LOGGER.info("Passage dans le filtre.");
		LOGGER.logger_end();
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
