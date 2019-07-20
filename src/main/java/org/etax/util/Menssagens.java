package org.etax.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Menssagens {

	public static void msgInfo(String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summari, datail));
	}

	public static void msgInfo(String id, String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, summari, datail));
	}

	public static void msgWarn(String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_WARN, summari, datail));

	}

	public static void msgWarn(String id, String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, summari, datail));

	}

	public static void msgError(String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_ERROR, summari, datail));

	}

	public static void msgError(String id, String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, summari, datail));

	}

	public static void msgFatal(String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage(FacesMessage.SEVERITY_FATAL, summari, datail));

	}
	public static void msgFatal(String id, String summari, String datail) {
		FacesContext.getCurrentInstance().addMessage(id,	new FacesMessage(FacesMessage.SEVERITY_FATAL, summari, datail));

	}


}
