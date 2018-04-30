package springboot.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${lknny@163.com} on 2017/11/13.
 */
@RestController
@ComponentScan(basePackages={"springboot"})
@EnableAutoConfiguration
public class Controller {

	@RequestMapping(value="/lk")
	public String home(){
		System.out.println("------------>hello world: "+this.getClass().getName());
		return "hello world.";
	}

//	@RequestMapping(value="config")
//	public Map<String,String> getCfg(){
//		Map<String, String> cfg = new HashMap<String, String>();
//		cfg.put("name", config.getName());
//		cfg.put("version",config.getVersion());
//		return cfg;
//	}
}
