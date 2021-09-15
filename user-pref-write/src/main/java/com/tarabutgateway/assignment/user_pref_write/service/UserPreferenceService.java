package com.tarabutgateway.assignment.user_pref_write.service;

import com.tarabutgateway.assignment.user_pref_write.event.EventType;
import com.tarabutgateway.assignment.user_pref_write.event.PrefEvent;
import com.tarabutgateway.assignment.user_pref_write.event.UserPrefChangePublisher;
import com.tarabutgateway.assignment.user_pref_write.model.UserPreference;
import com.tarabutgateway.assignment.user_pref_write.repository.UserPreferencesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author mahmood
 * @since 9/14/21
 */
@Service
@AllArgsConstructor
public class UserPreferenceService {

    private final UserPreferencesRepository userPreferencesRepository;
    private final UserPrefChangePublisher publisher;

    /**
     * create or update user pref
     *
     * @param request {@link UserPreference}
     */
    public void createOrUpdatePreferences(UserPreference request) {
        userPreferencesRepository.save(request);
        publisher.sendEvent(PrefEvent.builder()
                .eventType(EventType.ADD_OR_EDIT)
                .preference(request)
                .build());
    }

    /**
     * Delete user pref
     *
     * @param userId given user id
     */
    public void deletePreferences(String userId) {
        UserPreference preference = userPreferencesRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userPreferencesRepository.deleteById(userId);
        publisher.sendEvent(PrefEvent.builder()
                .eventType(EventType.DELETE)
                .preference(preference)
                .build());
    }

}
