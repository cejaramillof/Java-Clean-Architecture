package com.rickandmorty.application.data.character.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationEntity {
  @JsonProperty
  public String name;
  @JsonProperty
  public String url;
}
