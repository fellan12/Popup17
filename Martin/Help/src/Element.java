public class Element {
	private Type type;
	private String value;

	public Element(String value, int patternId) {
		this.value = value;
		if (value.startsWith("<") && value.endsWith(">")) {
			this.type = Type.PLACEHOLDER;
			this.value = this.value + patternId;
		}
		else
			this.type = Type.WORD;
	}

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	/**
	 * Sets this to have the same value and type as the argument
	 */
	public void copy(Element e) {
		this.type = e.type;
		this.value = e.value;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Element e = (Element) obj;
			return (this.type == e.type && this.value.equals(e.value));
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		return value.hashCode() * (3*type.hashCode());
	}
}
