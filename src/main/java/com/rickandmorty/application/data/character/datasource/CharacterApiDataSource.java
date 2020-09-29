package com.rickandmorty.application.data.character.datasource;

import com.rickandmorty.application.data.character.entity.CharacterEntity;
import com.rickandmorty.configuration.character.CharacterProperties;
import org.springframework.web.client.RestOperations;

public class CharacterApiDataSource implements CharacterDataSource {
  private final CharacterProperties characterProperties;
  private final RestOperations restOperations;

  public CharacterApiDataSource(CharacterProperties characterProperties, RestOperations restOperations) {
    this.characterProperties = characterProperties;
    this.restOperations = restOperations;
  }

  @Override public CharacterEntity getCharacter(String code) {
    return restOperations.getForObject(characterProperties.getCharacterURL(code), CharacterEntity.class);
  }
}
