package springboot;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${10190990} on 2017/11/13.
 */
@RestController
@RequestMapping(value="lk")
@EnableAutoConfiguration
public class Controller {

	@RequestMapping(value="/")
	public String home(){
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
