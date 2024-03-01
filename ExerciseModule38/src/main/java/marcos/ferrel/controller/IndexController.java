/**
 * 
 */
package marcos.ferrel.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * @author marcos.ferrel
 * 
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = -6908602489267150571L;
	
	public String redirectCar() {
		return "/car/list.xhtml";
	}

}
