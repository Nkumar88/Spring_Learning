package nk.pkg1;

public class Address {
	String streetName;
	String city;
	int zipCode;
	String country;
	
	public Address()
	{
		
	}
	
	public Address(String streetName, String city, int zipCode, String country)
	{
		this.streetName = streetName;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}
	

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Adress [streetName=" + streetName + ", city=" + city + ", zipCode=" + zipCode + ", country=" + country
				+ "]";
	}
	
	
}
