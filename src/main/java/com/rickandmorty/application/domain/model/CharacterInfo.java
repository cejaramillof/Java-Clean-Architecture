package com.rickandmorty.application.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterInfo {
  @JsonProperty
  public String name;
  @JsonProperty
  public String status;
  @JsonProperty
  public String species;
  @JsonProperty
  public String gender;
  @JsonProperty
  public FirstAppearance firstAppearance;
}
