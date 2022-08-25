package io.pieszku.messenger.api;

import io.pieszku.messenger.api.stereotype.Handler;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class ChannelHandlerScanner {

    private List<Class<?>> classes;

    public ChannelHandlerScanner(String packageName) {
        this.scanHandlers(packageName);
    }

    private void scanHandlers(String packageName) {
        Reflections reflections = new Reflections(packageName);
        this.classes = new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));
    }

    public List<Class<?>> getClasses() {
        return classes;
    }
}
