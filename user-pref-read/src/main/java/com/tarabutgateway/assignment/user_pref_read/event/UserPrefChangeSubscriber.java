package com.tarabutgateway.assignment.user_pref_read.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarabutgateway.assignment.user_pref_read.repository.UserPreferencesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author mahmood
 * @since 9/15/21
 */
@Component
@AllArgsConstructor
@Slf4j
public class UserPrefChangeSubscriber {

    private final RedissonClient redissonClient;
    private final UserPreferencesRepository repository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    void doSubscribe() {
        subscribe();//do subscribe
    }

    /**
     * subscriber to user preference change
     */
    public void subscribe() {
        redissonClient.getTopic("user-pref").addListener(String.class, (channel, msgStr) -> {
            PrefEvent msg;
            try {
                msg = objectMapper.readValue(msgStr, PrefEvent.class);
            } catch (JsonProcessingException e) {
                log.error("error in deserializing", e);
                throw new RuntimeException(e);
            }

            log.info("Received event with type {}", msg.getEventType());
            switch (msg.getEventType()) {
                case ADD_OR_EDIT:
                    repository.save(msg.getPreference());
                    break;
                case DELETE:
                    repository.deleteById(msg.getPreference().getUserId());
                    break;
            }
            log.info("event processed successfully");
        });
    }

}
