package uk.co.mistyknives.kick4j.events;

import lombok.*;
import uk.co.mistyknives.kick4j.util.Logger;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

/**
 * Project: Kick4J
 * <br>
 * Copyright MistyKnives © 2022-2023
 * <br>
 * ---------------------------------------
 * <br>
 * All Projects are located on my GitHub
 * <br>
 * Please provide credit where due :)
 * <br>
 * ---------------------------------------
 * <br>
 * https://github.com/MistyKnives
 */
public interface EventSub {

    Map<EventType, List<Object>> eventListeners = new HashMap<>();
    Map<UUID, Event> calledEvents = new HashMap<>();

    /**
     * Register the Listener to the HashMap
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.on(EventType.READY, new ReadyListener())
     * }</pre>
     * @param type EventType e.g. READY
     * @param listener EventListener
     */
    @SneakyThrows
    default void on(EventType type, Class<? extends EventListener> listener) {
        eventListeners.computeIfAbsent(type, k -> new LinkedList<>()).add(listener.newInstance());
    }

    /**
     * Register the Listener to the HashMap
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.on(EventType.READY, (ReadyEvent event) -> console.log("Hello from Kick4J!"));
     * }</pre>
     * @param type EventType e.g. READY
     * @param lambda Lambda instance.
     */
    default <T extends Event> void on(EventType type, Consumer<T> lambda) {
        eventListeners.
                computeIfAbsent(type, k -> new LinkedList<>())
                .add(lambda);
    }

    default <T extends Event> void on(Class<T> eventClass, Consumer<T> consumer) {
        eventListeners.
                computeIfAbsent(EventType.getEventType(eventClass), k -> new LinkedList<>())
                .add(consumer);
    }

    /**
     * Emit an event to the Client.
     * <p>
     * Example Usage:
     * <pre>{@code
     * Kick4J client = ...
     * client.on(EventType.READY, new ReadyEvent(client))
     * }</pre>
     * @param type EventType e.g. READY
     * @param event Event e.g. ReadyEvent
     */
    @SneakyThrows
    default void emit(EventType type, Event event) {
        List<Object> listeners = eventListeners.get(type);
        if (listeners != null) {
            for (Object listener : listeners) {
                if(listener instanceof EventListener) {
                    for(Method method : listener.getClass().getMethods()) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1 && Event.class.isAssignableFrom(parameterTypes[0]) && parameterTypes[0].isAssignableFrom(type.getEventClass())) {
                            if (method.isAnnotationPresent(Subscribe.class)) {
                                method.invoke(listener, event);
                            } else {
                                System.out.printf("[Events] You are trying to fire an event which is missing the annotation @Subscribe - Method: %s#%s()\n", listener.getClass().getSimpleName(), method.getName());
                            }
                        }
                    }
                } else if(listener instanceof Consumer) {
                    ((Consumer<Event>) listener).accept(event);
                }
            }
        }

        calledEvents.put(event.getEventId(), event);
    }
}
