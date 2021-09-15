package com.tarabutgateway.assignment.user_pref_write.web;

import com.tarabutgateway.assignment.user_pref_write.model.UserPreference;
import com.tarabutgateway.assignment.user_pref_write.service.UserPreferenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mahmood
 * @since 9/14/21
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user/preference")
public class UserPrefController {

    private final UserPreferenceService service;

    @PostMapping()
    public ResponseEntity<Void> createUserPref(@RequestBody @Valid UserPreference request) {
        service.createOrUpdatePreferences(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> getUserPref(@PathVariable("user_id") String userId) {
        service.deletePreferences(userId);
        return ResponseEntity.ok().build();
    }
}
