package com.rickandmorty.application.data.character.repository;

import com.rickandmorty.application.data.character.datasource.CharacterDataSource;
import com.rickandmorty.application.data.character.entity.CharacterEntity;
import com.rickandmorty.application.domain.model.CharacterInfo;
import com.rickandmorty.application.domain.model.FirstAppearance;
import com.rickandmorty.application.domain.repository.CharacterRepository;
import com.rickandmorty.common.mapper.Mapper;
import com.rickandmorty.application.data.episode.datasource.EpisodeDataSource;
import com.rickandmorty.application.data.episode.entity.EpisodeEntity;

public class CharacterApiRepository implements CharacterRepository {

  private final CharacterDataSource characterDatasource;
  private final EpisodeDataSource episodeDatasource;
  private final Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper;
  private final Mapper<FirstAppearance, EpisodeEntity> firstAppearanceToEpisodeEntityMapper;

  public CharacterApiRepository(
      CharacterDataSource characterDatasource,
      EpisodeDataSource episodeDatasource,
      Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper,
      Mapper<FirstAppearance, EpisodeEntity> firstAppearanceToEpisodeEntityMapper
  ) {
    this.characterDatasource = characterDatasource;
    this.episodeDatasource = episodeDatasource;
    this.characterInfoToCharacterEntityMapper = characterInfoToCharacterEntityMapper;
    this.firstAppearanceToEpisodeEntityMapper = firstAppearanceToEpisodeEntityMapper;
  }

  @Override public CharacterInfo getInfo(String code) {
    CharacterEntity characterEntity = characterDatasource.getCharacter(code);
    CharacterInfo characterInfo = characterInfoToCharacterEntityMapper.reverseMap(characterEntity);
    characterInfo.firstAppearance = getWith(characterEntity);
    return characterInfo;
  }

  private FirstAppearance getWith(CharacterEntity characterEntity) {
    String[] episodeUrlSplitted = characterEntity.episode.get(0).split("/");
    String episodeId = episodeUrlSplitted[episodeUrlSplitted.length - 1];
    EpisodeEntity episodeEntity = episodeDatasource.findBy(episodeId);
    return firstAppearanceToEpisodeEntityMapper.reverseMap(episodeEntity);
  }
}
