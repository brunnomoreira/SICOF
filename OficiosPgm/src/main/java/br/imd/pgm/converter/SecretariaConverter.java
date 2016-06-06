package br.imd.pgm.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.imd.pgm.model.Secretaria;
import br.imd.pgm.repository.Secretarias;
import br.imd.pgm.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Secretaria.class)
public class SecretariaConverter implements Converter<Object> {

	// @Inject
	private Secretarias secretarias;

	public SecretariaConverter() {
		secretarias = CDIServiceLocator.getBean(Secretarias.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Secretaria retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = secretarias.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Secretaria) value).getId().toString();
		}
		return "";
	}

}
