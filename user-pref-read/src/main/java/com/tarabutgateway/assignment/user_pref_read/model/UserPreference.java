package com.tarabutgateway.assignment.user_pref_read.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Data model for user preferences
 *
 * @author mahmood
 * @since 9/14/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "user_pref_read")
public class UserPreference implements Serializable {
    @NotNull
    @Id
    private String userId;
    @NotEmpty
    @NotNull
    private List<MarketingChannel> marketingPreferences;
}
