package com.rickandmorty.application.data.episode.datasource;

import com.rickandmorty.application.data.episode.entity.EpisodeEntity;
import com.rickandmorty.configuration.episode.EpisodeProperties;
import org.springframework.web.client.RestOperations;

public class EpisodeApiDataSource implements EpisodeDataSource {

  private final RestOperations restOperations;
  private final EpisodeProperties episodeProperties;

  public EpisodeApiDataSource(RestOperations restOperations, EpisodeProperties episodeProperties) {
    this.restOperations = restOperations;
    this.episodeProperties = episodeProperties;
  }

  @Override public EpisodeEntity findBy(String id) {
    return restOperations.getForObject(episodeProperties.getEpisodeURL(id), EpisodeEntity.class);
  }
}
