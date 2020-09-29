package com.rickandmorty.application.presentation.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class CharacterInfoBody {
  @JsonProperty
  @NotNull(message = "Code must have a value")
  public String code;
}
