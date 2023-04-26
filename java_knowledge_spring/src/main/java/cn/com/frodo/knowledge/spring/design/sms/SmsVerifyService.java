package cn.com.frodo.knowledge.spring.design.sms;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class SmsVerifyService {

    private static final Logger log = LoggerFactory.getLogger(SmsVerifyService.class);

    public static final String MALL_KEY = "MALL_KEY";
    public static final String MALL_CODE_HTML = "MALL_CODE_HTML";
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public boolean sendRegisterCode(String mail) {
        // 1.校验邮箱格式
        boolean isEmail = EmailValidator.getInstance().isValid(mail);
        if (!isEmail) throw new IllegalStateException("请输入正确的邮箱格式");

        // 2.查看邮箱是否注册
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(MALL_KEY, mail);
        if (isMember) throw new IllegalStateException("该邮箱已注册");

        // 3.获取验证码的Key
        String cacheCodeKey = stringRedisTemplate.opsForValue().get(mail);

        // 4.从Redis获取验证码，如果不为空判断是否大于一分钟，如果没有则生成验证码并存储到Redis
        String cacheCodeValue = stringRedisTemplate.opsForValue().get(cacheCodeKey);
        if (StringUtils.isNotBlank(cacheCodeValue)) {
            // 4.1 获取时间戳，判断是否大于0
            long ttl = Long.parseLong(cacheCodeValue.split("_")[1]);
            if (System.currentTimeMillis() - ttl < 1000 * 60) {
                log.info("重复发送验证码,时间间隔:{} 秒", (60 - ((System.currentTimeMillis() - ttl) / 1000)));
                throw new IllegalStateException("请" + (60 - ((System.currentTimeMillis() - ttl) / 1000)) + "s后发送验证码");
            }
        }

        // 5.生成验证码，验证码由：验证码_毫秒值
        String code = RandomUtils.nextInt(0, 6) + "_" + System.currentTimeMillis();
        // 6.存储验证码
        stringRedisTemplate.opsForValue().set(cacheCodeKey, code, 5, TimeUnit.MINUTES);
        // 7.发送验证码
//        MailUtil.send(mail, "星空航班-用户注册验证码", String.format(MALL_CODE_HTML, "注册", code.split("_")[0], code.split("_")[0]), true);
        return true;
    }

    public boolean mailRegister(RegisterParam param) {
        // 1.获取验证码的Key
        String cacheCodeKey = stringRedisTemplate.opsForValue().get(param.getMail());

        // 2.查询验证码
        String cacheCodeValue = stringRedisTemplate.opsForValue().get(cacheCodeKey);

        // 3.如果没有找到验证码，代表验证码以及过期了
        if (StringUtils.isBlank(cacheCodeValue))
            throw new IllegalStateException("验证码有误，请重新输入");

        // 4.判断验证码是否正确
        if (!param.getCode().equals(cacheCodeValue.split("_")[0]))
            throw new IllegalStateException("验证码有误，请重新输入");

        // TODO 业务逻辑
        return true;
    }
}
