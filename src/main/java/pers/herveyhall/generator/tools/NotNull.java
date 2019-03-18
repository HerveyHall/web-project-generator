/**
 * 
 */
package pers.herveyhall.generator.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证字段非空
 * 
 * @author <a href="mailto://herveyhall@foxmail.com">Hervey Hall</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
}
