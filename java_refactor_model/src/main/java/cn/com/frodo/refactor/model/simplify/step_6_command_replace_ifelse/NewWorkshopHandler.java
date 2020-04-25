package cn.com.frodo.refactor.model.simplify.step_6_command_replace_ifelse;

import java.util.Map;

public class NewWorkshopHandler extends Handler {
    private static final String ALL_WORKSHOPS = "ALL_WORKSHOPS";

    public NewWorkshopHandler(CatalogApp catalogApp) {
        super(catalogApp);
    }

    @Override
    public HandlerResponse execute(Map<Object, Object> parameters) {
        createNewWorkshop(parameters);
        return catalogApp.executeActionAndGetResponse(ALL_WORKSHOPS, parameters);
    }

    private void createNewWorkshop(Map<Object, Object> parameters) {
        String nextWorkshopID = workshopManager().getNextWorkshopID();
        workshopManager().addWorkshop(newWorkshopContents(nextWorkshopID));
        parameters.put("id", nextWorkshopID);
    }

    private StringBuffer newWorkshopContents(String nextWorkshopID) {
        return workshopManager().createNewFileFromTemplate(nextWorkshopID, workshopManager().getWorkshopDir(),
                workshopManager().getWorkshopTemplate());
    }

    private WorkshopManager workshopManager() {
        return catalogApp.getWorkshopManager();
    }
}
