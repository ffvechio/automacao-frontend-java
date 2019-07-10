package framework;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver.Navigation;

public class FrameworkNavigation implements Navigation {

	private Navigation navigation;
	private FrameworkUtils utils;

	public FrameworkNavigation(HashMap<String, String> propertyMap, FrameworkUtils utils, Navigation navigation) {
		this.navigation = navigation;
		this.utils = utils;
	}

	public void back() {
		this.navigation.back();

	}

	public void forward() {
		this.navigation.forward();

	}

	public void refresh() {
		this.navigation.refresh();

	}

	public void to(String arg0) {
		this.navigation.to(arg0);
		this.utils.addLogStep("Passed", "A p�gina � exibida com sucesso", "A p�gina n�o foi exibida com sucesso");
	}

	public void to(URL arg0) {
		this.navigation.to(arg0);

	}

}
