package com.tarabutgateway.assignment.user_pref_write.model;

import lombok.*;
import org.jboss.marshalling.SerializabilityChecker;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Data model for user pref
 * @author mahmood
 * @since 9/14/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "user_pref_write")
public class UserPreference implements Serializable {
    @NotNull
    @Id
    private String userId;
    @NotEmpty
    @NotNull
    private List<MarketingChannel> marketingPreferences;
}
