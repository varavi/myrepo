package com.example.service;

import java.util.*;

import com.example.entity.Medicine;
import com.example.exception.MedicineNotFoundException;
import com.example.repository.MedicineRepositoryStub;

public class MedicineServiceImpl implements MedicineService{

	MedicineRepositoryStub medicineRepository=new MedicineRepositoryStub();

    @Override
    public Medicine getMedicine(int id) throws MedicineNotFoundException {
    	// TODO Auto-generated method stub
        Medicine med = medicineRepository.getMedicines().get(id);
        if(med!=null)
        return medicineRepository.getMedicines().get(id);
        else
            throw new MedicineNotFoundException();
    }

    @Override
	public Boolean isAvailable(int id) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		Medicine medicine=medicineRepository.getMedicines().get(id);
		if(medicine!=null)
		return medicineRepository.getMedicines().get(id).isAvailability();
		else
			return null;
	}

	@Override
	public List<String> manufacturers(String name) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
        List<Medicine> medicineList = List.copyOf(medicineRepository.getMedicines().values());
        List<String> newListMenuf = medicineList.stream().filter(medicine -> medicine.getName().equalsIgnoreCase(name))
                .map(Medicine::getManufacturer).toList();
        if(newListMenuf.isEmpty())
		return null;

        return newListMenuf;
	}

	@Override
	public String getLowestPriceByManufacturer(String medicineName) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
      String  manufacturers=null;
       try {
           List<Medicine> medicineList = List.copyOf(medicineRepository.getMedicines().values());
            manufacturers = medicineList.stream().filter(medicine -> medicine.getName()
                           .equalsIgnoreCase(medicineName)).sorted(Comparator.comparing(Medicine::getPrice))
                   .map(Medicine::getManufacturer)
                   .findFirst().get();

       }catch (NoSuchElementException noSuchElementException)
       {
           return "No manufacturers found";
       }
		return manufacturers;
	}

}
