package com.rickandmorty.application.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstAppearance {
  @JsonProperty
  public String name;
  @JsonProperty
  public String code;
}
