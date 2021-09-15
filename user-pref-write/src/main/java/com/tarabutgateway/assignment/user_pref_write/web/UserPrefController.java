package com.tarabutgateway.assignment.user_pref_write.web;

import com.tarabutgateway.assignment.user_pref_write.model.UserPreference;
import com.tarabutgateway.assignment.user_pref_write.service.UserPreferenceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Create or update user marketing preferences")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully perform the operation"),
            @ApiResponse(code = 400, message = "The Request property was not provide correctly."),
    })
    @PostMapping()
    public ResponseEntity<Void> createUserPref(@RequestBody @Valid UserPreference request) {
        service.createOrUpdatePreferences(request);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Delete user marketing preferences")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully perform the operation"),
            @ApiResponse(code = 400, message = "The Request property was not provide correctly."),
            @ApiResponse(code = 404, message = "Data not found"),
    })
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> getUserPref(@PathVariable("user_id") String userId) {
        service.deletePreferences(userId);
        return ResponseEntity.ok().build();
    }
}
