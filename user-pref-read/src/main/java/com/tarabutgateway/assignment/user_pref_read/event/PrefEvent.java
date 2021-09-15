package com.tarabutgateway.assignment.user_pref_read.event;

import com.tarabutgateway.assignment.user_pref_read.model.UserPreference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * user preferences event
 * @author mahmood
 * @since 9/15/21
 */
@Data
@Builder
@AllArgsConstructor
@NotNull
public class PrefEvent implements Serializable {
    /**
     * data
     */
    UserPreference preference;
    /**
     * event type
     */
    EventType eventType;
}
