
public class EnumAppl {

	public static void main(String[] args) {
//		weekDayComment(WeekDay.valueOf("SAT"));
//		WeekDay wd = WeekDay.SAT;
//		System.out.printf("%s is yom %d ", wd, wd.ordinal() + 1);
		System.out.printf("10 kg is %f pounds",
				WeightUnit.KG.convert(10, WeightUnit.LBS));

	}
	public static void weekDayComment(WeekDay wd) {
		switch(wd) {
		case SUN: System.out.println("good day if you are not in Israel");break;
		case FRI: System.out.println("weekend if you are in Israel");break;
		case SAT: System.out.println("good day for all");
		default: System.out.println("working day for most people all over the world");
		
		}
	}

}
