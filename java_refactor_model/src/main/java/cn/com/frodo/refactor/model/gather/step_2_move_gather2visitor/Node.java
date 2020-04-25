package cn.com.frodo.refactor.model.gather.step_2_move_gather2visitor;

public interface Node {
    void accept(NodeVisitor visitor);
}
