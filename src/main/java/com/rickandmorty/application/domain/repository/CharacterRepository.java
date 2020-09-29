package com.rickandmorty.application.domain.repository;

import com.rickandmorty.application.domain.model.CharacterInfo;

public interface CharacterRepository {
  CharacterInfo getInfo(String code);
}
