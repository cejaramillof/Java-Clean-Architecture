package com.rickandmorty.application.domain.usecase;

import com.rickandmorty.application.domain.model.CharacterInfo;
import com.rickandmorty.application.domain.repository.CharacterRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CharacterInfoUseCaseTests {
  private CharacterInfoUseCase characterInfoUseCase;

  private final String DATA_CHARACTER_CODE = "1";

  @Mock
  private CharacterInfo characterInfo;

  @Mock
  private CharacterRepository characterRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    characterInfoUseCase = new CharacterInfoUseCase(characterRepository);
  }

  @Test
  public void shouldReturnValidCharacterInfo_whenExecuteIsCalled() {
    when(characterRepository.getInfo(DATA_CHARACTER_CODE)).thenReturn(characterInfo);

    CharacterInfo characterInfo = characterInfoUseCase.executeWith(DATA_CHARACTER_CODE);

    Assert.assertNotNull(characterInfo);
  }

  @Test
  public void shouldReturnNullValue_whenCodeIsIncorrect() {
    when(characterRepository.getInfo("code")).thenReturn(null);

    CharacterInfo characterInfo = characterInfoUseCase.executeWith("code");

    Assert.assertNull(characterInfo);
  }
}
