package cn.com.frodo.refactor.model.simplify.step_6_command_replace_ifelse;

import java.util.Map;

public abstract class Handler {
	protected CatalogApp catalogApp;

	public Handler(CatalogApp catalogApp) {
		this.catalogApp = catalogApp;
	}
	
	public abstract HandlerResponse execute(Map<Object, Object> parameters);
	

}
