package com.rickandmorty.configuration.episode;

import com.rickandmorty.application.data.episode.datasource.EpisodeApiDataSource;
import com.rickandmorty.application.data.episode.datasource.EpisodeDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class EpisodeConfiguration {
  @Bean
  EpisodeDataSource episodeDatasource(RestOperations restOperations, EpisodeProperties episodeProperties) {
    return new EpisodeApiDataSource(restOperations, episodeProperties);
  }

  @Bean
  RestOperations restOperations() {
    return new RestTemplate();
  }

  @Bean
  EpisodeProperties episodeProperties() {
    return new EpisodeProperties();
  }
}