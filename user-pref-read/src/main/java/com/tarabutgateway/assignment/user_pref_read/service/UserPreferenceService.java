package com.tarabutgateway.assignment.user_pref_read.service;

import com.tarabutgateway.assignment.user_pref_read.model.UserPreference;
import com.tarabutgateway.assignment.user_pref_read.repository.UserPreferencesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author mahmood
 * @since 9/14/21
 */
@Service
@AllArgsConstructor
public class UserPreferenceService {

    private final UserPreferencesRepository userMarketingPreferencesRepository;

    /**
     * Read all user preferences
     *
     * @return {@link List<UserPreference>}
     */
    public List<UserPreference> getPreferences() {
        return userMarketingPreferencesRepository.findAll();
    }

    /**
     * get preference of given user id
     *
     * @param userId goven user id
     * @return {@link UserPreference}
     */
    public UserPreference getPreferences(String userId) {
        return userMarketingPreferencesRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
