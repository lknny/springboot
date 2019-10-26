package springboot.order;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by ${ lknny@163.com } on 2019/10/26.
 */
@Configuration

public class OrderFirst implements InitializingBean{
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("this is--------------> first bean!");
	}
	@Bean
	@Order(1)
	public OrderFirst get() {
		return new OrderFirst();
	}
}
