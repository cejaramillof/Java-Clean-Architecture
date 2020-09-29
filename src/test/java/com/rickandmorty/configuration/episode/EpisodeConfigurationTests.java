package com.rickandmorty.configuration.episode;

import com.rickandmorty.application.data.episode.datasource.EpisodeDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestOperations;

import static org.hamcrest.Matchers.instanceOf;

public class EpisodeConfigurationTests {

  EpisodeConfiguration episodeConfiguration;

  @Mock
  private RestOperations restOperations;

  @Mock
  private EpisodeProperties episodeProperties;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    episodeConfiguration = new EpisodeConfiguration();
  }

  @Test
  public void shouldReturnACorrectInstanceOfEpisodeDataSource() {
    Assert.assertThat(
        episodeConfiguration.episodeDatasource(restOperations, episodeProperties),
        instanceOf(EpisodeDataSource.class)
    );
  }

  @Test
  public void shouldReturnAValidInstanceOfEpisodeProperties() {
    Assert.assertThat(
        episodeConfiguration.episodeProperties(),
        instanceOf(EpisodeProperties.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfRestOperations() {
    Assert.assertThat(
        episodeConfiguration.restOperations(),
        instanceOf(RestOperations.class)
    );
  }

}
