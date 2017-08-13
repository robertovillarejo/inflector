package mx.infotec.inflector.engine;
import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class Dictionary {

	BidiMap<String, Analysis> entries = new DualHashBidiMap<>();
	BidiMap<Analysis, String> reverseEntries;
	
	public Dictionary(BufferedReader reader) throws IOException {
		String entry = reader.readLine();
		while(entry != null) {
			String[] elements = entry.split(" ");
			String word = elements[0];
			String lemma = elements[1];
			String tag = elements[2];
			entries.put(word, new Analysis(lemma, tag));
			entry = reader.readLine();
		}
		reader.close();
		reverseEntries = entries.inverseBidiMap();
	}
	
	public Analysis searchForm(String word) {
		return entries.get(word.toLowerCase());
	}
	
	public String getForms(String lemma, String tag) {
		return reverseEntries.get(new Analysis(lemma, tag));
	}
	
	public class Analysis {
		private String lemma;
		private String tag;
		
		public Analysis(String lemma, String tag) {
			this.lemma = lemma;
			this.tag = tag;
		}
		public String getLemma() {
			return lemma;
		}
		public void setLemma(String lemma) {
			this.lemma = lemma;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (!(obj instanceof Analysis)) return false;
			final Analysis other = (Analysis) obj;
			return (this.lemma.equals(other.getLemma()))
					&& (this.tag.equals(other.getTag()));
		}
		@Override
		public int hashCode() {
			return this.lemma.hashCode() + this.tag.hashCode();
		}
	}
	
}
