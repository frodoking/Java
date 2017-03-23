package cn.com.frodo.knowledge.extendx;

import java.util.List;

/**
 * Created by xuwei19 on 2017/3/16.
 */
public class XResponse<T> extends Response<List<T>> {
    private List<T> data;

    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public void setData(List<T> data) {
        this.data = data;
    }
}
