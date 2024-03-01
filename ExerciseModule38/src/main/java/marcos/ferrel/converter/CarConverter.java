/**
 * 
 */
package marcos.ferrel.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import marcos.ferrel.domain.Car;

/**
 * @author marcos.ferrel
 * 
 */
@Named
@FacesConverter(value = "carConverter", forClass = Car.class)
public class CarConverter implements Converter {
	
	private static final String key = "marcos.ferrel.converter.CarConverter";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.isEmpty()) {
	        return null;
	    }
	    return getViewMap(context).get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object car) {
		String id = ((Car)car).getId().toString();
		getViewMap(context).put(id, car);
	    return id;
	}	
	
	private Map<String, Object> getViewMap(FacesContext context) {
	    Map<String, Object> viewMap = context.getViewRoot().getViewMap();
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    Map<String, Object> idMap = (Map) viewMap.get(key);
	    if (idMap == null) {
	        idMap = new HashMap<String, Object>();
	        viewMap.put(key, idMap);
	    }
	    return idMap;
	}	

}
