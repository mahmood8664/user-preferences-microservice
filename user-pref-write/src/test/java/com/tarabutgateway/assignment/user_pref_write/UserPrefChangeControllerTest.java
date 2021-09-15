package com.tarabutgateway.assignment.user_pref_write;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarabutgateway.assignment.user_pref_write.event.EventType;
import com.tarabutgateway.assignment.user_pref_write.event.PrefEvent;
import com.tarabutgateway.assignment.user_pref_write.event.UserPrefChangePublisher;
import com.tarabutgateway.assignment.user_pref_write.model.MarketingChannel;
import com.tarabutgateway.assignment.user_pref_write.model.UserPreference;
import com.tarabutgateway.assignment.user_pref_write.repository.UserPreferencesRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

/**
 * @author mahmood
 * @since 9/15/21
 */
@SpringBootTest
@AutoConfigureMockMvc
@MockBean(classes = RedissonClient.class)
public class UserPrefChangeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @MockBean
    private UserPreferencesRepository repository;

    @MockBean
    private UserPrefChangePublisher publisher;

    @SneakyThrows
    @Test
    void createUserPrefTest() {

        UserPreference userPreference = UserPreference.builder()
                .userId("1")
                .marketingPreferences(List.of(MarketingChannel.SMS, MarketingChannel.EMAIL))
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/user/preference")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(userPreference))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(repository, Mockito.times(1)).save(userPreference);
        Mockito.verify(publisher, Mockito.times(1)).sendEvent(PrefEvent.builder()
                .eventType(EventType.ADD_OR_EDIT)
                .preference(userPreference)
                .build());
    }

    @SneakyThrows
    @Test
    void deleteUserPrefTest() {

        UserPreference userPreference = UserPreference.builder()
                .userId("1")
                .marketingPreferences(List.of(MarketingChannel.EMAIL))
                .build();

        Mockito.when(repository.findById("1")).thenReturn(Optional.of(userPreference));

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/preference/1")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(repository, Mockito.times(1)).deleteById("1");
        Mockito.verify(publisher, Mockito.times(1)).sendEvent(PrefEvent.builder()
                .eventType(EventType.DELETE)
                .preference(userPreference)
                .build());
    }

}
