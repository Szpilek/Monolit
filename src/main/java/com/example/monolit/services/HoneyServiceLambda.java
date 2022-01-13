package com.example.monolit.services;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.example.monolit.MonolitApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tool.communication_model.RemoteType;
import com.tool.communication_model.RemoteArgument;

import com.tool.communication_model.RemoteCall;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Profile;
import java.lang.reflect.InvocationTargetException;
import com.fasterxml.jackson.databind.JavaType;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Collectors;

public class HoneyServiceLambda implements RequestStreamHandler {
    private static ApplicationContext context;
    static {
        try {
            // ZMIENIONE
            var a = SpringBootLambdaContainerHandler.getAwsProxyHandler(MonolitApplication.class, "HoneyServiceImpl");
            Field field = SpringBootLambdaContainerHandler.class.getDeclaredField("applicationContext");
            field.setAccessible(true);
            context = (ApplicationContext) field.get(a);
        } catch (ContainerInitializationException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        RemoteCall call = objectMapper.readValue(inputStream, RemoteCall.class);
        objectMapper.writeValue(outputStream, execute(call));
    }

    Object execute(RemoteCall call) {
        var arguments = call.getArguments().stream()
                .map(arg -> {
                    try {
                        return objectMapper.readValue(arg.getValue(), javaType(arg.getType()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Cannot deserialize parameter", e);
                    }
                }).collect(Collectors.toList());
        var argTypes = arguments.stream().map(Object::getClass).collect(Collectors.toList());
        var serviceBean  = context.getBean(call.getService());
        try {
            // ZMIENIONE toArray
            return serviceBean.getClass().getMethod(call.getMethod(), argTypes.toArray(Class[]::new))
                    // ZMIENIONE toArray
                    .invoke(serviceBean, arguments.toArray(Object[]::new));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    JavaType javaType(RemoteType remoteType) {
        var simpleType = objectMapper.getTypeFactory().constructFromCanonical(remoteType.getType());
        var typeParameters = remoteType.getParameters().stream()
                .map(it -> javaType(it))
                .collect(Collectors.toList());
        return remoteType.getParameters().isEmpty()
                ? simpleType
                : objectMapper.getTypeFactory().constructParametricType(
                simpleType.getRawClass(),
                // ZMIENIONE toArray
                typeParameters.toArray(JavaType[]::new)
        );
    }
}