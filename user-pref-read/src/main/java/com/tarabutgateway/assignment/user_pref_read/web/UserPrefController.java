package com.tarabutgateway.assignment.user_pref_read.web;

import com.tarabutgateway.assignment.user_pref_read.model.UserPreference;
import com.tarabutgateway.assignment.user_pref_read.service.UserPreferenceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mahmood
 * @since 9/14/21
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/preferences/")
public class UserPrefController {

    private final UserPreferenceService service;

    @ApiOperation(value = "Returns all user preferences")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully perform the operation"),
    })
    @GetMapping("")
    public ResponseEntity<List<UserPreference>> getAll() {
        return ResponseEntity.ok(service.getPreferences());
    }

    @ApiOperation(value = "Return user preferences for the given user id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully perform the operation"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @GetMapping("/{user_id}")
    public ResponseEntity<UserPreference> getUserPref(@PathVariable("user_id") String userId) {
        return ResponseEntity.ok(service.getPreferences(userId));
    }

}
