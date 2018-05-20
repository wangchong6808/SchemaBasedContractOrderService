package com.jsonschema.config;

import com.jsonschema.aop.MockBeanUtil;
import com.jsonschema.aop.MockRemoteBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;

@Slf4j
public class JsonSchemaExtension implements TestInstancePostProcessor, BeforeAllCallback, AfterAllCallback,
        ParameterResolver, BeforeTestExecutionCallback {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.getAnnotation(MockRemoteBean.class) != null) {
                Object mock = MockBeanUtil.mockBean(field.getType());
                field.setAccessible(true);
                ReflectionTestUtils.setField(testInstance, field.getName(), mock);
            }
        }
        log.info("testInstance is : " + testInstance);
        log.info("ExtensionContext is : " + context);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        log.info("after all callback");
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("before all callback");
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        log.info("support parameter " + parameterContext.toString());
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        log.info("resolve parameter " + parameterContext.toString());
        return null;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        System.out.println("beforeTestExecution");

    }
}
