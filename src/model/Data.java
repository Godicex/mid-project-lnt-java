package model;
import java.util.Random;

public class Data {
	    protected Random rand = new Random();
	    String code,name,gender,jabatan;
	    int gaji;
	    
	    public Data(String name, String gender, String jabatan, String code, int gaji){
	    	this.name = name;
	    	this.gender = gender;
	    	this.jabatan = jabatan;
	    	this.code = code;
	    	this.gaji = gaji;
	    }

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getJabatan() {
			return jabatan;
		}

		public void setJabatan(String jabatan) {
			this.jabatan = jabatan;
		}

		public int getGaji() {
			return gaji;
		}

		public void setGaji(int gaji) {
			this.gaji = gaji;
		}
	    
	}
