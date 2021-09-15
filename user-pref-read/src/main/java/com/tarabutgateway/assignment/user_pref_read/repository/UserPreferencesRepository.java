package com.tarabutgateway.assignment.user_pref_read.repository;

import com.tarabutgateway.assignment.user_pref_read.model.UserPreference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mahmood
 * @since 9/14/21
 */
@Repository
public interface UserPreferencesRepository extends MongoRepository<UserPreference, String> {
}
