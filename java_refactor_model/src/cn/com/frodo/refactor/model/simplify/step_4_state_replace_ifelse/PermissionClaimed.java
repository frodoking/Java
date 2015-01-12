package cn.com.frodo.refactor.model.simplify.step_4_state_replace_ifelse;

public class PermissionClaimed extends PermissionState {

	protected PermissionClaimed() {
		super("CLAIMED");
	}
	
	@Override
	public void grantedBy(SystemAdmin admin, SystemPermission permission) {
		if (permission.getAdmin().equals(admin))
			return;

		if (permission.getProfile().isUnixPermissionRequired() && !permission.isUnixPermissionGranted()) {
			permission.setState(UNIX_REQUESTED);
			permission.notifyUserOfPermissionRequestResult();
			return;
		}

		permission.setState(GRANTED);
		permission.setGranted(true);
		permission.notifyUserOfPermissionRequestResult();
	}

	@Override
	public void deniedBy(SystemAdmin admin, SystemPermission permission) {
		if (permission.getAdmin().equals(admin))
			return;

		permission.setGranted(false);
		permission.setUnixPermissionGranted(false);
		permission.setState(DENIED);
		permission.notifyUserOfPermissionRequestResult();
	}

}
