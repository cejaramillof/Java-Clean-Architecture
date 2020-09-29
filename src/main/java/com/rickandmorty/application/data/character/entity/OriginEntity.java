package com.rickandmorty.application.data.character.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OriginEntity {
  @JsonProperty
  public String name;
  @JsonProperty
  public String url;
}