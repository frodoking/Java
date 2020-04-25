package cn.com.frodo.refactor.model.simplify.step_6_command_replace_ifelse;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于Command的解决方案。现在CatalogApp使用Command模式来执行动作并返回响应。在这种设计下，声明新的处理程序、
 * 命名处理程序以及把处理程序注册到命令映射中，以便在运行时执行操作就变得容易很多。
 *
 * @author frodoking
 */
public class CatalogApp {
    private static final String NEW_WORKSHOP = "NEW_WORKSHOP";
    private static final String ALL_WORKSHOPS = "ALL_WORKSHOPS";

    private Map<String, Handler> handlers;

    private WorkshopManager workshopManager;

    protected HandlerResponse executeActionAndGetResponse(String handlerName, Map<Object, Object> parameters) {
        Handler handler = lookupHandlerBy(handlerName);
        return handler.execute(parameters);
    }

    private Handler lookupHandlerBy(String handlerName) {
        return handlers.get(handlerName);
    }

    public void createHandlers() {
        handlers = new HashMap<String, Handler>();
        handlers.put(NEW_WORKSHOP, new NewWorkshopHandler(this));
        handlers.put(ALL_WORKSHOPS, new AllWorkshopsHandler(this));
    }

    public WorkshopManager getWorkshopManager() {
        return workshopManager;
    }

}
