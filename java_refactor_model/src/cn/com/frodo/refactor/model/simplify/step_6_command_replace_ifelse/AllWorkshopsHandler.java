package cn.com.frodo.refactor.model.simplify.step_6_command_replace_ifelse;

import java.util.Iterator;
import java.util.Map;

public class AllWorkshopsHandler extends Handler {
	private static final String ALL_WORKSHOPS_STYLESHEET = "allWorkshops.xsl";

	private PrettyPrinter prettyPrinter = new PrettyPrinter();

	public AllWorkshopsHandler(CatalogApp catalogApp) {
		super(catalogApp);
	}

	@Override
	public HandlerResponse execute(Map<Object, Object> parameters) {
		return new HandlerResponse(new StringBuffer(prettyPrint(allWorkshopsData())), ALL_WORKSHOPS_STYLESHEET);
	}

	private String allWorkshopsData() {
		XMLBuilder allWorkshopsXml = new XMLBuilder("workshops");
		WorkshopRepository repository = workshopManager().getWorkshopRepository();
		Iterator ids = repository.keyIterator();
		while (ids.hasNext()) {
			String id = (String) ids.next();
			Workshop workshop = repository.getWorkshop(id);
			allWorkshopsXml.addBelowParent("workshop");
			allWorkshopsXml.addAttribute("id", workshop.getId());

			allWorkshopsXml.addAttribute("name", workshop.getName());
			allWorkshopsXml.addAttribute("status", workshop.getStatus());
			allWorkshopsXml.addAttribute("duration", workshop.getDurationAsString());
		}
		return allWorkshopsXml.toString();
	}

	private String prettyPrint(String buffer) {
		return prettyPrinter.format(buffer);
	}

	private WorkshopManager workshopManager() {
		return catalogApp.getWorkshopManager();
	}

}
