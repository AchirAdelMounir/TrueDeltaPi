package Entities;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Embeddable
public class Administrator  implements Serializable {
	private int hamdi;
	
	private int saidi;
	
	private String mounir;

	public int getHamdi() {
		return hamdi;
	}

	public void setHamdi(int hamdi) {
		this.hamdi = hamdi;
	}

	public int getSaidi() {
		return saidi;
	}

	public void setSaidi(int saidi) {
		this.saidi = saidi;
	}
	
	


}
