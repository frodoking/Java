package cn.com.frodo.design.pattern.behavior.memento.demo;


public class ClientDemo {
    public static void main(String[] args) {
        VersionControlSystem vcs = new VersionControlSystem();

        Document document = new Document();
        document.content = "content1";
        document.otherContent = "otherContent1";
        System.out.println(document);
        vcs.add(document.save());

        document.content = "content2";
        document.otherContent = "otherContent2";
        System.out.println(document);
        vcs.add(document.save());

        document.content = "content3";
        document.otherContent = "otherContent3";
        System.out.println(document);
        vcs.add(document.save());


        document.resume(vcs.get(1));
        System.out.println(document);
        document.resume(vcs.getLastVersion());
        System.out.println(document);
    }
}
