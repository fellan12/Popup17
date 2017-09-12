
public class Element {
	private String value;
	private Type type;

	public Element(String value) {
		if (value.startsWith("<") && value.endsWith(">"))
			this.type = Type.PLACEHOLDER;
		else
			this.type = Type.WORD;
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void copy(Element e) {
		this.value = e.value;
		this.type = e.type;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Element e = (Element) obj;
			return this.value.equals(e.value) && this.type.equals(e.type);
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return value;
	}
}
