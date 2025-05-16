package DuplicateSubmission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ZT
 * @version 1.0.0
 * @description $重复订单提交Demo
 * @date 2025/2/3
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final StringRedisTemplate redisTemplate;

    public OrderController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        // 使用 ping() 检查 Redis 连接
        // 检查连接是否为空
        if (redisTemplate != null && redisTemplate.getConnectionFactory() != null) {
            try {
                // 获取 Redis 连接并发送 ping 命令
                String pingResponse = redisTemplate.getConnectionFactory().getConnection().ping();
                // 输出 PONG 或其他响应
                log.info("Redis Connection Status: {}", pingResponse);
            } catch (Exception e) {
                log.error("Redis connection failed: ", e);
            }
        } else {
            log.error("Redis template or connection factory is null.");
        }
    }

    @PostMapping("/submit")
    public String submitOrder(@RequestBody OrderRequestEntity request) {
        // 订单唯一标识
        String key = "order:" + request.getOrderNo();

        // 使用 Redis `setIfAbsent` 实现幂等性检查
        Boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, "LOCKED", 5, TimeUnit.MINUTES);
        if (Boolean.FALSE.equals(isSuccess)) {
            return "订单已提交，请勿重复操作";
        }

        try {
            // 模拟业务逻辑（如生成订单）
            System.out.println("处理订单：" + request.getOrderNo());
            // 模拟订单处理时间
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "订单提交成功：" + request.getOrderNo();
    }
}
