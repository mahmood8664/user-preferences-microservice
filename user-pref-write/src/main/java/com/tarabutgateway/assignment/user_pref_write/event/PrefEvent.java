package com.tarabutgateway.assignment.user_pref_write.event;

import com.tarabutgateway.assignment.user_pref_write.model.UserPreference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User pref event
 * @author mahmood
 * @since 9/15/21
 */
@Data
@Builder
@AllArgsConstructor
@NotNull
public class PrefEvent implements Serializable {
    UserPreference preference;
    EventType eventType;
}
