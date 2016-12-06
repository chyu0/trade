package com.cjt.trade.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cjt.trade.dto.BaseDto;
import com.cjt.trade.model.Brand;

@Repository
public interface IBrandDao {
	
	public int insertBrand(Brand brand);
	
	public int deleteBrand(int id);
	
	public List<Brand> getAllBrands(BaseDto dto);
	public int getAllBrandsCount(BaseDto dto);
	
	public Brand getBrandById(int id);
	
	public int updateBrand(Brand brand);
}