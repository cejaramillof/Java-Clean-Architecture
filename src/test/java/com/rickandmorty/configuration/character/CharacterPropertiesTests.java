package com.rickandmorty.configuration.character;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class CharacterPropertiesTests {

  private CharacterProperties characterProperties;

  private final static String DATA_CHARACTER_ID = "123";
  private final static String DATA_BASEURL = "https://rickandmortyapi.com/api/";
  private final static String DATA_CHARACTER_ENDPOINT = "character/{characterId}";
  private final static String DATA_CHARACTERS_ENDPOINT = "character";
  private final static String DATA_CHARACTER_FULL_URL = "https://rickandmortyapi.com/api/character/123";

  @Before
  public void setUp() {
    characterProperties = new CharacterProperties();
    ReflectionTestUtils.setField(characterProperties, "baseURL", DATA_BASEURL);
    ReflectionTestUtils.setField(characterProperties, "characterEndpoint", DATA_CHARACTER_ENDPOINT);
    ReflectionTestUtils.setField(characterProperties, "charactersEndpoint", DATA_CHARACTERS_ENDPOINT);
  }

  @Test
  public void shouldReturnAValidEpisodesURL() {
    Assert.assertNotNull(characterProperties.getCharactersURL());
  }

  @Test
  public void shouldReturnAValidEpisodeURL() {
    Assert.assertNotNull(characterProperties.getCharacterURL(DATA_CHARACTER_ID));
    Assert.assertEquals(characterProperties.getCharacterURL(DATA_CHARACTER_ID), DATA_CHARACTER_FULL_URL);
  }
}
