package com.brighttalk.demo.repository;

import com.brighttalk.demo.model.Realm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmRepository extends CrudRepository<Realm, Long> {

}
