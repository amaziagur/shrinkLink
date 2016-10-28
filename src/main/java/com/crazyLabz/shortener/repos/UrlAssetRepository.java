package com.crazyLabz.shortener.repos;

import com.crazyLabz.shortener.entities.UrlAsset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlAssetRepository extends MongoRepository<UrlAsset, String>{
}
