package cn.com.frodo.design.pattern.behavior.state.demo;

public class TV {
    public final static Channel CCTV1 = new CCTV1();
    public final static Channel CCTV5 = new CCTV5();
    public final static Channel CCTV6 = new CCTV6();

    private Channel channel;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void disCCTV1() {
        setChannel(CCTV1);
        channel.display();
    }

    public void disCCTV5() {
        setChannel(CCTV5);
        channel.display();
    }

    public void disCCTV6() {
        setChannel(CCTV6);
        channel.display();
    }
}
