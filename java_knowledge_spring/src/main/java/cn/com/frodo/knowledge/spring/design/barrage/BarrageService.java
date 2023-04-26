package cn.com.frodo.knowledge.spring.design.barrage;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarrageService {

    public static final Long window = 100L;

    public static final List<Barrage> list = new ArrayList<>();

    public void send(Barrage barrage) {
        list.add(barrage);
    }

    public List<Barrage> play(Long currentTime) {
        return null;
    }
}
