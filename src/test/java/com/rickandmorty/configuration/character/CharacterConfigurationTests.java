package com.rickandmorty.configuration.character;

import com.rickandmorty.application.data.character.datasource.CharacterApiDataSource;
import com.rickandmorty.application.data.character.datasource.CharacterDataSource;
import com.rickandmorty.application.data.character.entity.CharacterEntity;
import com.rickandmorty.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickandmorty.application.data.character.mapper.FirstAppearanceToEpisodeEntityMapper;
import com.rickandmorty.application.data.character.repository.CharacterApiRepository;
import com.rickandmorty.application.data.episode.datasource.EpisodeDataSource;
import com.rickandmorty.application.data.episode.entity.EpisodeEntity;
import com.rickandmorty.application.domain.model.CharacterInfo;
import com.rickandmorty.application.domain.model.FirstAppearance;
import com.rickandmorty.application.domain.repository.CharacterRepository;
import com.rickandmorty.application.domain.usecase.CharacterInfoUseCase;
import com.rickandmorty.application.Character;
import com.rickandmorty.common.mapper.Mapper;
import com.rickandmorty.configuration.episode.EpisodeProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;

import static org.hamcrest.Matchers.instanceOf;

@RunWith(SpringJUnit4ClassRunner.class)
public class CharacterConfigurationTests {

  private CharacterConfiguration characterConfiguration;

  @Mock
  private RestOperations restOperations;

  @Mock
  private CharacterProperties characterProperties;

  @Mock
  private CharacterDataSource characterDataSource;

  @Mock
  private CharacterRepository characterRepository;

  @Mock
  private Mapper<FirstAppearance, EpisodeEntity> firstAppearanceEpisodeEntityMapper;

  @Mock
  private Mapper<CharacterInfo, CharacterEntity> characterInfoCharacterEntityMapper;

  @Mock
  private CharacterInfoUseCase characterInfoUseCase;

  @Mock
  private EpisodeDataSource episodeDataSource;

  @Mock
  private EpisodeProperties episodeProperties;



  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    characterConfiguration = new CharacterConfiguration();
  }

  @Test
  public void shouldReturnACorrectInstanceOfCharacter() {
    Assert.assertThat(characterConfiguration.character(characterInfoUseCase), instanceOf(Character.class));
  }

  @Test
  public void shouldReturnACorrectInstanceOfCharacterDataSource() {
    Assert.assertThat(
        characterConfiguration.characterDatasource(characterProperties, restOperations),
        instanceOf(CharacterApiDataSource.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfCharacterRepository() {
    Assert.assertThat(characterConfiguration.characterRepository(
        characterDataSource,
        episodeDataSource,
        characterInfoCharacterEntityMapper,
        firstAppearanceEpisodeEntityMapper
        ), instanceOf(CharacterApiRepository.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfCharacterInfoUseCase() {
    Assert.assertThat(
        characterConfiguration.characterInfoUseCase(characterRepository),
        instanceOf(CharacterInfoUseCase.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfFirstAppearanceMapper() {
    Assert.assertThat(
        characterConfiguration.firstAppearanceToEpisodeEntityMapper(),
        instanceOf(FirstAppearanceToEpisodeEntityMapper.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfCharacterInfoMapper() {
    Assert.assertThat(
        characterConfiguration.characterInfoToCharacterEntityMapper(),
        instanceOf(CharacterInfoToCharacterEntityMapper.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfEpisodeDataSource() {
    Assert.assertThat(
        characterConfiguration.episodeDatasource(restOperations, episodeProperties),
        instanceOf(EpisodeDataSource.class));
  }

  @Test
  public void shouldReturnACorrectInstanceOfRestOperations() {
    Assert.assertThat(
        characterConfiguration.restOperations(),
        instanceOf(RestOperations.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfCharacterProperties() {
    Assert.assertThat(
        characterConfiguration.characterProperties(),
        instanceOf(CharacterProperties.class)
    );
  }

  @Test
  public void shouldReturnACorrectInstanceOfEpisodeProperties() {
    Assert.assertThat(
        characterConfiguration.episodeProperties(),
        instanceOf(EpisodeProperties.class)
    );
  }
}
