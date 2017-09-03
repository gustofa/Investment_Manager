package groupone.java.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class Persistible implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
protected Long id;
protected String name;

protected Persistible() {
}
@Column(name = "id")
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
@Column(name = "name")
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public String toString() {
return getId() + "-" + getName();
}
}