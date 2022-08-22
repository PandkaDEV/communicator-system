package io.pieszku.messenger.api.di;

import com.google.inject.AbstractModule;
import io.pieszku.messenger.api.MessengerControllerAPI;
import io.pieszku.messenger.api.MessengerSubscriberCache;

public class DependencyInjectionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MessengerSubscriberCache.class).toInstance(MessengerControllerAPI.getInstance().getSubscriberCache());
    }
}
