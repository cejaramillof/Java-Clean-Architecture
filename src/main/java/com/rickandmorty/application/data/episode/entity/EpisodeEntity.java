package com.rickandmorty.application.data.episode.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class EpisodeEntity {
  @JsonProperty
  public String id;
  @JsonProperty
  public String name;
  @JsonProperty("air_date")
  public String airDate;
  @JsonProperty
  public String episode;
  @JsonProperty
  public List<String> chartacters;
  @JsonProperty
  public String url;
  @JsonProperty
  public String created;
}