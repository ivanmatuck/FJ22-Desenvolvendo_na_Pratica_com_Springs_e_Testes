package br.com.caelum.ingresso.model;

public class ImagemCapa {
	@JsonProperty("Poster")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
