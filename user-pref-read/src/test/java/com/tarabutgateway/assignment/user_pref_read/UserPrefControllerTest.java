package com.tarabutgateway.assignment.user_pref_read;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarabutgateway.assignment.user_pref_read.event.UserPrefChangeSubscriber;
import com.tarabutgateway.assignment.user_pref_read.model.MarketingChannel;
import com.tarabutgateway.assignment.user_pref_read.model.UserPreference;
import com.tarabutgateway.assignment.user_pref_read.repository.UserPreferencesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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
@MockBean(classes = {UserPrefChangeSubscriber.class, RedissonClient.class})
public class UserPrefControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @MockBean
    private UserPreferencesRepository repository;

    @Test
    void getAllPreferencesTest() throws Exception {

        List<UserPreference> userPreferences = List.of(
                UserPreference.builder()
                        .userId("1")
                        .marketingPreferences(List.of(MarketingChannel.EMAIL))
                        .build(),
                UserPreference.builder()
                        .userId("2")
                        .marketingPreferences(List.of(MarketingChannel.SMS))
                        .build()
        );
        Mockito.when(repository.findAll()).thenReturn(userPreferences);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/v1/preferences/"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<UserPreference> response =
                jsonMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
                });

        Assertions.assertThat(response.size()).isEqualTo(2);
        Assertions.assertThat(response).isEqualTo(userPreferences);
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void getUserPrefTest() throws Exception {

        UserPreference userPreferences = UserPreference.builder()
                .userId("1")
                .marketingPreferences(List.of(MarketingChannel.EMAIL))
                .build();
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(userPreferences));

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/v1/preferences/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        UserPreference response = jsonMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        Assertions.assertThat(response).isEqualTo(userPreferences);
        Mockito.verify(repository, Mockito.times(1)).findById("1");
    }

    @Test
    void userPref_UserNotFoundTest() throws Exception {

        UserPreference userPreferences = UserPreference.builder()
                .userId("1")
                .marketingPreferences(List.of(MarketingChannel.EMAIL))
                .build();
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(userPreferences));

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/api/v1/preferences/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        UserPreference response = jsonMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        Assertions.assertThat(response).isEqualTo(userPreferences);
        Mockito.verify(repository, Mockito.times(1)).findById("1");
    }

}
