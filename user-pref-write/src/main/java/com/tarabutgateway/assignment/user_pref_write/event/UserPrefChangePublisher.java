package com.tarabutgateway.assignment.user_pref_write.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * User pref event publisher
 *
 * @author mahmood
 * @since 9/15/21
 */
@Component
@AllArgsConstructor
public class UserPrefChangePublisher {

    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;

    /**
     * publish event
     *
     * @param event {@link PrefEvent}
     */
    public void sendEvent(PrefEvent event) {
        try {
            redissonClient.getTopic("user-pref").publish(objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
