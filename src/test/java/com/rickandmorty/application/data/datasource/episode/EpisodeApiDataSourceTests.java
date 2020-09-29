package com.rickandmorty.application.data.datasource.episode;

import com.rickandmorty.application.data.episode.datasource.EpisodeApiDataSource;
import com.rickandmorty.application.data.episode.datasource.EpisodeDataSource;
import com.rickandmorty.application.data.episode.entity.EpisodeEntity;
import com.rickandmorty.configuration.episode.EpisodeProperties;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.instanceOf;

@RunWith(SpringJUnit4ClassRunner.class)
public class EpisodeApiDataSourceTests {
  private EpisodeApiDataSource episodeApiDatasource;

  private EpisodeEntity episodeEntity;

  @Mock
  private EpisodeProperties episodeProperties;

  @Mock
  private RestOperations restOperations;

  private final String DATA_EPISODE_ID = "1";

  private final String DATA_EPISODE_ENTITY_AIRDATE = "December 2, 2013";
  private final String DATA_EPISODE_ENTITY_CREATED = "2017-11-10T12:56:33.798Z";
  private final String DATA_EPISODE_ENTITY_EPISODE = "S01E01";
  private final String DATA_EPISODE_ENTITY_ID = "1";
  private final String DATA_EPISODE_ENTITY_NAME = "Pilot";
  private final String DATA_EPISODE_ENTITY_URL = "https://rickandmortyapi.com/api/episode/1";
  private final String DATA_EPISODE_CHARACTERS_URL = "https://rickandmortyapi.com/api/character/1";

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    episodeEntity = new EpisodeEntity();
    episodeEntity.airDate = DATA_EPISODE_ENTITY_AIRDATE;
    episodeEntity.chartacters = new ArrayList();
    episodeEntity.chartacters.add(DATA_EPISODE_CHARACTERS_URL);
    episodeEntity.created = DATA_EPISODE_ENTITY_CREATED;
    episodeEntity.episode = DATA_EPISODE_ENTITY_EPISODE;
    episodeEntity.id = DATA_EPISODE_ENTITY_ID;
    episodeEntity.name = DATA_EPISODE_ENTITY_NAME;
    episodeEntity.url = DATA_EPISODE_ENTITY_URL;

    episodeApiDatasource = new EpisodeApiDataSource(restOperations, episodeProperties);
  }

  @Test
  public void shouldBeValidInstanceOfEpisodeDatasource_whenCreated() {
    Assert.assertThat(episodeApiDatasource, instanceOf(EpisodeDataSource.class));
  }

  @Test
  public void shouldReturnValidEpisodeEntity_whenFindByIsCalled() {
    when(restOperations.getForObject(episodeProperties.getEpisodeURL(DATA_EPISODE_ID), EpisodeEntity.class)).thenReturn(episodeEntity);

    EpisodeEntity episodeEntity = episodeApiDatasource.findBy(DATA_EPISODE_ID);

    Assert.assertEquals(DATA_EPISODE_ENTITY_AIRDATE, episodeEntity.airDate);
    Assert.assertEquals(DATA_EPISODE_ENTITY_CREATED, episodeEntity.created);
    Assert.assertEquals(DATA_EPISODE_ENTITY_EPISODE, episodeEntity.episode);
    Assert.assertEquals(DATA_EPISODE_ENTITY_ID, episodeEntity.id);
    Assert.assertEquals(DATA_EPISODE_ENTITY_NAME, episodeEntity.name);
    Assert.assertEquals(DATA_EPISODE_ENTITY_URL, episodeEntity.url);
    Assert.assertEquals(1, episodeEntity.chartacters.size());
    Assert.assertEquals(DATA_EPISODE_CHARACTERS_URL, episodeEntity.chartacters.get(0));
  }
}
