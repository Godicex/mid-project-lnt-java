package program;

import java.util.Scanner;
import java.util.Vector;
import java.util.Random;

import model.Data;


public class Main {
    public static Scanner scan = new Scanner(System.in);
    private Vector<Data> listData = new Vector<Data>();
    
    public Main() {
    	mainMenu();	
    }
    
    private void mainMenu(){
        int menu=0;
        do{ 
        	System.out.println("\nDatabase Karyawan PT.Mentol");
        	System.out.println("=============================");
            System.out.println("1. Insert Data Karyawan");
            System.out.println("2. View Data Karyawan");
            System.out.println("3. Update Data Karyawan");
            System.out.println("4. Delete Data Karyawan");
            System.out.println("5. Exit");
            System.out.print("Pilih menu anda >> ");
            menu = scan.nextInt();
            scan.nextLine();
            switch(menu){
                case 1:
                createData();
                break;
                case 2:
                viewData();
                break;
                case 3:
                editData();
                break;
                case 4:
                popData();
                break;
            }
        }while(menu!=5);
    }
    
    public static String generateRandomChars() {
    	String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String candidateNums = "1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        sb.append("-");
        for (int i = 0; i < 4; i++) {
            sb.append(candidateNums.charAt(random.nextInt(candidateNums.length())));
        }
        return sb.toString();
    }
    
    private void functionBonus(String tempJabatan) {
		int totalMan = -1;
		int totalSup = -1;
		int totalAdm = -1;
		for (int i = 0; i < listData.size(); i++) {
			if(listData.get(i).getJabatan().equals("Manager")) {
				totalMan++;
			} else if (listData.get(i).getJabatan().equals("Supervisor")) {
				totalSup++;
			} else if (listData.get(i).getJabatan().equals("Admin")) {
				totalAdm++;
			}	
		}
		double bonus;
		int gaji;
		if(totalMan % 3 == 0 && totalMan != 0 && tempJabatan.equals("Manager")) {
			
			System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
			
			for (int i = 0; i < listData.size()-1; i++) {
				if(listData.get(i).getJabatan().equals("Manager")) {
					bonus = 0.1 * listData.get(i).getGaji();
					gaji = (int) (listData.get(i).getGaji() + bonus);
					listData.get(i).setGaji(gaji);
					if(i == listData.size()-2) {
						System.out.print(listData.get(i).getCode());
					}else {
					System.out.print(listData.get(i).getCode() + ", ");
					}
				}
			}
			System.out.println();
		} else if(totalSup % 3 == 0 && totalSup != 0 && tempJabatan.equals("Supervisor")) {
			
			System.out.print("Bonus sebesar 7,5% telah diberikan kepada karyawan dengan id ");
			
			for (int i = 0; i < listData.size()-1; i++) {
				if(listData.get(i).getJabatan().equals("Supervisor")) {
					bonus = 0.075 * listData.get(i).getGaji();
					gaji = (int) (listData.get(i).getGaji() + bonus);
					listData.get(i).setGaji(gaji);
					if(i == listData.size()-2) {
						System.out.print(listData.get(i).getCode());
					}else {
					System.out.print(listData.get(i).getCode() + ", ");
					}
				}
			}
			System.out.println();
		} else if(totalAdm % 3 == 0 && totalAdm != 0 && tempJabatan.equals("Admin")) {
			
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
			
			for (int i = 0; i < listData.size()-1; i++) {
				if(listData.get(i).getJabatan().equals("Admin")) {
					bonus = 0.05 * listData.get(i).getGaji();
					gaji = (int) (listData.get(i).getGaji() + bonus);
					listData.get(i).setGaji(gaji);
					if(i == listData.size()-2) {
						System.out.print(listData.get(i).getCode());
					}else {
					System.out.print(listData.get(i).getCode() + ", ");
					}
				}
			}
			System.out.println();
		}
    }
    
    private void createData() {
    	String name,gender,jabatan,code;
    	int gaji=0;
    	boolean nameBool = true;
		do {
			System.out.print("Input nama karyawan [>= 3]: ");
			name = scan.nextLine();
			for (int i = 0; i < name.length(); i++) {
				if(!Character.isAlphabetic(name.charAt(i))) {
					nameBool = false;
				} else {
					nameBool = true;
				}
			}
		} while (!nameBool || name.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			gender = scan.nextLine();
		} while (!gender.equals("Laki-laki") && !gender.equals("Perempuan"));

		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
		} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
       	code = generateRandomChars();
    	System.out.println("Berhasil menambahkan karyawan dengan ID " +code);
    	if(jabatan.equals("Manager")) {
    		gaji = 8000000;
    	}else if(jabatan.equals("Supervisor")) {
    		gaji = 6000000;
    	}else if(jabatan.equals("Admin")) {
    		gaji = 4000000;
    	}
    	Data karyawan = new Data(name, gender, jabatan, code, gaji);
    	listData.add(karyawan);
    	functionBonus(jabatan);
    }

	private void sortData() {
		for (int i = 0; i < listData.size(); i++) {
			for (int j = i+1; j < listData.size(); j++) {
				if (listData.get(i).getName().compareTo(listData.get(j).getName()) > 0) {
					String temp = listData.get(i).getCode();
					listData.get(i).setCode(listData.get(j).getCode());
					listData.get(j).setCode(temp);
					temp = listData.get(i).getName();
					listData.get(i).setName(listData.get(j).getName());
					listData.get(j).setName(temp);
					temp = listData.get(i).getGender();
					listData.get(i).setGender(listData.get(j).getGender());
					listData.get(j).setGender(temp);
					temp = listData.get(i).getJabatan();
					listData.get(i).setJabatan(listData.get(j).getJabatan());
					listData.get(j).setJabatan(temp);
					int t = listData.get(i).getGaji();
					listData.get(i).setGaji(listData.get(j).getGaji());
					listData.get(j).setGaji(t);
				}
			}
		}
	}
    
    private void viewData() {
    	if(listData.size() == 0) {
    		System.out.println("No Data!");
    	}else{
    	sortData();
		System.out.printf("|---|---------------------|--------------------------------------------------|---------------------|---------------|---------------|\n");
		System.out.printf("|%-3s|%-21s|%-50s|%-21s|%-15s|%-15s|\n", "No", "Kode Karyawan","Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan");
		System.out.printf("|---|---------------------|--------------------------------------------------|---------------------|---------------|---------------|\n");
		for (int i = 0; i < listData.size(); i++) {
			System.out.printf("|%3s|%21s|%50s|%21s|%15s|%15d|\n", (i + 1),listData.get(i).getCode(), listData.get(i).getName(),
					listData.get(i).getGender(), listData.get(i).getJabatan(), listData.get(i).getGaji());
		}
		System.out.printf("|---|---------------------|--------------------------------------------------|---------------------|---------------|---------------|\n");
    }
    }
    
	private int getNum() {
		int menu;
		try {
			menu = scan.nextInt();
		} catch (Exception e) {
			menu = -1;
		}
		scan.nextLine();
		return menu;
	}
	
    
    private void popData() {
		if(listData.size() == 0) {
			System.out.println("No Data!");
		}else {
			viewData();
			int index;
			do {
				System.out.print("Masukkan nomor yang ingin dihapus : ");
				index = getNum();
			} while (index < 1 || index > listData.size());
			index--;
			String kode = listData.get(index).getCode();
			listData.remove(index);
			System.out.println("Karyawan dengan kode " + kode + " berhasil dihapus");
		}
    	
    }
    
    private void editData() {
    	if(listData.size() == 0) {
			System.out.println("No Data!");
		}else {
			viewData();
			int index;
			do {
				System.out.print("Masukkan nomor yang ingin diupdate : ");
				index = getNum();
				index--;
			} while (index < 0 || index > listData.size());
			boolean codeBool = true;
			String kode;
			do {
				System.out.print("Input kode karyawan [MM-XXXX]: ");
				kode = scan.nextLine();
				
				for (int i = 0; i < 2; i++) {
					if(!Character.isAlphabetic(kode.charAt(i))){
						codeBool = false;
					} else {
						codeBool = true;
					}
				}
				
				if(!kode.contains("-")) {
					codeBool = false;
				} else {
					codeBool = true;
				}
				
				for (int i = 3; i < 7; i++) {
					if(!Character.isDigit(kode.charAt(i))){
						codeBool = false;
					} else {
						codeBool = true;
					}
				}
				
			} while (!codeBool || kode.length() != 7);
			
			String nama, jenisKelamin, jabatan;
			int gaji;
			
			boolean nameBool = true;
			do {
				System.out.print("Input nama karyawan [>= 3]: ");
				nama = scan.nextLine();
				
				for (int i = 0; i < nama.length(); i++) {
					if(!Character.isAlphabetic(nama.charAt(i))) {
						nameBool = false;
					} else {
						nameBool = true;
					}
				}
				
			} while (!nameBool || nama.length() < 3);
			
			do {
				System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				jenisKelamin = scan.nextLine();
			} while (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan"));

			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
				jabatan = scan.nextLine();
			} while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
			
			if(jabatan.equals("Manager")) {
				gaji = 8000000;
			} else if(jabatan.equals("Supervisor")) {
				gaji = 6000000;
			} else {
				gaji = 4000000;
			}
			System.out.println("Update sukses!");
			listData.get(index).setGaji(gaji);
			listData.get(index).setJabatan(jabatan);
			listData.get(index).setGender(jenisKelamin);
			listData.get(index).setCode(kode);
			listData.get(index).setName(nama);
		}
    }
    
    public static void main(String[] args){
        new Main();
    }
}
