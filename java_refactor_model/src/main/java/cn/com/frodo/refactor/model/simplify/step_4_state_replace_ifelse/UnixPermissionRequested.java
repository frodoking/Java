package cn.com.frodo.refactor.model.simplify.step_4_state_replace_ifelse;

public class UnixPermissionRequested extends PermissionState {

    protected UnixPermissionRequested() {
        super("UNIX_REQUESTED");
    }

    @Override
    public void claimedBy(SystemAdmin admin, SystemPermission permission) {
        permission.willBeHandledBy(admin);
        permission.setState(UNIX_CLAIMED);
    }

}
