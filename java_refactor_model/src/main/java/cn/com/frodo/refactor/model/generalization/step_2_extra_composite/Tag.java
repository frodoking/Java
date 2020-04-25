package cn.com.frodo.refactor.model.generalization.step_2_extra_composite;

public abstract class Tag {
    int tagBegin;
    int tagEnd;
    String tagContents;
    String tagLine;

    public Tag(int tagBegin, int tagEnd, String tagContents, String tagLine) {
        this.tagBegin = tagBegin;
        this.tagEnd = tagEnd;
        this.tagContents = tagContents;
        this.tagLine = tagLine;
    }
}
