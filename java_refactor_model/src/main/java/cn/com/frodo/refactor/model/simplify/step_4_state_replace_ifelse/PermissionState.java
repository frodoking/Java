package cn.com.frodo.refactor.model.simplify.step_4_state_replace_ifelse;

/**
 * 重构实现state模式的主要原因，是为了对付过度复杂的状态改变条件逻辑。 
 * 这种逻辑往往会散步在整个类中，控制对象的状态以及状态之间的转化。
 * 在实现state模式的时候创建代表对象特殊状态和状态间转换的类
 * 
 * @author XuWei4
 * 
 */
public abstract class PermissionState {
	private String name;

	protected PermissionState(String name) {
		this.name = name;
	}

	public static final PermissionState REQUESTED = new PermissionRequested();
	public static final PermissionState CLAIMED = new PermissionClaimed();
	public static final PermissionState GRANTED = new PermissionGranted();
	public static final PermissionState DENIED = new PermissionDenied();
	public static final PermissionState UNIX_REQUESTED = new UnixPermissionRequested();
	public static final PermissionState UNIX_CLAIMED = new UnixPermissionClaimed();

	@Override
	public String toString() {
		return name;
	}

	public void claimedBy(SystemAdmin admin, SystemPermission permission) {
	}

	public void grantedBy(SystemAdmin admin, SystemPermission permission) {
	}

	public void deniedBy(SystemAdmin admin, SystemPermission permission) {
	}
}
