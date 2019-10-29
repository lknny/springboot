package springboot.order;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by ${ lknny@163.com } on 2019/10/26.
 */
@Configuration
public class AOrder implements InitializingBean ,BeanPostProcessor{
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("this is--------------> third bean!");
	}
	@Bean
	@Order(3)
	public AOrder get(){
		return new AOrder();
	}

	@Override
	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
		return o;
	}

	@Override
	public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
		if (s.contains("order")) {
			System.out.println(s);
			System.out.println("bean post processor....");
		}
		return o;
	}
}
