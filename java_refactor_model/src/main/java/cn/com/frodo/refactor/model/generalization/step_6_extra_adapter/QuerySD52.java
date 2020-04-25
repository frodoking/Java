package cn.com.frodo.refactor.model.generalization.step_6_extra_adapter;

public class QuerySD52 extends AbstractQuery {

    private String sdConfigFileName;

    public QuerySD52(String sdConfigFileName) {
        this.sdConfigFileName = sdConfigFileName;
    }

    /**
     * 创建不一样的查询
     */
    @Override
    protected SDQuery createQuery() {
        return null;
    }

}
