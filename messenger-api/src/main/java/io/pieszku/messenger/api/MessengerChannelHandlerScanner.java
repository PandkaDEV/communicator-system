package io.pieszku.messenger.api;

import io.pieszku.messenger.api.stereotype.MessengerHandler;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class MessengerChannelHandlerScanner {

    private List<Class<?>> classes;

    public MessengerChannelHandlerScanner(String packageName) {
        this.scanHandlers(packageName);
    }

    private void scanHandlers(String packageName) {
        Reflections reflections = new Reflections(packageName);
        this.classes = new ArrayList<>(reflections.getTypesAnnotatedWith(MessengerHandler.class));
    }

    public List<Class<?>> getClasses() {
        return classes;
    }
}
