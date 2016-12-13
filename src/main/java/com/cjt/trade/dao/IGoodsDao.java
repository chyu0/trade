package com.cjt.trade.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cjt.trade.dto.BaseDto;
import com.cjt.trade.model.Goods;
import com.cjt.trade.vo.GoodsVo;

@Repository
public interface IGoodsDao {

	public int insertGoods(Goods product);
	
	public int deleteGoods(int id);
	
	public List<GoodsVo> getAllGoods(BaseDto dto);
	public int getAllGoodsCount(BaseDto dto);
	
	public Goods getGoodsById(int id);
	
	public int updateGoods(Goods product);
}