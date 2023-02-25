package telran.utils;

public interface Rule {
void checkRule(int number, int min, int max) throws NoRuleMatchException ;
}
