package com.rickandmorty.application.data.character.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CharacterEntity {
  @JsonProperty
  public String id;
  @JsonProperty
  public String name;
  @JsonProperty
  public String status;
  @JsonProperty
  public String species;
  @JsonProperty
  public String type;
  @JsonProperty
  public String gender;
  @JsonProperty
  public OriginEntity origin;
  @JsonProperty
  public LocationEntity location;
  @JsonProperty
  public String image;
  @JsonProperty
  public List<String> episode;
  @JsonProperty
  public String url;
  @JsonProperty
  public String created;
}
