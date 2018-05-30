package shooter.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // only instantiated on methods
@Retention(value = RetentionPolicy.RUNTIME) // keeps the annotation past compile
public @interface EventListener {}
