package com.rickandmorty.configuration.character;

import com.rickandmorty.application.data.character.datasource.CharacterApiDataSource;
import com.rickandmorty.application.data.character.datasource.CharacterDataSource;
import com.rickandmorty.application.data.character.entity.CharacterEntity;
import com.rickandmorty.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickandmorty.application.data.character.mapper.FirstAppearanceToEpisodeEntityMapper;
import com.rickandmorty.application.data.character.repository.CharacterApiRepository;
import com.rickandmorty.application.domain.model.CharacterInfo;
import com.rickandmorty.application.domain.model.FirstAppearance;
import com.rickandmorty.application.domain.repository.CharacterRepository;
import com.rickandmorty.application.domain.usecase.CharacterInfoUseCase;
import com.rickandmorty.application.Character;
import com.rickandmorty.application.CharacterController;
import com.rickandmorty.common.mapper.Mapper;
import com.rickandmorty.application.data.episode.datasource.EpisodeApiDataSource;
import com.rickandmorty.application.data.episode.datasource.EpisodeDataSource;
import com.rickandmorty.application.data.episode.entity.EpisodeEntity;
import com.rickandmorty.configuration.episode.EpisodeProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CharacterConfiguration {

  @Bean
  Character character(CharacterInfoUseCase characterInfoUseCase) {
    return new CharacterController(characterInfoUseCase);
  }

  @Bean
  CharacterInfoUseCase characterInfoUseCase(CharacterRepository characterRepository) {
    return new CharacterInfoUseCase(characterRepository);
  }

  @Bean
  CharacterRepository characterRepository(
      CharacterDataSource characterDataSource,
      EpisodeDataSource episodeDataSource,
      Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper,
      Mapper<FirstAppearance, EpisodeEntity> firstAppearanceToEpisodeEntityMapper
  ) {
    return new CharacterApiRepository(
        characterDataSource,
        episodeDataSource,
        characterInfoToCharacterEntityMapper,
        firstAppearanceToEpisodeEntityMapper
    );
  }

  @Bean
  EpisodeDataSource episodeDatasource(RestOperations restOperations, EpisodeProperties episodeProperties) {
    return new EpisodeApiDataSource(restOperations, episodeProperties);
  }

  @Bean
  Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper() {
    return new CharacterInfoToCharacterEntityMapper();
  }

  @Bean
  Mapper<FirstAppearance, EpisodeEntity> firstAppearanceToEpisodeEntityMapper() {
    return new FirstAppearanceToEpisodeEntityMapper();
  }

  @Bean
  CharacterDataSource characterDatasource(CharacterProperties characterProperties, RestOperations restOperations) {
    return new CharacterApiDataSource(characterProperties, restOperations);
  }

  @Bean
  RestOperations restOperations() {
    return new RestTemplate();
  }

  @Bean
  CharacterProperties characterProperties() {
    return new CharacterProperties();
  }

  @Bean
  EpisodeProperties episodeProperties() {
    return new EpisodeProperties();
  }
}
