package Entities;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable

public class Administrator {
	private int hamdi;
	
	private int saidi;

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
