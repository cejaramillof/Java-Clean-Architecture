package com.rickandmorty.application.data.repository;

import com.rickandmorty.application.data.character.datasource.CharacterApiDataSource;
import com.rickandmorty.application.data.character.datasource.CharacterDataSource;
import com.rickandmorty.application.data.character.entity.CharacterEntity;
import com.rickandmorty.application.data.character.entity.LocationEntity;
import com.rickandmorty.application.data.character.entity.OriginEntity;
import com.rickandmorty.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickandmorty.application.data.character.mapper.FirstAppearanceToEpisodeEntityMapper;
import com.rickandmorty.application.data.character.repository.CharacterApiRepository;
import com.rickandmorty.application.domain.model.CharacterInfo;
import com.rickandmorty.application.domain.model.FirstAppearance;
import com.rickandmorty.application.domain.repository.CharacterRepository;
import com.rickandmorty.common.mapper.Mapper;
import com.rickandmorty.application.data.episode.datasource.EpisodeDataSource;
import com.rickandmorty.application.data.episode.entity.EpisodeEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CharacterApiRepositoryTests {
  private CharacterApiRepository characterApiRepository;

  @Mock
  private CharacterDataSource characterDatasource;
  @Mock

  private EpisodeDataSource episodeDatasource;
  private Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper;
  private Mapper<FirstAppearance, EpisodeEntity> firstAppearanceToEpisodeEntityMapper;

  private final String DATA_CHARACTER_ID = "1";
  private final String DATA_CHARACTER_STATUS = "Alive";
  private final String DATA_CHARACTER_SPECIES = "Human";
  private final String DATA_CHARACTER_TYPE = "Unknown";
  private final String DATA_CHARACTER_GENDER = "Male";
  private final String DATA_CHARACTER_IMAGE = "https://rickandmortyapi.com/api/character/avatar/1.jpeg";
  private final String DATA_CHARACTER_URL = "https://rickandmortyapi.com/api/character/1";
  private final String DATA_CHARACTER_CREATED = "Jun 01 2000";
  private final String DATA_CHARACTER_NAME = "Ricky";

  private final String DATA_ORIGIN_NAME = "Earth";
  private final String DATA_ORIGIN_URL = "https://rickandmortyapi.com/api/location/1";

  private final String DATA_LOCATION_NAME = "Galaxy 1";
  private final String DATA_LOCATION_URL = "https://rickandmortyapi.com/api/location/10";

  private final String DATA_EPISODE = "https://rickandmortyapi.com/api/episode/1";
  private final String DATA_EPISODE_ID = "1";

  private final String DATA_EPISODE_ENTITY_AIRDATE = "December 2, 2013";
  private final String DATA_EPISODE_ENTITY_CREATED = "2017-11-10T12:56:33.798Z";
  private final String DATA_EPISODE_ENTITY_EPISODE = "S01E01";
  private final String DATA_EPISODE_ENTITY_ID = "1";
  private final String DATA_EPISODE_ENTITY_NAME = "Pilot";
  private final String DATA_EPISODE_ENTITY_URL = "https://rickandmortyapi.com/api/episode/1";
  private final String DATA_EPISODE_CHARACTERS_URL = "https://rickandmortyapi.com/api/character/1";

  private final String DATA_CHARACTER_INFO_NAME = "Ricky";
  private final String DATA_CHARACTER_INFO_STATUS = "Alive";
  private final String DATA_CHARACTER_INFO_SPECIES = "Human";
  private final String DATA_CHARACTER_INFO_GENDER = "Male";
  private final String DATA_EPISODE_NAME = "Pilot";
  private final String DATA_EPISODE_CODE = "S01E01";

  private CharacterApiDataSource characterApiDatasource;
  private CharacterEntity characterEntity;
  private List<String> episode;
  private LocationEntity location;
  private OriginEntity origin;
  private EpisodeEntity episodeEntity;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    characterInfoToCharacterEntityMapper = new CharacterInfoToCharacterEntityMapper();
    firstAppearanceToEpisodeEntityMapper = new FirstAppearanceToEpisodeEntityMapper();

    characterApiRepository = new CharacterApiRepository(
        characterDatasource,
        episodeDatasource,
        characterInfoToCharacterEntityMapper,
        firstAppearanceToEpisodeEntityMapper
    );

    episodeEntity = new EpisodeEntity();
    episodeEntity.airDate = DATA_EPISODE_ENTITY_AIRDATE;
    episodeEntity.chartacters = new ArrayList();
    episodeEntity.created = DATA_EPISODE_ENTITY_CREATED;
    episodeEntity.episode = DATA_EPISODE_ENTITY_EPISODE;
    episodeEntity.id = DATA_EPISODE_ENTITY_ID;
    episodeEntity.name = DATA_EPISODE_ENTITY_NAME;
    episodeEntity.url = DATA_EPISODE_ENTITY_URL;

    episodeEntity.chartacters.add(DATA_EPISODE_CHARACTERS_URL);

    episode = new ArrayList<>();
    episode.add(DATA_EPISODE);

    origin = new OriginEntity();
    origin.name = DATA_ORIGIN_NAME;
    origin.url = DATA_ORIGIN_URL;

    location = new LocationEntity();
    location.name = DATA_LOCATION_NAME;
    location.url = DATA_LOCATION_URL;

    characterEntity = new CharacterEntity();
    characterEntity.id = DATA_CHARACTER_ID;
    characterEntity.episode = episode;
    characterEntity.created = DATA_CHARACTER_CREATED;
    characterEntity.gender = DATA_CHARACTER_GENDER;
    characterEntity.image = DATA_CHARACTER_IMAGE;
    characterEntity.location = location;
    characterEntity.name = DATA_CHARACTER_NAME;
    characterEntity.url = DATA_CHARACTER_URL;
    characterEntity.species = DATA_CHARACTER_SPECIES;
    characterEntity.status = DATA_CHARACTER_STATUS;
    characterEntity.type = DATA_CHARACTER_TYPE;
    characterEntity.origin = origin;
  }

  @Test
  public void shouldBeValidInstanceOfCharacterRepository_whenCreated() {
    Assert.assertThat(characterApiRepository, instanceOf(CharacterRepository.class));
  }

  @Test
  public void shouldReturnValidCharacterInfo_whenGetInfoIsCalled() {
    when(characterDatasource.getCharacter(DATA_CHARACTER_ID)).thenReturn(characterEntity);
    when(episodeDatasource.findBy(DATA_EPISODE_ID)).thenReturn(episodeEntity);

    CharacterInfo characterInfo = characterApiRepository.getInfo(DATA_CHARACTER_ID);

    Assert.assertEquals(DATA_CHARACTER_INFO_GENDER, characterInfo.gender);
    Assert.assertEquals(DATA_CHARACTER_INFO_NAME, characterInfo.name);
    Assert.assertEquals(DATA_CHARACTER_INFO_SPECIES, characterInfo.species);
    Assert.assertEquals(DATA_CHARACTER_INFO_STATUS, characterInfo.status);
    Assert.assertEquals(DATA_EPISODE_CODE, characterInfo.firstAppearance.code);
    Assert.assertEquals(DATA_EPISODE_NAME, characterInfo.firstAppearance.name);
  }
}
