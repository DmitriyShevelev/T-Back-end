package telran.view;

import java.util.function.Consumer;

public interface Item {
String displayName();
void perform (InputOutput io) throws Exception;
boolean isExit();
static Item of(String name, Consumer<InputOutput> action, boolean flExit) throws Exception {
	return new Item() {
		@Override
		public void perform(InputOutput io) throws Exception{
			action.accept(io);
		}
		@Override
		public boolean isExit() {
			return flExit;
		}
		@Override
		public String displayName() {
			return name;
		}
	};
}
static Item of(String name, Consumer<InputOutput> action) throws Exception {
	return of(name, action, false);
}
static Item exit() throws Exception {
	return of("Exit", io -> {}, true);
}
}
