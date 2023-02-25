package telran.utils;

public class Generator {

int min;
int max;
Rule rule;
public Rule getRule() {
	return rule;
}
public void setRule(Rule rule) {
	this.rule = rule;
}

public int[] generate(int nNumbers, int min, int max) {
	if (max <= min) {
		throw new IllegalArgumentException(" max should be greater than min");
	}
	int [] res = new int[nNumbers];
	for (int i = 0; i < nNumbers; i++) {
		res[i] = getNumber();
	}
	return res;
}
private int getNumber()  {
	int res = (int) (min + Math.random() * (max - min + 1));
	if (rule != null) {
		try {
			rule.checkRule(res, min, max);
		} catch (NoRuleMatchException e) {
			res += e.getDelta();
		} 
	}
	return res;
}

}
