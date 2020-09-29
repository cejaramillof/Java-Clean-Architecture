package com.rickandmorty.application.data.episode.datasource;

import com.rickandmorty.application.data.episode.entity.EpisodeEntity;

public interface EpisodeDataSource {
  EpisodeEntity findBy(String id);
}
