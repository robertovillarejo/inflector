package mx.infotec.dads.inflector.core;

/**
 * The analysis of a Word
 * @author Roberto Villarejo Martinez <roberto.villarejo@infotec.mx>
 *
 */
public class Analysis {
	private String lemma;
	private String tag;

	/**
	 * Constructor for Analysis
	 * @param lemma
	 * @param tag
	 */
	public Analysis(String lemma, String tag) {
		this.lemma = lemma;
		this.tag = tag;
	}

	/**
	 * Returns the lemma
	 * @return
	 */
	public String getLemma() {
		return lemma;
	}

	/**
	 * Set the lemma for the analysis
	 * @param lemma
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	/**
	 * Returns the tag
	 * @return
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag for the analysis
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Analysis))
			return false;
		final Analysis other = (Analysis) obj;
		return (this.lemma.equals(other.getLemma())) && (this.tag.equals(other.getTag()));
	}

	@Override
	public int hashCode() {
		return this.lemma.hashCode() + this.tag.hashCode();
	}
}
