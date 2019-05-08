import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface SortProperties {
	public String name();

	public String worstTime();

	public String bestTime();

	public String averageTime();

	public String worstSpace();

	public boolean adaptive();

	public boolean stable();
}
