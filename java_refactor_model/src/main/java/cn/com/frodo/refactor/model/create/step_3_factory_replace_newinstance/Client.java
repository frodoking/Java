package cn.com.frodo.refactor.model.create.step_3_factory_replace_newinstance;

import cn.com.frodo.refactor.model.create.step_3_factory_replace_newinstance.User.RemoteUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    // FIXME old
    protected List<AttributeDescriptor> createAttributeDescriptors() {
        List<AttributeDescriptor> result = new ArrayList<AttributeDescriptor>();
        result.add(new DefaultDescriptor("remoteId", getClass(), Integer.TYPE));
        result.add(new DefaultDescriptor("createdDate", getClass(), Date.class));
        result.add(new DefaultDescriptor("lastChangedDate", getClass(), Date.class));

        result.add(new ReferenceDescriptor("createBy", getClass(), User.class, RemoteUser.class));
        result.add(new ReferenceDescriptor("lastChangedBy", getClass(), User.class, RemoteUser.class));

        result.add(new DefaultDescriptor("optimisticLockVersion", getClass(), Integer.TYPE));
        return result;
    }

    // TODO 重构
    protected List<AttributeDescriptor> createAttributeDescriptors2() {
        List<AttributeDescriptor> result = new ArrayList<AttributeDescriptor>();
        result.add(AttributeDescriptor.forInteger("remoteId", getClass()));
        result.add(AttributeDescriptor.forDate("createdDate", getClass()));
        result.add(AttributeDescriptor.forDate("lastChangedDate", getClass()));

        result.add(AttributeDescriptor.forUserAndRemoteUser("createBy", getClass()));
        result.add(AttributeDescriptor.forUserAndRemoteUser("lastChangedBy", getClass()));

        result.add(AttributeDescriptor.forInteger("optimisticLockVersion", getClass()));
        return result;
    }
}
