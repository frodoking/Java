package cn.com.frodo.refactor.model.create.step_3_factory_replace_newinstance;

import cn.com.frodo.refactor.model.create.step_3_factory_replace_newinstance.User.RemoteUser;

import java.sql.Date;

/**
 * 1、通过超类访问子类； <br>
 * 2、确保客户端代码通过超类的接口获得其子类的对象； <br>
 * 3、防止客户代码直接实例化该超类的子类； <br>
 * 4、该超类的子类不打算公开； <br>
 * (参考<code>java.util.Collections</code>类)
 *
 * @author frodoking
 */
public abstract class AttributeDescriptor {
    protected AttributeDescriptor() {
    }

    public static AttributeDescriptor forInteger(String flag, Class clazz) {
        return new DefaultDescriptor(flag, clazz, Integer.TYPE);
    }

    public static AttributeDescriptor forDate(String flag, Class clazz) {
        return new DefaultDescriptor(flag, clazz, Date.class);
    }

    public static AttributeDescriptor forString(String flag, Class clazz) {
        return new DefaultDescriptor(flag, clazz, String.class);
    }

    public static AttributeDescriptor forUserAndRemoteUser(String flag, Class clazz) {
        return new ReferenceDescriptor(flag, clazz, User.class, RemoteUser.class);
    }
}
