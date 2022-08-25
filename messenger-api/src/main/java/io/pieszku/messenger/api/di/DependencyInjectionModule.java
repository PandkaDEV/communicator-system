package io.pieszku.messenger.api.di;

import com.google.inject.AbstractModule;
import io.pieszku.messenger.api.ControllerAPI;
import io.pieszku.messenger.api.SubscriberCache;

public class DependencyInjectionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SubscriberCache.class).toInstance(ControllerAPI.getInstance().getSubscriberCache());
    }
}
