package com.rickandmorty.common.mapper;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTests {

  private MapperChild mapperChild;

  private final static int DATA_STATUS = 200;
  private final static String DATA_MESSAGE = "message";

  @Before
  public void setup() {
    mapperChild = new MapperChild();
  }

  @Test
  public void shouldReturnAMapOfNewSingleObject() {
    MapperModelMock result =
        mapperChild.map(new MapperModelMock(DATA_STATUS, DATA_MESSAGE));

    Assert.assertEquals(DATA_STATUS, result.status);
    Assert.assertEquals(DATA_MESSAGE, result.message);
  }

  @Test
  public void shouldReturnAReverseMapOfNewSingleObject() {
    MapperModelMock result =
        mapperChild.reverseMap(new MapperModelMock(DATA_STATUS, DATA_MESSAGE));

    Assert.assertEquals(DATA_STATUS, result.status);
    Assert.assertEquals(DATA_MESSAGE, result.message);
  }

  @Test
  public void shouldReturnAListOfMapOfNewSingleObject() {

    MapperModelMock mapperModelMock = new MapperModelMock(DATA_STATUS, DATA_MESSAGE);

    List<MapperModelMock> mapperModelMockList = new ArrayList<>();
    mapperModelMockList.add(mapperModelMock);
    mapperModelMockList.add(mapperModelMock);
    mapperModelMockList.add(mapperModelMock);

    List<MapperModelMock> resultList =
        mapperChild.map(mapperModelMockList);

    Assert.assertThat(resultList, hasSize(3));

    Assert.assertEquals(DATA_STATUS, resultList.get(0).status);
    Assert.assertEquals(DATA_MESSAGE, resultList.get(0).message);
  }

  @Test
  public void shouldReturnAListOfReverseMapOfNewSingleObject() {

    MapperModelMock mapperModelMock = new MapperModelMock(DATA_STATUS, DATA_MESSAGE);

    List<MapperModelMock> mapperModelMockList = new ArrayList<>();
    mapperModelMockList.add(mapperModelMock);
    mapperModelMockList.add(mapperModelMock);
    mapperModelMockList.add(mapperModelMock);

    List<MapperModelMock> resultList =
        mapperChild.reverseMap(mapperModelMockList);

    Assert.assertThat(resultList, hasSize(3));

    Assert.assertEquals(DATA_STATUS, resultList.get(0).status);
    Assert.assertEquals(DATA_MESSAGE, resultList.get(0).message);
  }

  private class MapperChild extends Mapper<MapperModelMock, MapperModelMock> {

    @Override public MapperModelMock map(MapperModelMock value) {
      MapperModelMock mapperModelMock = new MapperModelMock();
      mapperModelMock.status = value.status;
      mapperModelMock.message = value.message;
      return mapperModelMock;
    }

    @Override public MapperModelMock reverseMap(MapperModelMock value) {
      MapperModelMock mapperModelMock = new MapperModelMock();
      mapperModelMock.status = value.status;
      mapperModelMock.message = value.message;
      return mapperModelMock;
    }
  }

  private class MapperModelMock {
    public int status;
    public String message;

    public MapperModelMock(int status, String message) {
      this.status = status;
      this.message = message;
    }

    public MapperModelMock() {}
  }
}
