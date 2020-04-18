package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PortfolioSecurityKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "Portfolio_id")
	    int PortfolioId;
	@Column(name="Security_id")
	int SecurityId;
	public int getPortfolioI() {
		return PortfolioId;
	}
	public void setPortfolioI(int portfolioId) {
		PortfolioId = portfolioId;
	}
	public int getSecurityId() {
		return SecurityId;
	}
	public void setSecurityId(int securityId) {
		SecurityId = securityId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + PortfolioId;
		result = prime * result + SecurityId;
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
		PortfolioSecurityKey other = (PortfolioSecurityKey) obj;
		if (PortfolioId != other.PortfolioId)
			return false;
		if (SecurityId != other.SecurityId)
			return false;
		return true;
	}

	// standard constructors, getters, and setters
	// hashcode and equals implementation
}

