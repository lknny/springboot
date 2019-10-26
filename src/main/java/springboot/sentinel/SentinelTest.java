package springboot.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${ lknny@163.com } on 2018/12/29.
 */
public class SentinelTest {
	public static void main(String[] args) throws InterruptedException {
		initFlowRules();

		while (true) {
			Entry entry = null;
			try {
				entry = SphU.entry("lk");
				System.out.println("hello, lk");
			} catch (Exception e) {
				Thread.sleep(1000);
				System.out.println("block........!");
			}finally {
				if (null != entry) {
					entry.exit();
				}
			}
		}
	}

	private static void initFlowRules() {
		List<FlowRule> flowRules = new ArrayList<>();
		FlowRule flowRule = new FlowRule();
		flowRule.setResource("lk");
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		flowRule.setCount(20);
		flowRules.add(flowRule);
		FlowRuleManager.loadRules(flowRules);

	}
}
