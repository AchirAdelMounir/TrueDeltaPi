package Entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="Flux")
public class Flux implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	PortfolioSecurityKey id;
 
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @MapsId("PortfolioId")
    @JoinColumn(name = "Id_Portfolio")
    Portfolio P;
 
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @MapsId("SecurityId")
    @JoinColumn(name = "Id_Security")
    Security S;
    @Column(name="NbrOfStock")
    int volume;
    @Column(name="poids")
    float poids;
	public PortfolioSecurityKey getId() {
		return id;
	}
	public void setId(PortfolioSecurityKey id) {
		this.id = id;
	}
	public Portfolio getP() {
		return P;
	}
	public void setP(Portfolio p) {
		P = p;
	}
	public Security getS() {
		return S;
	}
	public void setS(Security s) {
		S = s;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public float getPoids() {
		return poids;
	}
	public void setPoids(float poids) {
		this.poids = poids;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((P == null) ? 0 : P.hashCode());
		result = prime * result + ((S == null) ? 0 : S.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Float.floatToIntBits(poids);
		result = prime * result + volume;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flux other = (Flux) obj;
		if (P == null) {
			if (other.P != null)
				return false;
		} else if (!P.equals(other.P))
			return false;
		if (S == null) {
			if (other.S != null)
				return false;
		} else if (!S.equals(other.S))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Float.floatToIntBits(poids) != Float.floatToIntBits(other.poids))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}

}
