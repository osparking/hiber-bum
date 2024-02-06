package space.bum.junit.hiber.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY")
public class Country {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private int id;
  
  @Column(name = "NAME")
  private String name;
  
  @Column(name = "CODE_NAME")
  private String code_name;

  public Country(String name, String code_name) {
    super();
    this.name = name;
    this.code_name = code_name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code_name, name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Country other = (Country) obj;
    return Objects.equals(code_name, other.code_name)
        && Objects.equals(name, other.name);
  }
}
