package marcos.ferrel;

public class LegalPerson extends Person {

	private String socialReason;

	private String cnpj;

	/**
	 * @return the socialReason
	 */
	public String getSocialReason() {
		return socialReason;
	}

	/**
	 * @param socialReason the socialReason to set
	 */
	public void setSocialReason(String socialReason) {
		this.socialReason = socialReason;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
