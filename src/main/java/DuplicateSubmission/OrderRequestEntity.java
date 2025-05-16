package DuplicateSubmission;

import lombok.Data;

/**
 * @author ZT
 * @version 1.0.0
 * @description $
 * @date 2025/2/3
 */
@Data
public class OrderRequestEntity {
    // 用户ID
    private String userId;
    // 订单号 (前端或后端生成唯一标识)
    private String orderNo;
}
