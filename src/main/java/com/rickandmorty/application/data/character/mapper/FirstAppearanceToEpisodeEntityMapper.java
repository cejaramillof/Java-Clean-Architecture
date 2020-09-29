package com.rickandmorty.application.data.character.mapper;

import com.rickandmorty.application.domain.model.FirstAppearance;
import com.rickandmorty.common.mapper.Mapper;
import com.rickandmorty.application.data.episode.entity.EpisodeEntity;

public class FirstAppearanceToEpisodeEntityMapper extends Mapper<FirstAppearance, EpisodeEntity> {

  @Override public EpisodeEntity map(FirstAppearance value) {
    return null;
  }

  @Override public FirstAppearance reverseMap(EpisodeEntity value) {
    FirstAppearance firstAppearance = new FirstAppearance();
    firstAppearance.name = value.name;
    firstAppearance.code = value.episode;
    return firstAppearance;
  }
}
