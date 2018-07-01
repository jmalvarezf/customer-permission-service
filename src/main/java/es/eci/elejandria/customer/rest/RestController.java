package es.eci.elejandria.customer.rest;

import es.eci.elejandria.customer.beans.ResponseBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final Random random = new Random();

    private final Map<Integer, ResponseBean> cache = Collections.synchronizedMap(new WeakHashMap<Integer, ResponseBean>());

    @RequestMapping("/customers/{customerId}")
    public ResponseBean getCustomerPermission(@PathVariable("customerId") Integer customerId) {
        ResponseBean result;
        if(cache.containsKey(customerId)) {
            result = cache.get(customerId);
        } else {
            result = new ResponseBean();
            result.setIsallowedToUseData(random.nextBoolean());
            cache.put(customerId, result);
        }
        return result;
    }

}
