package org.paprika.core.slot;

import lombok.extern.slf4j.Slf4j;
import org.paprika.slot.PaprikaPlugin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 */
@Component
@Slf4j
public class PaprikaPluginBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        PaprikaSlogScanner scanner = new PaprikaSlogScanner(registry);
        scanner.resetFilters(false);
        scanner.setBeanNameGenerator(new PaprikaPluginNameGenerator());
        scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
        // todo 此处应改为可扩展
        int scan = scanner.scan("org.paprika");
        log.info("插件总数[{}]", scan);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    static class PaprikaSlogScanner extends ClassPathBeanDefinitionScanner {

        public PaprikaSlogScanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            ScannedGenericBeanDefinition definition = (ScannedGenericBeanDefinition) beanDefinition;
            AnnotationMetadata metadata = definition.getMetadata();
            if (metadata.isAnnotation()) {
                return false;
            }
            return metadata.hasAnnotation(PaprikaPlugin.class.getName());
        }
    }
}