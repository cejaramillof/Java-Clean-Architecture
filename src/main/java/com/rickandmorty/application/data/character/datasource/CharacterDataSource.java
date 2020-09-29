package com.rickandmorty.application.data.character.datasource;

import com.rickandmorty.application.data.character.entity.CharacterEntity;

public interface CharacterDataSource {
  CharacterEntity getCharacter(String code);
}
