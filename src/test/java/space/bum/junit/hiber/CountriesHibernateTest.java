package space.bum.junit.hiber;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import space.bum.junit.hiber.model.Country;

class CountriesHibernateTest {

  private List<Country> expectedCountryList = new ArrayList<>();
  private List<Country> expected국포함CountryList = new ArrayList<>();
  public static final String[][] COUNTRY_INIT_DATA = { { "호주", "AU" },
      { "대한민국", "KR" }, { "일본", "JP" }, { "중국", "CN" },
      { "미국", "US" }, { "영국", "UK" } };

  private EntityManagerFactory emf;
  private EntityManager em;

  @BeforeEach
  void setUp() {
    initExpectedCountryLists();
    emf = Persistence.createEntityManagerFactory("manning.hibernate");
    em = emf.createEntityManager();
    em.getTransaction().begin();

    for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
      var c = new Country(COUNTRY_INIT_DATA[i][0], COUNTRY_INIT_DATA[i][1]);
      em.persist(c);
    }
    em.getTransaction().commit();
  }

  @Test
  public void testCountryList() {
    @SuppressWarnings("unchecked")
    List<Country> countries = em.createQuery("select c from Country c")
        .getResultList();
    assertNotNull(countries);
    assertEquals(COUNTRY_INIT_DATA.length, countries.size());
    countries
        .forEach(country -> assertTrue(expectedCountryList.contains(country)));
  }

  @Test
  public void test국CountryList() {
    @SuppressWarnings("unchecked")
    List<Country> countries = em
        .createQuery("select c from Country c where c.name like '%국%'")
        .getResultList();
    assertNotNull(countries);
    assertEquals(expected국포함CountryList.size(), countries.size());
    countries.forEach(
        country -> assertTrue(expected국포함CountryList.contains(country)));
  }

  @AfterEach
  public void dropDown() {
    em.close();
    emf.close();
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
