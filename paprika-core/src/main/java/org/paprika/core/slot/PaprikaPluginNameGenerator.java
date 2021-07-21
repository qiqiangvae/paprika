package org.paprika.core.slot;

import org.paprika.slot.PaprikaPlugin;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author qiqiang
 */
public class PaprikaPluginNameGenerator extends AnnotationBeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = ((ScannedGenericBeanDefinition) definition).getMetadata().getAnnotationAttributes(PaprikaPlugin.class.getName());
        if (annotationAttributes == null || annotationAttributes.size() == 0) {
            return super.generateBeanName(definition, registry);
        } else {
            String value = (String) annotationAttributes.get("value");
            if (!StringUtils.isEmpty(value)) {
                return value;
            }
        }
        return super.generateBeanName(definition, registry);

    }
}