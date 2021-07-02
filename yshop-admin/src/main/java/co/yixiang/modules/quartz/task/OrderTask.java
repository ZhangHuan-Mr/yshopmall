package co.yixiang.modules.quartz.task;

import co.yixiang.modules.quartz.utils.DataUtil;
import co.yixiang.modules.quartz.utils.StringUtil;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.service.YxStoreOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * <p>  </p>
 *
 * @author zhanghuan <br />
 * @version v1.0
 * @date 2021/7/1 11:27 <br />
 * 修改履历 <br />
 * 日期 : 姓名: 修改内容<br />
 */
@Slf4j
@Component
public class OrderTask {

    private final YxStoreOrderService yxStoreOrderService;

    public OrderTask(YxStoreOrderService yxStoreOrderService) {
        this.yxStoreOrderService = yxStoreOrderService;
    }

    public void run() {
        YxStoreOrder resources = new YxStoreOrder();
        String ownSpuNo = DataUtil.getMallProductOwnNo("ORDER");
        resources.setOrderId(ownSpuNo);
        String extendOrder = DataUtil.getMallProductOwnNo("EXTEND_ORDER");
        resources.setExtendOrderId(extendOrder);
        resources.setUid(1L);
        resources.setRealName("张三");
        resources.setUserPhone("18616881688");
        resources.setUserAddress("上海黄浦外滩码头");
        resources.setCartId("48985453658");
        resources.setFreightPrice(new BigDecimal(0));
        resources.setTotalNum(1);
        BigDecimal total = new BigDecimal(100);
        resources.setTotalPrice(total);
        resources.setPayPrice(total);
        resources.setPaid(1);
        Date date = new Date();
        resources.setPayTime(date);
        resources.setPayType("yue");
        resources.setCreateTime(date);
        resources.setUpdateTime(date);
        resources.setStatus(0);
        resources.setRefundStatus(0);
        resources.setUnique(StringUtil.truncateByDbcCase(UUID.randomUUID().toString(), 32));
        resources.setCost(new BigDecimal(80));
        resources.setShippingType(1);
        resources.setMark("自动生成订单");
        resources.setRemark("自动生成订单");
        yxStoreOrderService.create(resources);
    }
}
