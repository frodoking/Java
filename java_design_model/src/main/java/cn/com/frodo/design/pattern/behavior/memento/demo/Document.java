package cn.com.frodo.design.pattern.behavior.memento.demo;

public class Document {
    String content;
    String otherContent;

    public Backup save() {
        System.out.println("保存备份");
        return new Backup(content);
    }

    public void resume(Backup backup) {
        System.out.println("恢复备份");
        content = backup.content;
    }

    @Override
    public String toString() {
        return "content : " + content + " , otherContent: " + otherContent;
    }
}
