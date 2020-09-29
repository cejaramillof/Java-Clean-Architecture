package com.rickandmorty.configuration.character;

import org.springframework.beans.factory.annotation.Value;

public class CharacterProperties {
  @Value("${api.baseURL}")
  private String baseURL;

  @Value("${api.paths.characters}")
  private String charactersEndpoint;

  @Value("${api.paths.character}")
  private String characterEndpoint;

  public String getCharacterURL(String characterId) {
    return baseURL.concat(characterEndpoint).replace("{characterId}", characterId);
  }

  public String getCharactersURL() {
    return baseURL.concat(charactersEndpoint);
  }
}
