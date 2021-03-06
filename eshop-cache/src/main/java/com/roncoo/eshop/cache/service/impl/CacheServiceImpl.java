package com.roncoo.eshop.cache.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.roncoo.eshop.cache.model.ProductInfo;
import com.roncoo.eshop.cache.model.ShopInfo;
import com.roncoo.eshop.cache.service.CacheService;

import redis.clients.jedis.JedisCluster;

/**
 * 缓存Service实现类
 * @author Administrator
 *
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {
	
	public static final String CACHE_NAME = "local";
	
	@Resource
	private JedisCluster jedisCluster;
	
	/** 
	 * 将商品信息保存到本地缓存中
	 * @param productInfo
	 * @return
	 */
	@Override
    @CachePut(value = CacheServiceImpl.CACHE_NAME, key = "'key_'+#productInfo.getId()")
	public ProductInfo saveLocalCache(ProductInfo productInfo) {
		return productInfo;
	}
	
	/**
	 * 从本地缓存中获取商品信息
	 * @param id 
	 * @return
	 */
	@Override
    @Cacheable(value = CacheServiceImpl.CACHE_NAME, key = "'key_'+#id")
	public ProductInfo getLocalCache(Long id) {
		return null;
	}
	
	/**
	 * 将商品信息保存到本地的ehcache缓存中
	 * @param productInfo
	 */
	@Override
    @CachePut(value = CacheServiceImpl.CACHE_NAME, key = "'product_info_'+#productInfo.getId()")
	public ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo) {
		return productInfo;
	}
	
	/**
	 * 从本地ehcache缓存中获取商品信息
	 * @param productId
	 * @return
	 */
	@Override
    @Cacheable(value = CacheServiceImpl.CACHE_NAME, key = "'product_info_'+#productId")
	public ProductInfo getProductInfoFromLocalCache(Long productId) {
		return null;
	}
	
	/**
	 * 将店铺信息保存到本地的ehcache缓存中
	 */
	@Override
    @CachePut(value = CacheServiceImpl.CACHE_NAME, key = "'shop_info_'+#shopInfo.getId()")
	public ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo) {
		return shopInfo;
	}
	
	/**
	 * 从本地ehcache缓存中获取店铺信息
	 * @return
	 */
	@Override
    @Cacheable(value = CacheServiceImpl.CACHE_NAME, key = "'shop_info_'+#shopId")
	public ShopInfo getShopInfoFromLocalCache(Long shopId) {
		return null;
	}
	
	/**
	 * 将商品信息保存到redis中
	 * @param productInfo 
	 */
	@Override
    public void saveProductInfo2ReidsCache(ProductInfo productInfo) {
		String key = "product_info_" + productInfo.getId();
		this.jedisCluster.set(key, JSON.toJSONString(productInfo));  
	}
	
	/**
	 * 将店铺信息保存到redis中
	 */
	@Override
    public void saveShopInfo2ReidsCache(ShopInfo shopInfo) {
		String key = "shop_info_" + shopInfo.getId();
		this.jedisCluster.set(key, JSON.toJSONString(shopInfo));  
	}
	
}
