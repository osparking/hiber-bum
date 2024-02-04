package space.bum.junit.hiber;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import space.bum.junit.hiber.model.Country;

class CountriesHibernateTest {
  
  private List<Country> expectedCountryList = new ArrayList<>();
  private List<Country> expected국포함CountryList = new ArrayList<>();
  public static final String[][] COUNTRY_INIT_DATA = { { "호주", "AU" },
      { "대한민국", "KR" }, { "일본", "JP" }, { "중국", "CN" },
      { "미국", "US" }, { "영국", "UK" } };
  
  @BeforeEach
  void setUp() {
    initExpectedCountryLists();
  }
  
  private void initExpectedCountryLists() {
    for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
      var c = new Country(COUNTRY_INIT_DATA[i][0], COUNTRY_INIT_DATA[i][1]);
      expectedCountryList.add(c);
      if (c.getName().contains("국")) {
        expected국포함CountryList.add(c);
      }
    }
  }
}
