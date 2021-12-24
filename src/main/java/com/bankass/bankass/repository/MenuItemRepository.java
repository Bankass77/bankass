package com.bankass.bankass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.MenuItem;

@Repository
public interface MenuItemRepository  extends JpaRepository<MenuItem, Long>{
	public List<MenuItem> findByParent(Long parent);
}
