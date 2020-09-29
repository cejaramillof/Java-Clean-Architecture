package com.rickandmorty.application.data.mapper;

import com.rickandmorty.application.data.character.entity.CharacterEntity;
import com.rickandmorty.application.data.character.entity.LocationEntity;
import com.rickandmorty.application.data.character.entity.OriginEntity;
import com.rickandmorty.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickandmorty.application.domain.model.CharacterInfo;
import com.rickandmorty.common.mapper.Mapper;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CharacterInfoToCharacterEntityMapperTests {

  private Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper;
  private CharacterInfo characterInfo;
  private CharacterEntity characterEntity;
  private List<String> episode;
  private LocationEntity location;
  private OriginEntity origin;

  private final String DATA_CHARACTER_INFO_NAME = "Ricky";
  private final String DATA_CHARACTER_INFO_STATUS = "Alive";
  private final String DATA_CHARACTER_INFO_SPECIES = "Human";
  private final String DATA_CHARACTER_INFO_GENDER = "Male";

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

  @Before
  public void setUp() {
    characterInfoToCharacterEntityMapper = new CharacterInfoToCharacterEntityMapper();

    characterInfo = new CharacterInfo();
    characterInfo.gender = DATA_CHARACTER_INFO_GENDER;
    characterInfo.name = DATA_CHARACTER_INFO_NAME;
    characterInfo.species = DATA_CHARACTER_INFO_SPECIES;
    characterInfo.status = DATA_CHARACTER_INFO_STATUS;

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
  public void shouldReturnValidCharacterInfo_whenReverseMapIsCalled() {
    CharacterInfo characterInfo = characterInfoToCharacterEntityMapper.reverseMap(characterEntity);

    Assert.assertEquals(DATA_CHARACTER_INFO_GENDER, characterInfo.gender);
    Assert.assertEquals(DATA_CHARACTER_INFO_NAME, characterInfo.name);
    Assert.assertEquals(DATA_CHARACTER_INFO_SPECIES, characterInfo.species);
    Assert.assertEquals(DATA_CHARACTER_INFO_STATUS, characterInfo.status);
    Assert.assertEquals(null, characterInfo.firstAppearance);
  }

  @Test
  public void shouldReturnNull_whenMapIsCalled() {
    Assert.assertEquals(null, characterInfoToCharacterEntityMapper.map(characterInfo));
  }
}
