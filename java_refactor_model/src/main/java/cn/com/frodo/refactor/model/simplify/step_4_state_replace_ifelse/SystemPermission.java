package cn.com.frodo.refactor.model.simplify.step_4_state_replace_ifelse;

public class SystemPermission {

	private PermissionState state;
	private SystemUser requestor;
	private SystemProfile profile;

	private SystemAdmin admin;

	private boolean isGranted;
	private boolean isUnixPermissionGranted;

	public SystemPermission(SystemUser requestor, SystemProfile profile) {
		this.requestor = requestor;
		this.profile = profile;
	}

	public PermissionState getState() {
		return state;
	}

	protected void setState(PermissionState state) {
		this.state = state;
	}

	public SystemUser getRequestor() {
		return requestor;
	}

	public void setRequestor(SystemUser requestor) {
		this.requestor = requestor;
	}

	public SystemProfile getProfile() {
		return profile;
	}

	public void setProfile(SystemProfile profile) {
		this.profile = profile;
	}

	void claimedBy(SystemAdmin admin) {
		state.claimedBy(admin, this);
	}

	void grantedBy(SystemAdmin admin) {
		state.grantedBy(admin, this);
	}

	void deniedBy(SystemAdmin admin) {
		state.deniedBy(admin, this);
	}

	public void willBeHandledBy(SystemAdmin admin) {
	}

	public SystemAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(SystemAdmin admin) {
		this.admin = admin;
	}

	public boolean isGranted() {
		return isGranted;
	}

	public void setGranted(boolean isGranted) {
		this.isGranted = isGranted;
	}

	public boolean isUnixPermissionGranted() {
		return isUnixPermissionGranted;
	}

	public void setUnixPermissionGranted(boolean isUnixPermissionGranted) {
		this.isUnixPermissionGranted = isUnixPermissionGranted;
	}

	public void notifyUserOfPermissionRequestResult() {

	}
}
