package cn.com.frodo.refactor.model.simplify.step_4_state_replace_ifelse;

public class PermissionRequested extends PermissionState {

	protected PermissionRequested() {
		super("REQUESTED");
	}
	
	@Override
	public void claimedBy(SystemAdmin admin, SystemPermission permission) {
		permission.willBeHandledBy(admin);
		permission.setState(CLAIMED);
	}
}
