package com.poly.repository;

import com.poly.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery,Long> {
}
