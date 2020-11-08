package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ManyToOne {
	//用于属性为对象 @ManyToOne(fk="dept_id",fkClassName = "model.Department")
	String fk();
	String fkClassName();	
}
