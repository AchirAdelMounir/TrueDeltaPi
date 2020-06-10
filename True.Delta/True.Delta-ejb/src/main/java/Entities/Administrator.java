package Entities;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@DiscriminatorValue(value="Admin")
@PrimaryKeyJoinColumn(name="Id")
public class Administrator extends User implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

}
