package com.example.contexthierarchy;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ContextListener {

    @EventListener({ ContextRefreshedEvent.class, ContextStartedEvent.class })
    public void logContext(final ApplicationContextEvent event) {
        try {
            final JSONObject eventJson = new JSONObject();
            eventJson.put(event.getClass().getSimpleName(), toJson(event.getApplicationContext()));
            log.debug(eventJson.toString(4));
        } catch (final JSONException ex) {
            throw new RuntimeException("Unable to JSONize ApplicationContext", ex);
        }
    }

    private JSONObject toJson(final ApplicationContext ctx) throws JSONException {
        final JSONObject contextJson = new JSONObject();
        contextJson.put("name", ctx.getDisplayName());
        contextJson.put("beans", JSONObject.wrap(ctx.getBeanDefinitionNames()));
        if (ctx.getParent() != null) {
            contextJson.put("parent", toJson(ctx.getParent()));
        }

        return contextJson;
    }

}
