package com.rickandmorty.application.data.datasource.character;

import com.rickandmorty.application.data.character.datasource.CharacterApiDataSource;
import com.rickandmorty.application.data.character.datasource.CharacterDataSource;
import com.rickandmorty.application.data.character.entity.CharacterEntity;
import com.rickandmorty.application.data.character.entity.LocationEntity;
import com.rickandmorty.application.data.character.entity.OriginEntity;
import com.rickandmorty.configuration.character.CharacterProperties;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CharacterApiDatasourceTests {

  private final String DATA_CHARACTER_CODE = "1";

  private final String DATA_CHARACTER_ID = "1";
  private final String DATA_CHARACTER_STATUS = "Alive";
  private final String DATA_CHARACTER_SPECIES = "Human";
  private final String DATA_CHARACTER_TYPE = "Unknown";
  private final String DATA_CHARACTER_GENDER = "Male";
  private final String DATA_CHARACTER_IMAGE = "https://example.com/image";
  private final String DATA_CHARACTER_URL = "https://example.com/url";
  private final String DATA_CHARACTER_CREATED = "Jun 01 2000";
  private final String DATA_CHARACTER_NAME = "Ricky";

  private final String DATA_ORIGIN_NAME = "Earth";
  private final String DATA_ORIGIN_URL = "https://example.com/earth";

  private final String DATA_LOCATION_NAME = "Galaxy 1";
  private final String DATA_LOCATION_URL = "https://example.com/galaxy1";

  private final String DATA_EPISODE = "1";


  private CharacterApiDataSource characterApiDatasource;
  private CharacterEntity characterEntity;
  private List<String> episode;
  private LocationEntity location;
  private OriginEntity origin;

  @Mock
  private RestOperations restOperations;

  @Mock
  private CharacterProperties characterProperties;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    characterApiDatasource = new CharacterApiDataSource(characterProperties, restOperations);

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
  public void shouldReturnValidInstanceOfCharacterDatasource_whenCreated() {
    Assert.assertThat(characterApiDatasource, instanceOf(CharacterDataSource.class));
  }

  @Test
  public void shouldReturnCharacterEntity_whenRequestIsExecuted() {
    when(restOperations.getForObject(characterProperties.getCharacterURL(DATA_CHARACTER_CODE), CharacterEntity.class)).thenReturn(characterEntity);

    CharacterEntity characterEntity = characterApiDatasource.getCharacter(DATA_CHARACTER_CODE);

    Assert.assertEquals(DATA_CHARACTER_ID, characterEntity.id);
    Assert.assertEquals(DATA_CHARACTER_CREATED, characterEntity.created);
    Assert.assertEquals(DATA_CHARACTER_GENDER, characterEntity.gender);
    Assert.assertEquals(DATA_CHARACTER_IMAGE, characterEntity.image);
    Assert.assertEquals(DATA_CHARACTER_NAME, characterEntity.name);
    Assert.assertEquals(DATA_CHARACTER_SPECIES, characterEntity.species);
    Assert.assertEquals(DATA_CHARACTER_STATUS, characterEntity.status);
    Assert.assertEquals(DATA_CHARACTER_TYPE, characterEntity.type);
    Assert.assertEquals(DATA_CHARACTER_URL, characterEntity.url);
    Assert.assertEquals(DATA_LOCATION_NAME, characterEntity.location.name);
    Assert.assertEquals(DATA_LOCATION_URL, characterEntity.location.url);
    Assert.assertEquals(DATA_ORIGIN_NAME, characterEntity.origin.name);
    Assert.assertEquals(DATA_ORIGIN_URL, characterEntity.origin.url);
  }
}
