package com.walkingtree.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


import com.walkingtree.entity.UserElasticSearch;

@Repository
public interface UserElasticSearchRepository extends ElasticsearchRepository<UserElasticSearch, String>{

}
