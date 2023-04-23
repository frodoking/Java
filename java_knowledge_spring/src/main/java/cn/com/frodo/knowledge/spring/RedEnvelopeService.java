package cn.com.frodo.knowledge.spring;

import cn.com.frodo.SnowflakeIdUtils;
import com.beust.jcommander.internal.Lists;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class RedEnvelopeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发红包
     */
    public Long sendPackage(Long userId, Long amount, Integer quantity) {
        SnowflakeIdUtils idWorker = new SnowflakeIdUtils(3, 1);

        Long packageId = idWorker.nextId();

        // 指定 lua 脚本，并且指定返回值类型
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/red_envelope_set_package.lua")));
        redisScript.setResultType(Long.class);

        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Long result = stringRedisTemplate.execute(redisScript, Lists.newArrayList(packageId.toString()), amount.toString(), quantity.toString());
        if (result == 1) {
            System.out.println("set sucess");
        }
        return packageId;
    }

    /**
     * 抢红包
     */
    public boolean grabPackage(Long userId, Long packageId) {
        // 指定 lua 脚本，并且指定返回值类型
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/red_envelope_grab_package.lua")));
        redisScript.setResultType(Long.class);

        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Long result = stringRedisTemplate.execute(redisScript, Lists.newArrayList(packageId.toString()), userId.toString());

        if (result > 0) {
            System.out.println("sucess : " + userId + " >> " + result);
        }

        return result > 0;
    }
}
