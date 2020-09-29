package com.rickandmorty.application.data.mapper;

import com.rickandmorty.application.data.character.mapper.FirstAppearanceToEpisodeEntityMapper;
import com.rickandmorty.application.data.episode.entity.EpisodeEntity;
import com.rickandmorty.application.domain.model.FirstAppearance;
import com.rickandmorty.common.mapper.Mapper;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class FirstAppearanceToEpisodeEntityMapperTests {

  private Mapper<FirstAppearance, EpisodeEntity> firstAppearanceToEpisodeEntityMapper;

  private EpisodeEntity episodeEntity;
  private FirstAppearance firstAppearance;

  private final String DATA_EPISODE_ENTITY_AIRDATE = "December 2, 2013";
  private final String DATA_EPISODE_ENTITY_CREATED = "2017-11-10T12:56:33.798Z";
  private final String DATA_EPISODE_ENTITY_EPISODE = "S01E01";
  private final String DATA_EPISODE_ENTITY_ID = "1";
  private final String DATA_EPISODE_ENTITY_NAME = "Pilot";
  private final String DATA_EPISODE_ENTITY_URL = "https://rickandmortyapi.com/api/episode/1";
  private final String DATA_EPISODE_CHARACTERS_URL = "https://rickandmortyapi.com/api/character/1";

  private final String DATA_FIRST_APPEARANCE_EPISODE = "S01E01";
  private final String DATA_FIRST_APPEARANCE_NAME = "Pilot";

  @Before
  public void setUp() {
    firstAppearanceToEpisodeEntityMapper = new FirstAppearanceToEpisodeEntityMapper();

    episodeEntity = new EpisodeEntity();
    episodeEntity.airDate = DATA_EPISODE_ENTITY_AIRDATE;
    episodeEntity.chartacters = new ArrayList();
    episodeEntity.created = DATA_EPISODE_ENTITY_CREATED;
    episodeEntity.episode = DATA_EPISODE_ENTITY_EPISODE;
    episodeEntity.id = DATA_EPISODE_ENTITY_ID;
    episodeEntity.name = DATA_EPISODE_ENTITY_NAME;
    episodeEntity.url = DATA_EPISODE_ENTITY_URL;

    episodeEntity.chartacters.add(DATA_EPISODE_CHARACTERS_URL);

    firstAppearance = new FirstAppearance();
    firstAppearance.code = DATA_FIRST_APPEARANCE_EPISODE;
    firstAppearance.name = DATA_FIRST_APPEARANCE_NAME;
  }

  @Test
  public void shouldReturnNull_whenMapIsCalled() {
    Assert.assertEquals(null, firstAppearanceToEpisodeEntityMapper.map(firstAppearance));
  }

  @Test
  public void shouldReturnValidFistAppearance_whenReverseMapIsCalled() {
    FirstAppearance firstAppearance = firstAppearanceToEpisodeEntityMapper.reverseMap(episodeEntity);

    Assert.assertEquals(DATA_FIRST_APPEARANCE_EPISODE, firstAppearance.code);
    Assert.assertEquals(DATA_FIRST_APPEARANCE_NAME, firstAppearance.name);
  }
}
