package cn.com.frodo.knowledge.juc;

/**
 * Created by frodoking on 2019/3/10 上午11:19.
 * Description:
 */
public interface ProducerConsumer {

    void produce();

    void consume();


    static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            ProducerConsumer producerConsumer = new SynchronizedProducerConsumer();
            if (i % 2 == 0) {
                producerConsumer.produce();
            } else {
                producerConsumer.consume();
            }
        }
    }
}
