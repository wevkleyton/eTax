package org.etax.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.etax.util.SituacaoDoc;

@FacesConverter(forClass = SituacaoDoc.class)
public class SituacaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value==null || value.trim().contentEquals("")) {
			return null;
		}
		
		if ("Ativo".equalsIgnoreCase(value)) {
			return SituacaoDoc.ATIVO;
		}
		if ("Inativo".equalsIgnoreCase(value)) {
			return SituacaoDoc.INATIVO;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value==null) {
			return null;
		}
		
		SituacaoDoc situacao = (SituacaoDoc) value;
		return situacao.getDescricao();
	}

}
