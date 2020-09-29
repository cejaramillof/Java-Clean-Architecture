package com.rickandmorty.configuration.episode;

import org.springframework.beans.factory.annotation.Value;

public class EpisodeProperties {
  @Value("${api.baseURL}")
  private String baseURL;

  @Value("${api.paths.episodes}")
  private String episodesEndpoint;

  @Value("${api.paths.episode}")
  private String episodeEndpoint;

  public String getEpisodeURL(String characterId) {
    return baseURL.concat(episodeEndpoint).replace("{episodeId}", characterId);
  }

  public String getEpisodesURL() {
    return baseURL.concat(episodesEndpoint);
  }
}