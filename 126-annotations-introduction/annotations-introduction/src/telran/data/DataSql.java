package telran.data;

import java.lang.reflect.*;

import telran.data.annotations.Id;
import telran.data.annotations.Table;

public class DataSql {
public void displayEntitytInfo(Object object) {
	Class<?> clazz = object.getClass();
	Table anTable = clazz.getAnnotation(Table.class);
	String tableName = anTable == null ? clazz.getSimpleName() : anTable.name();
	System.out.println("Table name is " + tableName);
	Field [] fields = clazz.getDeclaredFields();
	displayIdOrException(fields);
}

private void displayIdOrException(Field[] fields) {
	String idFieldName = null;
	for(Field field: fields) {
		if (field.isAnnotationPresent(Id.class)) {
			if (idFieldName != null) {
				throw new IllegalStateException("Class may contain only one field ID");
			}
			idFieldName = field.getName();
		}
	}
	if (idFieldName == null) {
		throw new IllegalStateException("Class should have one field ID");
	}
	System.out.println("Field ID is " + idFieldName);
	
}
}
