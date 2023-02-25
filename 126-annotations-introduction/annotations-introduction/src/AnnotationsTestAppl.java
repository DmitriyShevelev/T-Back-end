import telran.data.DataSql;

public class AnnotationsTestAppl {

	public static void main(String[] args) {
		Person prs = new Person(123, "gggg@google.main");
		DataSql dataSql = new DataSql();
		dataSql.displayEntitytInfo(prs);

	}

}
